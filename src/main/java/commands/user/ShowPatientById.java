package commands.user;

import com.mysql.cj.xdevapi.Session;
import commands.Command;
import model.dto.PatientDto;
import model.entity.User;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static commands.Constants.*;

public class ShowPatientById implements Command {

    private static UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request) {
        long patientId = Long.parseLong(request.getParameterValues("id")[0]);
        HttpSession session = request.getSession();
        User user = userService.getUserById(patientId);
        User doctor = userService.getUserById(user.getDoctorId());
        session.setAttribute(DOCTOR_FULL_NAME, doctor.getFirstName() + " " + doctor.getLastName());
        session.setAttribute(QUALIFICATION, doctor.getQualification().name());
        session.setAttribute(PATIENT, new PatientDto(user));
        return REDIRECT_ADMIN_PATIENT_INFO;
    }
}
