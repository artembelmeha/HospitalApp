package model.dao.impl;

import exception.EntityNotFoundException;
import exception.UnknownSqlException;
import model.dao.AssignmentDao;
import model.dao.mapper.AssignmentMapper;
import model.dao.mapper.MedicalCardMapper;
import model.entity.Assignment;
import model.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCAssignmentDao implements AssignmentDao {

    private static final Logger LOGGER = Logger.getLogger(JDBCAssignmentDao.class);

    private Connection connection;

    public static final String FROM_ASSIGNMENT_WHERE_CARD_ID = "SELECT * FROM assignment WHERE card_id = ?";
    public static final String FROM_ASSIGNMENT_WHERE_ID = "SELECT * FROM assignment WHERE id = ?";
    private static final String UPDATE_TEMPLATE = "UPDATE assignment SET current_diagnosis = ?, date = ?, done_times = ?, " +
            "is_complete = ?, name = ?, notes = ?, quantity = ?, type = ? WHERE  id = ?";


    public JDBCAssignmentDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Assignment> getAssignmentByMedicalCardId(long id) {
        try (PreparedStatement ps = connection.prepareCall(FROM_ASSIGNMENT_WHERE_CARD_ID)) {
            List<Assignment> assignments = new ArrayList<>();
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            AssignmentMapper assignmentMapper = new AssignmentMapper();
            while (rs.next()) {
                assignments.add(assignmentMapper.extractFromResultSet(rs));
            }
            return assignments;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }

    @Override
    public Assignment create(Assignment assignment) {

        return null;
    }

    @Override
    public Assignment findById(long id) {
        try (PreparedStatement ps = connection.prepareCall(FROM_ASSIGNMENT_WHERE_ID)) {
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            AssignmentMapper assignmentMapper = new AssignmentMapper();
            if (rs.next()) {
                return assignmentMapper.extractFromResultSet(rs);
            }
            LOGGER.error("There is no assignment with id [" + id + "]");
            throw new EntityNotFoundException("There is no assignment with id [" + id + "]");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }



    @Override
    public List<Assignment> findAll() {
        return null;
    }

    @Override
    public void update(Assignment assignment) {
        try (PreparedStatement ps = connection.prepareCall(UPDATE_TEMPLATE)) {
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
    public void delete(int id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
