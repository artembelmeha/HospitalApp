package commands.user;

import commands.Command;
import model.dto.DoctorDto;
import model.dto.PatientDto;
import model.dto.UserDto;
import model.entity.Qualification;
import model.entity.Role;
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


        if (currentUser.getRole() == Role.ADMIN) {
            PatientDto patientDto = (PatientDto) session.getAttribute(PATIENT);
            patientDto.setBirthDate(LocalDate.parse(request.getParameter("birthDate")));
            patientDto.setTelephoneNumber(request.getParameter("telephoneNumber"));
            patientDto.setSex(Sex.valueOf(request.getParameter("sex")));
            patientDto.setDoctorId(Long.parseLong(request.getParameter(DOCTOR)));
            userService.assignAsPatient(patientDto);
            return HREF_LIST_OF_PATIENTS;
        }
        return PAGE_ERROR;
    }
}