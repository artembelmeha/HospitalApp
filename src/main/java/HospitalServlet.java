import commands.Command;
import commands.authentication.ErrorCommand;
import commands.authentication.LoginCommand;
import commands.authentication.LogoutCommand;
import commands.authentication.RegistrationCommand;
import commands.user.ShowDoctors;
import commands.user.ShowNurses;
import commands.user.ShowPatients;
import commands.user.ShowUndefineUsers;
import service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class HospitalServlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig){

        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());
        commands.put("login", new LoginCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("error", new ErrorCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("admin/users", new ShowUndefineUsers());
        commands.put("admin/nurses", new ShowNurses());
        commands.put("admin/doctors", new ShowDoctors());
        commands.put("admin/patients", new ShowPatients());
        commands.put("doctor/patients", new ShowPatients());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getRequestURI();
        path = path.replaceAll(".*/api/" , "");
        path = path.replaceAll(".jsp","");
        Command command = commands.getOrDefault(path , (r)->"/index.jsp");
        System.out.println(command.getClass().getName());
        String page = command.execute(request);
        if(page.contains("redirect:")){
            response.sendRedirect(page.replace("redirect:", "/api"));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
