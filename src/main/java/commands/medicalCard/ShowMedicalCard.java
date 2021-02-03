package commands.medicalCard;

import commands.Command;
import model.dto.PatientDto;
import model.entity.Assignment;
import model.entity.MedicalCard;
import model.entity.User;
import service.AssignmentService;
import service.MedicalCardService;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static commands.Constants.*;

public class ShowMedicalCard implements Command {

    private static MedicalCardService medicalCardService = ServiceFactory.getInstance().getCardService();
    private static AssignmentService assignmentService = ServiceFactory.getInstance().getAssignmentService();

    @Override
    public String execute(HttpServletRequest request) {
        long medicalCardId = Long.parseLong(request.getParameterValues(ID)[0]);
        HttpSession session = request.getSession();
        MedicalCard medicalCard = medicalCardService.getMedicalCardById(medicalCardId);
        List<Assignment> assignmentList = assignmentService.getAssignmentByMedicalCardId(medicalCardId);
        session.setAttribute(MEDICAL_CARD, medicalCard);
        session.setAttribute(ASSIGNMENTS, assignmentList);


//        User doctor = userService.getUserById(user.getDoctorId());
//        session.setAttribute(QUALIFICATION, doctor.getQualification().name());
//        session.setAttribute(PATIENT, new PatientDto(user));
        return REDIRECT_ADMIN_MEDICAL_CARD;
    }
}
