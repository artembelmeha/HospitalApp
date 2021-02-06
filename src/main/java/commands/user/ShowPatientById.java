package commands.user;

import commands.Command;
import model.dto.PatientDto;
import model.dto.UserDto;
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
        long patientId = Long.parseLong(request.getParameterValues(ID)[0]);
        HttpSession session = request.getSession();
        UserDto currentUser = (UserDto) session.getAttribute(USER);

        User user = userService.getUserById(patientId);
        User doctor = userService.getUserById(user.getDoctorId());
        session.setAttribute(DOCTOR_FULL_NAME, doctor.getFirstName() + " " + doctor.getLastName());
        session.setAttribute(QUALIFICATION, doctor.getQualification().name());
        session.setAttribute(PATIENT, new PatientDto(user));
        if(currentUser.isAdmin()) {
            return REDIRECT_ADMIN_PATIENT_INFO;
        }
        if(currentUser.isDoctor()) {
            return REDIRECT_DOCTOR_PATIENT_INFO;
        }
        if(currentUser.isNurse()) {
            return REDIRECT_NURSE_PATIENT_INFO;
        }
        return null;
    }

}
