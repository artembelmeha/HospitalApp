package commands.user;

import commands.Command;
import exception.InvalidDataException;
import model.dto.PatientDto;
import model.dto.UserDto;
import model.entity.Assignment;
import model.entity.Sex;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.time.LocalDate;

import static commands.Constants.*;

public class AssignAsPatient implements Command {

    private static UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto currentUser = (UserDto) session.getAttribute(USER);
        PatientDto patientDto;
        if (currentUser.isAdmin()) {
            try {
                patientDto = getPatientDtoFromSession(request);
                validate(patientDto);
            } catch (Exception e) {
                session.setAttribute(ERROR, "Data is invalid");
                return PAGE_REGISTRATION_PATIENT;
            }
            userService.assignAsPatient(patientDto);
            return HREF_LIST_OF_PATIENTS;
        }
        return PAGE_ACCESS_DENIED;
    }

    private PatientDto getPatientDtoFromSession(HttpServletRequest request) throws InvalidDataException {
        PatientDto patientDto = (PatientDto) request.getSession().getAttribute(PATIENT);
        patientDto.setBirthDate(LocalDate.parse(request.getParameter(BIRTH_DATE)));
        patientDto.setTelephoneNumber(request.getParameter(TELEPHONE_NUMBER));
        patientDto.setSex(Sex.valueOf(request.getParameter(PARAMETER_SEX)));
        patientDto.setDoctorId(Long.parseLong(request.getParameter(DOCTOR)));
        return patientDto;
    }

    private void validate(PatientDto patientDto) throws InvalidDataException {
        if (patientDto.getBirthDate().isAfter(LocalDate.now())) {
            throw new InvalidDataException("Date is invalid");
        }
    }
}