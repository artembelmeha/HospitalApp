package commands.user;

import commands.Command;
import model.dto.DoctorDto;
import model.dto.PatientDto;
import model.dto.UserDto;
import model.entity.Role;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static commands.Constants.*;

public class ShowPatients implements Command {


    private static UserService userService = ServiceFactory.getInstance().getUserService();

    private int page = 1;
    private static final int RECORDS_PER_PAGE = 5;
    private String sortBy = FIRST_NAME;

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto currentUser = (UserDto) session.getAttribute(USER);
        List<PatientDto> patients;
        extractedAttributes(request);

        if (currentUser.isAdmin()) {
            patients = userService.getUsersByRole(Role.PATIENT).stream()
                    .map(PatientDto::new)
                    .collect(Collectors.toList());
            sortByAttribute(patients);
            setAttributeToSession(session, patients);
            return REDIRECT_ADMIN_PATIENTS;
        }
        if (currentUser.isDoctor()) {
            patients = userService.getUsersByDoctorId(currentUser.getId()).stream()
                    .map(PatientDto::new)
                    .collect(Collectors.toList());
            sortByAttribute(patients);
            setAttributeToSession(session, patients);
            return REDIRECT_DOCTOR_PATIENTS;
        }
        if (currentUser.isNurse()) {
            patients = userService.getUsersByAssignedNurse(currentUser.getId()).stream()
                    .map(PatientDto::new)
                    .collect(Collectors.toList());
            sortByAttribute(patients);
            setAttributeToSession(session, patients);
            return REDIRECT_NURSE_PATIENTS;
        }
        return "/error.jsp";
    }

    private void extractedAttributes(HttpServletRequest request) {
        if (request.getParameter(ATTRIBUTE_PAGE) != null)
            page = Integer.parseInt(request.getParameter(ATTRIBUTE_PAGE));
        if (request.getParameter(ATTRIBUTE_SORT_BY) != null)
            sortBy = request.getParameter(ATTRIBUTE_SORT_BY);
    }

    private void sortByAttribute(List<PatientDto> patients) {
        switch (sortBy) {
            case BIRTH_DATE:
                patients.sort(Comparator.comparing(PatientDto::getBirthDate).reversed());
                break;
            case LAST_NAME:
                patients.sort(Comparator.comparing(PatientDto::getLastName));
                break;
            default:
                patients.sort(Comparator.comparing(PatientDto::getFirstName));
        }
    }

    private void setAttributeToSession(HttpSession session, List<PatientDto> patients) {
        session.setAttribute(ATTRIBUTE_NO_OF_PAGES, getNumberOfPages(patients.size(), RECORDS_PER_PAGE));
        session.setAttribute(ATTRIBUTE_CURRENT_PAGE, page);
        session.setAttribute(ATTRIBUTE_SORT_BY, sortBy);
        session.setAttribute(PATIENTS, getPage(patients));
    }

    private int getNumberOfPages(int size, int recordsPerPage) {
        int result = size / recordsPerPage;
        return size % recordsPerPage > 0 ? result + 1 : result;
    }

    private List<PatientDto> getPage(List<PatientDto> patients) {
        return patients.stream()
                .skip(page * RECORDS_PER_PAGE - RECORDS_PER_PAGE)
                .limit(RECORDS_PER_PAGE)
                .collect(Collectors.toList());
    }

}
