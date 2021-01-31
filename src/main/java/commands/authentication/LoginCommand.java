package commands.authentication;

import commands.Command;
import exception.UnknownSqlException;
import model.dto.UserDto;
import model.entity.User;
import service.ServiceFactory;
import service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static commands.authentication.Constants.*;
import static model.entity.Role.ADMIN;

public class LoginCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);


    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = ServiceFactory.getInstance().getUserService();
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        HttpSession session = request.getSession();
        if( email == null || email.equals("") || password == null || password.equals("")  ){
            return PAGE_LOGIN;
        }
        try {
            userService.signIn(email, password);
            UserDto currentUser = new UserDto(userService.getUserByEmail(email));
            System.out.println(currentUser);
            session.setAttribute(USER, currentUser);
            if (currentUser.getRole().equals(ADMIN)){
                return REDIRECT_ADMIN_SUCCESS;
            }
            return REDIRECT_DOCTOR_SUCCESS;
        } catch (UnknownSqlException  e) {
            LOGGER.error("Error caught while executing the method:", e);
            session.setAttribute("login_fails", e.getMessage());
            return PAGE_LOGIN;
        }
    }
}
