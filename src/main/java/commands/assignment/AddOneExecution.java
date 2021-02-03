package commands.assignment;

import commands.Command;
import model.dto.UserDto;
import model.entity.Assignment;
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
        long assignmentId = Long.parseLong(request.getParameterValues(ID)[0]);
        System.out.println(assignmentId);

//        HttpSession session = request.getSession();
//        Assignment assignment = assignmentService.getAssignmentById(assignmentId);
//        List<UserDto> assignedNurses = userService.getNursesByAssignmentID(assignmentId);
//        List<UserDto> freeNurses = userService.getUsersByRole(Role.NURSE).stream()
//                .map(UserDto::new)
//                .collect(Collectors.toList());
//        freeNurses.removeAll(assignedNurses);
//
//
//        session.setAttribute(ASSIGNMENT, assignment);
//        session.setAttribute(NURSES, assignedNurses);
//        session.setAttribute(FREE_NURSES, freeNurses);
//        return REDIRECT_ADMIN_ASSIGNMENT_INFO;
        return null;
    }
}
