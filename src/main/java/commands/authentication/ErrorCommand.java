package commands.authentication;

import commands.Command;

import javax.servlet.http.HttpServletRequest;

public class ErrorCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

//        return "/error.jsp";
        return null;  //todo
    }
}
