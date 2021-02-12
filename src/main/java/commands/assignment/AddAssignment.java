package commands.assignment;

import commands.Command;
import exception.InvalidDataException;
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

import org.apache.log4j.Logger;



public class AddAssignment implements Command {

    private static final Logger LOGGER = Logger.getLogger(AddAssignment.class);
    private static final AssignmentService assignmentService = ServiceFactory.getInstance().getAssignmentService();

    private static final int INITIAL_DONE_TIMES = 0;

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        MedicalCard medicalCard = (MedicalCard) session.getAttribute(MEDICAL_CARD);
        UserDto currentUser = (UserDto) session.getAttribute(USER);

        if(currentUser.isDoctor()) {
            Assignment assignment;
            try {
                assignment = createAssignmentFromRequest(request);
                validate(assignment);
            } catch (InvalidDataException e) {
                session.setAttribute(ERROR, "Data is invalid");
                return PAGE_ADD_ASSIGNMENT;
            }
            assignment.setCardId(medicalCard.getId());
            assignmentService.createAssignment(assignment);
            return REDIRECT_DOCTOR_MEDICAL_CARD_ID  + medicalCard.getId();
        }
        return PAGE_ACCESS_DENIED;
    }

    private void validate(Assignment assignment) throws InvalidDataException {
        if (assignment.getDate().isAfter(LocalDate.now()) ) {
            LOGGER.error("[AssignmentValidation] invalid assignment date.");
            throw new InvalidDataException("Date is invalid");
        }
        if (assignment.getQuantity() < 1) {
            LOGGER.error("[AssignmentValidation] invalid assignment quantity [{}].");
            throw new InvalidDataException("Quantity is less than 1");
        }
    }

    private Assignment createAssignmentFromRequest(HttpServletRequest request) {
        Assignment assignment = new Assignment();
        assignment.setDoneTimes(INITIAL_DONE_TIMES);
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
