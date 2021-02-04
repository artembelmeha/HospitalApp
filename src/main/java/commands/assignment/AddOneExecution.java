package commands.assignment;

import commands.Command;
import model.dto.UserDto;
import model.entity.Assignment;
import model.entity.AssignmentType;
import model.entity.Role;
import service.AssignmentService;
import service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

import static commands.Constants.*;

public class AddOneExecution implements Command {

    private static AssignmentService assignmentService = ServiceFactory.getInstance().getAssignmentService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Assignment assignment = (Assignment) session.getAttribute(ASSIGNMENT);
        UserDto currentUser = (UserDto) session.getAttribute(USER);
        if(currentUser.isDoctor()) {
            assignmentService.addOneExecutionById(assignment.getId());
            return REDIRECT_DOCTOR_ASSIGNMENT_INFO_ID  + assignment.getId();
        }
        if(currentUser.isDoctor() && assignment.getType() != AssignmentType.SURGERY) {
            assignmentService.addOneExecutionById(assignment.getId());
            return REDIRECT_NURSE_ASSIGNMENT_INFO_ID  + assignment.getId();
        }
        return PAGE_ACCESS_DENIED;
    }
}
