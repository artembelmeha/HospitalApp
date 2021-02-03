package model.dao.impl;

import exception.UnknownSqlException;
import model.dao.AssignmentDao;
import model.dao.mapper.AssignmentMapper;
import model.entity.Assignment;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JDBCAssignmentDao implements AssignmentDao {

    private static final Logger LOGGER = Logger.getLogger(JDBCAssignmentDao.class);

    private Connection connection;

    public static final String FROM_ASSIGNMENT_WHERE_CARD_ID = "SELECT * FROM assignment WHERE card_id = ?";

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
    public AssignmentDao create(AssignmentDao entity) {
        return null;
    }

    @Override
    public AssignmentDao findById(long id) {
        return null;
    }

    @Override
    public List<AssignmentDao> findAll() {
        return null;
    }

    @Override
    public void update(AssignmentDao entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
