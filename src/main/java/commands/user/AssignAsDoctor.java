package commands.user;

import commands.Command;
import model.dto.UserDto;
import model.entity.Role;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static commands.Constants.*;

public class AssignAsDoctor implements Command {

    private static UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto currentUser = (UserDto) session.getAttribute(USER);
        if(currentUser.getRole() == Role.ADMIN) {
            long userId = Long.parseLong(request.getParameterValues("id")[0]);


//            userService.assignAsNurse(userId);

            return HREF_LIST_OF_DOCTORS;
        }
        return PAGE_ERROR;
    }
}