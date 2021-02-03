package model.dao.impl;

import exception.EntityNotFoundException;
import exception.UnknownSqlException;
import model.dao.AssignmentDao;
import model.dao.mapper.AssignmentMapper;
import model.dao.mapper.MedicalCardMapper;
import model.entity.Assignment;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCAssignmentDao implements AssignmentDao {

    private static final Logger LOGGER = Logger.getLogger(JDBCAssignmentDao.class);

    private Connection connection;

    public static final String FROM_ASSIGNMENT_WHERE_CARD_ID = "SELECT * FROM assignment WHERE card_id = ?";
    public static final String FROM_ASSIGNMENT_WHERE_ID = "SELECT * FROM assignment WHERE id = ?";


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
    public Assignment create(Assignment entity) {
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
    public void update(Assignment entity) {

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
