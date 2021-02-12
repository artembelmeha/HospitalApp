package model.dao.impl;

import static model.dao.mapper.AssignmentMapper.extractAssignment;
import static model.dao.mapper.AssignmentMapper.extractAssignments;

import java.sql.*;
import java.util.List;

import org.apache.log4j.Logger;

import exception.UnknownSqlException;
import model.dao.AssignmentDao;
import model.dao.JDBCDao;
import model.entity.Assignment;

public class JDBCAssignmentDao extends JDBCDao implements AssignmentDao {

    private static final Logger LOGGER = Logger.getLogger(JDBCAssignmentDao.class);

    public JDBCAssignmentDao(Connection connection) {
        super(connection);
    }

    private static final String GET_ASSIGNMENT_BY_CARD_ID = "SELECT * FROM assignment WHERE card_id = ?";
    private static final String GET_ASSIGNMENT_BY_ID = "SELECT * FROM assignment WHERE id = ?";
    private static final String UPDATE_ASSIGNMENT = "UPDATE assignment SET current_diagnosis = ?, date = ?, done_times = ?, " +
            "is_complete = ?, name = ?, notes = ?, quantity = ?, type = ? WHERE  id = ?";
    private static final String CREATE_ASSIGNMENT_QUERY = "INSERT INTO assignment (current_diagnosis, date, done_times, " +
            "is_complete, name, notes, quantity, type, card_id) VALUES  (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_FROM_HELPER_BY_ASSIGNMENT_ID = "DELETE FROM assignment_nursehelper where assignment_id = ?;";


    @Override
    public List<Assignment> getAssignmentsByMedicalCardId(long id) {
        try (PreparedStatement ps = connection.prepareCall(GET_ASSIGNMENT_BY_CARD_ID)) {
            ps.setLong(1, id);
            return extractAssignments(ps.executeQuery());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public Assignment create(Assignment assignment) {
        try (PreparedStatement ps = connection.prepareCall(CREATE_ASSIGNMENT_QUERY)) {
            ps.setString(1, assignment.getCurrentDiagnosis());
            ps.setDate(2, Date.valueOf(assignment.getDate()));
            ps.setInt(3, assignment.getDoneTimes());
            ps.setBoolean(4, assignment.getIsComplete());
            ps.setString(5, assignment.getName());
            ps.setString(6, assignment.getNotes());
            ps.setInt(7, assignment.getQuantity());
            ps.setString(8, assignment.getType().name());
            ps.setLong(9, assignment.getCardId());
            ps.execute();
            return assignment;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public Assignment findById(long id) {
        try (PreparedStatement ps = connection.prepareCall(GET_ASSIGNMENT_BY_ID)) {
            ps.setString(1, String.valueOf(id));
            return extractAssignment(ps.executeQuery(), "There is no assignment with id [" + id + "]");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public void update(Assignment assignment) {
        try (PreparedStatement ps = connection.prepareCall(UPDATE_ASSIGNMENT)) {
            ps.setString(1, assignment.getCurrentDiagnosis());
            ps.setDate(2, Date.valueOf(assignment.getDate()));
            ps.setInt(3, assignment.getDoneTimes());
            ps.setBoolean(4, assignment.getIsComplete());
            ps.setString(5, assignment.getName());
            ps.setString(6, assignment.getNotes());
            ps.setInt(7, assignment.getQuantity());
            ps.setString(8, assignment.getType().name());
            ps.setLong(9, assignment.getId());
            ps.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        //for future
    }

    @Override
    public void cleanupAssignmentRefWhenCompleted(long id) {
        try (PreparedStatement ps = connection.prepareCall(DELETE_FROM_HELPER_BY_ASSIGNMENT_ID)) {
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            LOGGER.error("Error during [cleanupAssignmentRefWhenCompleted].", e);
            throw new UnknownSqlException(e.getMessage());
        }
    }
}
