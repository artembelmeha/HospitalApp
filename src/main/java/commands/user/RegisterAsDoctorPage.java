package commands.user;

import commands.Command;
import model.dto.DoctorDto;
import model.dto.UserDto;
import model.entity.Role;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static commands.Constants.*;

public class RegisterAsDoctorPage implements Command {

    private static UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto currentUser = (UserDto) session.getAttribute(USER);
        if(currentUser.getRole() == Role.ADMIN) {
            long userId = Long.parseLong(request.getParameterValues(ID)[0]);
            DoctorDto doctor = new DoctorDto(userService.getUserById(userId));
            session.setAttribute(DOCTOR, doctor);
            return REDIRECT_ADMIN_REGISTER_DOCTOR;
        }
        return PAGE_ACCESS_DENIED;
    }
}