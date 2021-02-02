package model.dao.mapper;

import model.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class MedicalCardMapper {

    public MedicalCard extractFromResultSet(ResultSet rs) throws SQLException {
        MedicalCard medicalCard = new MedicalCard();
        medicalCard.setId(rs.getInt("id"));
        medicalCard.setFinalDiagnosis(rs.getString("final_diagnosis"));
        return medicalCard;
    }

}
