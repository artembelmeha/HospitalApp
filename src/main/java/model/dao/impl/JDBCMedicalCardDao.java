package model.dao.impl;

import exception.EntityNotFoundException;
import exception.UnknownSqlException;
import model.dao.MedicalCardDao;
import model.dao.mapper.MedicalCardMapper;
import model.dao.mapper.UserMapper;
import model.entity.MedicalCard;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JDBCMedicalCardDao implements MedicalCardDao {

    private static final Logger LOGGER = Logger.getLogger(JDBCMedicalCardDao.class);
    private Connection connection;

    private static final String INSERT_TEMPLATE = "INSERT INTO cards (final_diagnosis) VALUES (?)";
    public static final String CARDS_DIAGNOSIS = "SELECT * FROM cards WHERE final_diagnosis = ?";
    private static final String UPDATE_CARD = "UPDATE cards SET final_diagnosis = ? WHERE id = ?";
    public static final String FROM_CARDS_WHERE_ID = "SELECT * FROM cards WHERE id = ?";


    public JDBCMedicalCardDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public MedicalCard create(MedicalCard medicalCard) {
        try(PreparedStatement ps = connection.prepareCall(INSERT_TEMPLATE)) {
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

    @Override
    public MedicalCard findByDiagnosis(String diagnosis) {
        try(PreparedStatement ps = connection.prepareCall(CARDS_DIAGNOSIS)){
            ps.setString( 1, diagnosis);
            ResultSet rs = ps.executeQuery();
            MedicalCardMapper medicalCardMapper = new MedicalCardMapper();
            if(rs.next()) {
                return medicalCardMapper.extractFromResultSet(rs);
            }
            throw new EntityNotFoundException("There is no card with diagnosis ["+diagnosis+"]");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public MedicalCard findById(long id) {
        try (PreparedStatement ps = connection.prepareCall(FROM_CARDS_WHERE_ID)) {
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            MedicalCardMapper medicalCardMapper = new MedicalCardMapper();
            if (rs.next()) {
                return medicalCardMapper.extractFromResultSet(rs);
            }
            LOGGER.error("There is no medical card with id [" + id + "]");
            throw new EntityNotFoundException("There is no medical card with id [" + id + "]");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public List<MedicalCard> findAll() {
        return null;
    }

    @Override
    public void update(MedicalCard entity) {
        try(PreparedStatement ps = connection.prepareCall(UPDATE_CARD)) {
            ps.setString( 1, entity.getFinalDiagnosis());
            ps.setString( 2, String.valueOf(entity.getId()));
            ps.executeUpdate();
        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
