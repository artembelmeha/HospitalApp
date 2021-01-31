package commands.user;

import commands.Command;
import model.dto.DoctorDto;
import model.dto.PatientDto;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

import static commands.Constants.*;
import static model.entity.Role.DOCTOR;
import static model.entity.Role.PATIENT;

public class ShowPatients implements Command {


    public static UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<PatientDto> patients = userService.getUsersByRole(PATIENT).stream()
                .map(PatientDto::new)
                .collect(Collectors.toList());
        session.setAttribute(PATIENTS, patients);
        return REDIRECT_ADMIN_PATIENTS;
    }
}
