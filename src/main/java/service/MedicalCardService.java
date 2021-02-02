package service;

import model.dao.DaoFactory;
import model.dao.MedicalCardDao;
import model.entity.MedicalCard;
import org.apache.log4j.Logger;

public class MedicalCardService {

    private static final Logger LOGGER = Logger.getLogger(MedicalCardService.class);

    public MedicalCard create(String keyWord) {
        MedicalCard medicalCard = new MedicalCard();
        medicalCard.setFinalDiagnosis(keyWord);
        try (MedicalCardDao medicalCardDao = DaoFactory.getInstance().createMedicalCardDao()){
            MedicalCard createdMC = medicalCardDao.create(medicalCard);
            LOGGER.info("Medical card #[" + createdMC.getId() + "] was successful created." );
            return createdMC;

        }
    }
}
