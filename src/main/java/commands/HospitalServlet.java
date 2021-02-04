package commands;

import commands.assignment.AddAssignment;
import commands.assignment.AddOneExecution;
import commands.assignment.AssignmentInfo;
import commands.assignmnet_nurshelper.AddNurseToAssignment;
import commands.authentication.ErrorCommand;
import commands.authentication.LoginCommand;
import commands.authentication.LogoutCommand;
import commands.authentication.RegistrationCommand;
import commands.medicalCard.DischargePatient;
import commands.medicalCard.ShowMedicalCard;
import commands.user.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import static commands.Constants.*;

public class HospitalServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(HospitalServlet.class);

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
        commands.put("admin/patient", new ShowPatientById());
        commands.put("doctor/patient", new ShowPatientById());
        commands.put("nurse/patient", new ShowPatientById());
        commands.put("admin/medicalCard", new ShowMedicalCard());
        commands.put("doctor/medicalCard", new ShowMedicalCard());
        commands.put("nurse/medicalCard", new ShowMedicalCard());
        commands.put("doctor/patients", new ShowPatients());
        commands.put("admin/assignAsNurse", new AssignAsNurse());
        commands.put("admin/assignAsDoctor", new AssignAsDoctor());
        commands.put("admin/assignAsPatient", new AssignAsPatient());
        commands.put("admin/registerAsDoctor", new RegisterAsDoctorPage());
        commands.put("admin/registerAsPatient", new RegisterAsPatientPage());
        commands.put("admin/assignmentInfo", new AssignmentInfo());
        commands.put("doctor/assignmentInfo", new AssignmentInfo());
        commands.put("nurse/assignmentInfo", new AssignmentInfo());
        commands.put("doctor/addOneExecution", new AddOneExecution());
        commands.put("doctor/addNurseToAssignment", new AddNurseToAssignment());
        commands.put("doctor/addAssignment", new AddAssignment());
        commands.put("doctor/dischargePatient", new DischargePatient());
        commands.put("nurse/addOneExecution", new AddOneExecution());

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
        path = path.replaceAll(".*/api/" , EMPTY_STRING);
        path = path.replaceAll(".jsp",EMPTY_STRING);
        Command command = commands.getOrDefault(path , (r)->"/index.jsp");
        LOGGER.info(command.getClass().getName());
        String page = command.execute(request);
        if(page.contains(PREFIX_REDIRECT)){
            response.sendRedirect(page.replace(PREFIX_REDIRECT, "/api"));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
