package commands.authentication;

import commands.Command;

import javax.servlet.http.HttpServletRequest;
import static commands.Constants.*;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return REDIRECT_INDEX;
    }
}
