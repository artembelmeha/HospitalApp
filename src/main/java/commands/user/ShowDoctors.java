package commands.user;

import commands.Command;
import model.dto.DoctorDto;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

import static commands.Constants.DOCTORS;
import static commands.Constants.REDIRECT_ADMIN_DOCTORS;
import static model.entity.Role.DOCTOR;

public class ShowDoctors implements Command {


    public static final UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<DoctorDto> doctors = getDoctors();
        session.setAttribute(DOCTORS, doctors);
        return REDIRECT_ADMIN_DOCTORS;
    }

    private List<DoctorDto> getDoctors() {
        return userService.getUsersByRole(DOCTOR).stream()
                .map(DoctorDto::new)
                .collect(Collectors.toList());
    }
}
