package service;

import model.dao.DaoFactory;
import model.dao.MedicalCardDao;
import model.dao.UserDao;
import model.dto.UserDto;
import model.entity.MedicalCard;
import model.entity.User;
import org.apache.log4j.Logger;

import static model.entity.Role.UNDEFINE;

public class MedicalCardService {

    private static final Logger LOGGER = Logger.getLogger(MedicalCardService.class);

    public MedicalCard create(String keyWord) {
        MedicalCard medicalCard = new MedicalCard();
        medicalCard.setFinalDiagnosis(keyWord);
        try (MedicalCardDao medicalCardDao = DaoFactory.getInstance().createMedicalCardDao()){
            return medicalCardDao.create(medicalCard);
        }
    }
}
