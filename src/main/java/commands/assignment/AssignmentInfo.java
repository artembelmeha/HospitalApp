package commands.assignment;

import commands.Command;
import model.dto.UserDto;
import model.entity.Assignment;
import model.entity.Role;
import service.AssignmentService;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.stream.Collectors;

import static commands.Constants.*;

public class AssignmentInfo implements Command {

    private static AssignmentService assignmentService = ServiceFactory.getInstance().getAssignmentService();
    private static UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request) {
        long assignmentId = Long.parseLong(request.getParameterValues(ID)[0]);
        HttpSession session = request.getSession();
        UserDto currentUser = (UserDto) session.getAttribute(USER);
        Assignment assignment = assignmentService.getAssignmentById(assignmentId);
        List<UserDto> assignedNurses = userService.getNursesByAssignmentID(assignmentId);
        session.setAttribute(ASSIGNMENT, assignment);
        session.setAttribute(NURSES, assignedNurses);
        session.setAttribute(FREE_NURSES, getFreeNurses(assignedNurses));
        if(currentUser.isAdmin()) {
            return REDIRECT_ADMIN_ASSIGNMENT_INFO;
        }
        if(currentUser.isDoctor()) {
            return REDIRECT_DOCTOR_ASSIGNMENT_INFO;
        }
        return null;
    }

    private List<UserDto> getFreeNurses(List<UserDto> assignedNurses) {
        List<UserDto> freeNurses = userService.getUsersByRole(Role.NURSE).stream()
                .map(UserDto::new)
                .collect(Collectors.toList());

        freeNurses.removeAll(assignedNurses);
        return freeNurses;
    }
}
