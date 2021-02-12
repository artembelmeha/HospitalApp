package model.dao.mapper;

import exception.EntityNotFoundException;
import model.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import static commands.Constants.*;

public class MedicalCardMapper {

    public static MedicalCard extractMedicalCard(ResultSet resultSet) throws SQLException {
        return extractMedicalCard(resultSet, EMPTY_STRING);
    }

    public static MedicalCard extractMedicalCard(ResultSet resultSet, String message) throws SQLException {
        if (resultSet.next()) {
            return extractFromResultSet(resultSet);
        }
        throw new EntityNotFoundException(message);
    }

    public static MedicalCard extractFromResultSet(ResultSet rs) throws SQLException {
        MedicalCard medicalCard = new MedicalCard();
        medicalCard.setId(rs.getLong(ID));
        medicalCard.setFinalDiagnosis(rs.getString("final_diagnosis"));
        return medicalCard;
    }

}
