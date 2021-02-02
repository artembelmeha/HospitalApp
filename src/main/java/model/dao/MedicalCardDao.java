package model.dao;

import model.entity.MedicalCard;
import model.entity.User;

public interface MedicalCardDao extends GenericDao<MedicalCard> {
    MedicalCard findByDiagnosis(String diagnosis);
}
