package commands.user;

import commands.Command;
import model.entity.Role;
import model.entity.User;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static commands.Constants.*;
import static model.entity.Role.UNDEFINE;

public class ShowUndefineUsers implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = ServiceFactory.getInstance().getUserService();
        HttpSession session = request.getSession();
        List<User> undefineUsers = userService.getUsersByRole(UNDEFINE);
        session.setAttribute(UNDEFINED, undefineUsers);
        return REDIRECT_ADMIN_UNDEFINED;
    }
}
