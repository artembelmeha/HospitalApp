package commands.medicalCard;

import commands.Command;
import model.dto.UserDto;
import model.entity.Assignment;
import model.entity.MedicalCard;
import service.AssignmentService;
import service.MedicalCardService;
import service.ServiceFactory;

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
        UserDto currentUser = (UserDto) session.getAttribute(USER);

        MedicalCard medicalCard = medicalCardService.getMedicalCardById(medicalCardId);
        List<Assignment> assignmentList = assignmentService.getAssignmentByMedicalCardId(medicalCardId);
        session.setAttribute(MEDICAL_CARD, medicalCard);
        session.setAttribute(ASSIGNMENTS, assignmentList);
        if(currentUser.isAdmin()) {
            return REDIRECT_ADMIN_MEDICAL_CARD;
        }
        if(currentUser.isDoctor()) {
            return REDIRECT_DOCTOR_MEDICAL_CARD;
        }
        if(currentUser.isNurse()) {
            return REDIRECT_NURSE_MEDICAL_CARD;
        }
        return PAGE_ACCESS_DENIED;
    }
}
