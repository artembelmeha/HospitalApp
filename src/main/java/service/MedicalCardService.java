package service;

import exception.EntityNotFoundException;
import exception.UnknownSqlException;
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

    public MedicalCard getMedicalCardById(long id) {
        try (MedicalCardDao medicalCardDao = DaoFactory.getInstance().createMedicalCardDao()) {
            return medicalCardDao.findById(id);
        } catch (EntityNotFoundException | UnknownSqlException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException();
        }
    }

    public void setFinalDiagnosisById(MedicalCard medicalCard) {
        try (MedicalCardDao medicalCardDao = DaoFactory.getInstance().createMedicalCardDao()) {
            medicalCardDao.update(medicalCard);
        } catch (EntityNotFoundException | UnknownSqlException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException();
        }
    }
}
