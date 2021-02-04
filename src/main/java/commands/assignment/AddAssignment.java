package commands.assignment;

import commands.Command;
import model.dto.UserDto;
import model.entity.Assignment;
import model.entity.AssignmentType;
import model.entity.MedicalCard;
import service.AssignmentService;
import service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

import static commands.Constants.*;

public class AddAssignment implements Command {


    private static AssignmentService assignmentService = ServiceFactory.getInstance().getAssignmentService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        MedicalCard medicalCard = (MedicalCard) session.getAttribute(MEDICAL_CARD);
        UserDto currentUser = (UserDto) session.getAttribute(USER);
        if(currentUser.isDoctor()) {
            Assignment assignment = getAssignmentFromSession(request);
            assignment.setCardId(medicalCard.getId());
            assignmentService.addAssignmentToMedicalCard(assignment);
            return REDIRECT_DOCTOR_MEDICAL_CARD_ID  + medicalCard.getId();
        }
        return PAGE_ACCESS_DENIED;
    }

    private Assignment getAssignmentFromSession(HttpServletRequest request) {
        Assignment assignment = new Assignment();
        assignment.setDoneTimes(0);
        assignment.setType(AssignmentType.valueOf(request.getParameter(PARAMETER_TYPE)));
        assignment.setName(request.getParameter(PARAMETER_NAME));
        assignment.setNotes(request.getParameter(PARAMETER_NOTES));
        assignment.setIsComplete(false);
        assignment.setQuantity(Integer.parseInt(request.getParameter(PARAMETER_QUANTITY)));
        assignment.setDate(LocalDate.parse(request.getParameter(PARAMETER_DATE)));
        assignment.setCurrentDiagnosis(request.getParameter(PARAMETER_CURRENT_DIAGNOSIS));
        return assignment;

    }
}
