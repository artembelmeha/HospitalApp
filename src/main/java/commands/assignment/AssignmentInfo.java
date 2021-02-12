package commands.assignment;

import static commands.Constants.*;
import static model.entity.Role.NURSE;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import commands.Command;
import model.dto.UserDto;
import model.entity.Assignment;
import service.*;


public class AssignmentInfo implements Command {

    private static AssignmentService assignmentService = ServiceFactory.getInstance().getAssignmentService();
    private static UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto currentUser = (UserDto) session.getAttribute(USER);

        long assignmentId = Long.parseLong(request.getParameterValues(ID)[0]);
        Assignment assignment = assignmentService.getAssignmentById(assignmentId);
        List<UserDto> assignedNurses = userService.getNursesByAssignmentID(assignmentId);

        session.setAttribute(ASSIGNMENT, assignment);
        session.setAttribute(NURSES, assignedNurses);
        session.setAttribute(FREE_NURSES, getFreeNurses(assignedNurses));

        if (currentUser.isAdmin()) {
            return REDIRECT_ADMIN_ASSIGNMENT_INFO;
        } else if (currentUser.isDoctor()) {
            return REDIRECT_DOCTOR_ASSIGNMENT_INFO;
        } else if (currentUser.isNurse()) {
            return REDIRECT_NURSE_ASSIGNMENT_INFO;
        }

        return PAGE_ACCESS_DENIED;
    }

    private List<UserDto> getFreeNurses(List<UserDto> assignedNurses) {
        List<UserDto> freeNurses = userService.getUsersByRole(NURSE).stream()
                .map(UserDto::new)
                .collect(Collectors.toList());

        freeNurses.removeAll(assignedNurses);
        return freeNurses;
    }
}
