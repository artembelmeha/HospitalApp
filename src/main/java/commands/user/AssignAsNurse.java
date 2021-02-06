package commands.user;

import commands.Command;
import model.dto.UserDto;
import model.entity.Role;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static commands.Constants.*;

public class AssignAsNurse implements Command {

    private static UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto currentUser = (UserDto) session.getAttribute(USER);
        if(currentUser.isAdmin()) {
            long userId = Long.parseLong(request.getParameterValues(ID)[0]);
            userService.assignAsNurse(userId);
            return HREF_LIST_OF_NURSES;
        }
        return PAGE_ACCESS_DENIED;
    }
}