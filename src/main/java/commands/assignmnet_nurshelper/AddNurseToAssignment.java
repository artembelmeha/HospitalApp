package commands.assignmnet_nurshelper;

import commands.Command;
import model.dto.UserDto;
import model.entity.Assignment;
import service.AssignmentNurshelperService;
import service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static commands.Constants.*;

public class AddNurseToAssignment implements Command {
    private static AssignmentNurshelperService assignmentNurshelperService
            = ServiceFactory.getInstance().getAssignmentNurshelperService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        long nurseId = Long.parseLong(request.getParameterValues(PARAMETER_ID)[0]);
        Assignment assignment = (Assignment) session.getAttribute(ASSIGNMENT);
        UserDto currentUser = (UserDto) session.getAttribute(USER);
        if(currentUser.isDoctor()) {
            assignmentNurshelperService.addNurseToAssignment(nurseId,assignment.getId());
            return REDIRECT_DOCTOR_ASSIGNMENT_INFO_ID  + assignment.getId();
        }
        return PAGE_ACCESS_DENIED;
    }
}
