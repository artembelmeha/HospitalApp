package commands.user;

import commands.Command;
import model.dto.DoctorDto;
import model.dto.UserDto;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

import static commands.Constants.*;
import static model.entity.Role.DOCTOR;
import static model.entity.Role.NURSE;

public class ShowDoctors implements Command {


    public static UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<DoctorDto> doctors = userService.getUsersByRole(DOCTOR).stream()
                .map(DoctorDto::new)
                .collect(Collectors.toList());
        session.setAttribute(DOCTORS, doctors);
        System.out.println(session.getAttribute(DOCTORS));
        return REDIRECT_ADMIN_DOCTORS;
    }
}
