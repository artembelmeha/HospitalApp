package model.dao.impl;

import exception.UnknownSqlException;
import model.dao.JDBCDao;
import model.dao.MedicalCardDao;
import model.entity.MedicalCard;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static commands.Constants.EMPTY_STRING;
import static model.dao.mapper.MedicalCardMapper.extractMedicalCard;


public class JDBCMedicalCardDao extends JDBCDao implements MedicalCardDao {

    private static final Logger LOGGER = Logger.getLogger(JDBCMedicalCardDao.class);

    private static final String CREATE_CARD = "INSERT INTO cards (final_diagnosis) VALUES (?)";
    private static final String GET_CARD_BY_DIAGNOSIS = "SELECT * FROM cards WHERE final_diagnosis = ?";
    private static final String UPDATE_CARD = "UPDATE cards SET final_diagnosis = ? WHERE id = ?";
    private static final String GET_CARD_BY_ID = "SELECT * FROM cards WHERE id = ?";


    public JDBCMedicalCardDao(Connection connection) {
        super(connection);
    }

    @Override
    public MedicalCard create(MedicalCard medicalCard) {
        MedicalCard savedMedicalCard;

        try (PreparedStatement ps = connection.prepareStatement(CREATE_CARD)) {
            ps.setString(1, medicalCard.getFinalDiagnosis());
            ps.execute();
        }  catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
        MedicalCard savedMedicalCard = this.findByDiagnosis(medicalCard.getFinalDiagnosis());
        savedMedicalCard.setFinalDiagnosis("");
        this.update(savedMedicalCard);
        LOGGER.debug("MedicalCard with id [ "+savedMedicalCard.getId()+" ] successfully saved.");
        return savedMedicalCard;
    }

    private void cleanupFirstDiagnosis(MedicalCard savedMedicalCard) {
        savedMedicalCard.setFinalDiagnosis(EMPTY_STRING);
        update(savedMedicalCard);
    }

    @Override
    public MedicalCard findByDiagnosis(String diagnosis) {
        try(PreparedStatement ps = connection.prepareCall(GET_CARD_BY_DIAGNOSIS)){
            ps.setString( 1, diagnosis);
            return extractMedicalCard(ps.executeQuery(),"There is no card with diagnosis [" + diagnosis + "]");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public MedicalCard findById(long id) {
        try (PreparedStatement ps = connection.prepareCall(GET_CARD_BY_ID)) {
            ps.setLong(1, id);
            return extractMedicalCard(ps.executeQuery(), "There is no medical card with id [" + id + "]");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public void update(MedicalCard entity) {
        try (PreparedStatement ps = connection.prepareCall(UPDATE_CARD)) {
            ps.setString(1, entity.getFinalDiagnosis());
            ps.setLong(2, entity.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        // for future
    }

}
