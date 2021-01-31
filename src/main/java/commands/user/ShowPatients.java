package commands.user;

import commands.Command;
import model.dto.PatientDto;
import model.dto.UserDto;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static commands.Constants.*;
import static model.entity.Role.*;

public class ShowPatients implements Command {


    private static UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto currentUser = (UserDto) session.getAttribute(USER);
        List<PatientDto> patients;

        if(currentUser.getRole() == ADMIN) {
            patients = userService.getUsersByRole(PATIENT).stream()
                    .map(PatientDto::new)
                    .collect(Collectors.toList());
            session.setAttribute(PATIENTS, patients);
            return REDIRECT_ADMIN_PATIENTS;
        }
        if (currentUser.getRole() == DOCTOR) {
                patients = userService.getUsersByDoctorId(currentUser.getId()).stream()
                        .map(PatientDto::new)
                        .collect(Collectors.toList());
                session.setAttribute(PATIENTS, patients);
                return REDIRECT_DOCTOR_PATIENTS;
        }
        return "/error.jsp";
    }
}
