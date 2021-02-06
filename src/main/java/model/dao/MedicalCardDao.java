package model.dao;

import model.entity.MedicalCard;

public interface MedicalCardDao extends GenericDao<MedicalCard> {
    MedicalCard findByDiagnosis(String diagnosis);
}
