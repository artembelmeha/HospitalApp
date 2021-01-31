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
import static commands.Constants.USER;
import static model.entity.Role.NURSE;

public class ShowNurses implements Command {


    public static UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<UserDto> nurses = userService.getUsersByRole(NURSE).stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
        session.setAttribute(NURSES, nurses);
        return REDIRECT_ADMIN_NURSES;
    }
}
