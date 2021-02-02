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

import java.util.List;
import java.util.stream.Collectors;

import static commands.Constants.*;

public class RegisterAsPatientPage implements Command {

    private static UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto currentUser = (UserDto) session.getAttribute(USER);
        if(currentUser.getRole() == Role.ADMIN) {
            long userId = Long.parseLong(request.getParameterValues("id")[0]);
            PatientDto patient = new PatientDto(userService.getUserById(userId));
            List<DoctorDto> doctors = userService.getUsersByRole(Role.DOCTOR).stream()
                    .map(DoctorDto::new)
                    .collect(Collectors.toList());
            session.setAttribute(DOCTORS, doctors);
            session.setAttribute(PATIENT, patient);
            return REDIRECT_ADMIN_REGISTER_PATIENT;
        }
        return PAGE_ERROR;
    }
}