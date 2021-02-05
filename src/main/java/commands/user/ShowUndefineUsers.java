package commands.user;

import commands.Command;
import model.dto.UserDto;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

import static commands.Constants.*;
import static model.entity.Role.UNDEFINE;

public class ShowUndefineUsers implements Command {

    public static final UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<UserDto> undefineUsers = userService.getUsersByRole(UNDEFINE).stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
        session.setAttribute(UNDEFINED, undefineUsers);
        return REDIRECT_ADMIN_UNDEFINED;
    }
}
