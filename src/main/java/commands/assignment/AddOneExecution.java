package commands.assignment;

import static commands.Constants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import commands.Command;
import model.dto.UserDto;
import model.entity.Assignment;
import model.entity.AssignmentType;
import service.AssignmentService;
import service.ServiceFactory;

public class AddOneExecution implements Command {

    private static AssignmentService assignmentService = ServiceFactory.getInstance().getAssignmentService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto currentUser = (UserDto) session.getAttribute(USER);
        Assignment assignment = (Assignment) session.getAttribute(ASSIGNMENT);
        long assignmentId = assignment.getId();

        if (currentUser.isDoctor()) {
            assignmentService.addOneExecutionById(assignmentId);
            return REDIRECT_DOCTOR_ASSIGNMENT_INFO_ID + assignmentId;
        } else if (currentUser.isNurse() && assignment.getType() != AssignmentType.SURGERY) {
            assignmentService.addOneExecutionById(assignmentId);
            return REDIRECT_NURSE_ASSIGNMENT_INFO_ID + assignmentId;
        }

        return PAGE_ACCESS_DENIED;
    }
}
