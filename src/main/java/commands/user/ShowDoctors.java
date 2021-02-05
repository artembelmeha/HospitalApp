package commands.user;

import commands.Command;
import model.dto.DoctorDto;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static commands.Constants.*;
import static model.entity.Role.DOCTOR;

public class ShowDoctors implements Command {

    public static final UserService userService = ServiceFactory.getInstance().getUserService();
    private int page = 1;
    private static final int RECORDS_PER_PAGE = 5;
    private String sortBy = FIRST_NAME;

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<DoctorDto> doctors = getDoctors();
        extractedAttributes(request);
        sortByAttribute(doctors);
        setAttributeToSession(session, doctors);
        return REDIRECT_ADMIN_DOCTORS;
    }

    private void setAttributeToSession(HttpSession session, List<DoctorDto> doctors) {
        session.setAttribute(ATTRIBUTE_NO_OF_PAGES, getNumberOfPages(doctors.size(), RECORDS_PER_PAGE));
        session.setAttribute(ATTRIBUTE_CURRENT_PAGE, page);
        session.setAttribute(ATTRIBUTE_SORT_BY, sortBy);
        session.setAttribute(DOCTORS,  getPage(doctors));
    }

    private List<DoctorDto> getPage(List<DoctorDto> doctors) {
        return doctors.stream()
                .skip(page * RECORDS_PER_PAGE - RECORDS_PER_PAGE)
                .limit(RECORDS_PER_PAGE)
                .collect(Collectors.toList());
    }

    private void extractedAttributes(HttpServletRequest request) {
        if (request.getParameter(ATTRIBUTE_PAGE) != null)
            page = Integer.parseInt(request.getParameter(ATTRIBUTE_PAGE));
        if (request.getParameter(ATTRIBUTE_SORT_BY) != null)
            sortBy = request.getParameter(ATTRIBUTE_SORT_BY);
    }

    private void sortByAttribute(List<DoctorDto> doctors) {
        switch (sortBy) {
            case NUMBER_OF_PATIENT:
                doctors.sort(Comparator.comparing(DoctorDto::getPatientsNumber).reversed());
                break;
            case QUALIFICATION:
                doctors.sort(Comparator.comparing(DoctorDto::getQualification));
                break;
            case LAST_NAME:
                doctors.sort(Comparator.comparing(DoctorDto::getLastName));
                break;
            default:
                doctors.sort(Comparator.comparing(DoctorDto::getFirstName));
        }
    }

    private int getNumberOfPages(int size, int recordsPerPage) {
        int result = size / recordsPerPage;
        return size % recordsPerPage > 0 ? result + 1 : result;
    }


    private List<DoctorDto> getDoctors() {
        return userService.getUsersByRole(DOCTOR).stream()
                .map(DoctorDto::new)
                .collect(Collectors.toList());
    }
}
