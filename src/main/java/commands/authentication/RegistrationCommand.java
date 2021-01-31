package commands.authentication;

import commands.Command;
import exception.UnknownSqlException;
import model.dto.UserDto;
import model.entity.User;
import org.apache.log4j.Logger;
import service.ServiceFactory;
import service.UserService;
import utils.EncryptUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static commands.Constants.*;

public class RegistrationCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(RegistrationCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = ServiceFactory.getInstance().getUserService();
        HttpSession session = request.getSession();
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String lastName = request.getParameter(LAST_NAME);
        String firstName = request.getParameter(FIRST_NAME);

        try{
            User user = userService.getUserByEmail(email);
            if (user != null) {
                throw new UnknownSqlException("User with such email already exist");
            }
        } catch (UnknownSqlException e){
            LOGGER.error(e.getMessage());
            session.setAttribute(ERROR, e.getMessage());
            return PAGE_REGISTRATION;
        }
        userService.create(new UserDto(firstName, lastName,email,EncryptUtil.encryptString(password)));
        return PAGE_LOGIN;

    }
}
