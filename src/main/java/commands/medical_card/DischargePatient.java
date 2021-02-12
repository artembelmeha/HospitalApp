package commands.medical_card;

import commands.Command;
import model.dto.UserDto;
import model.entity.MedicalCard;
import service.MedicalCardService;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static commands.Constants.*;

public class DischargePatient implements Command {

    private static UserService userService = ServiceFactory.getInstance().getUserService();
    private static MedicalCardService medicalCardService = ServiceFactory.getInstance().getCardService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto currentUser = (UserDto) session.getAttribute(USER);
        String diagnosis = request.getParameter(PARAMETER_FINAL_DIAGNOSIS);
        MedicalCard medicalCard = (MedicalCard) session.getAttribute(MEDICAL_CARD);
        medicalCard.setFinalDiagnosis(diagnosis);

        if (currentUser.isDoctor()) {
            medicalCardService.update(medicalCard);
            userService.dischargePatientByMedicalCardId(medicalCard.getId());
            return REDIRECT_DOCTOR_PATIENTS_HREF;
        }
        return PAGE_ACCESS_DENIED;
    }
}
