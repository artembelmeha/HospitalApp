package model.dao.impl;

import exception.UnknownSqlException;
import model.dao.AssignmentNursehelperDao;
import model.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JDBCAssignmentNursehelperDao implements AssignmentNursehelperDao {

    private static final Logger LOGGER = Logger.getLogger(JDBCAssignmentNursehelperDao.class);

    private Connection connection;

    public static final String INSERT_INTO_ASSIGNMENT_NURSEHELPER = "INSERT INTO assignment_nursehelper " +
            "(nurse_id, assignment_id) VALUES (? , ?)";

    public JDBCAssignmentNursehelperDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public AssignmentNursehelperDao create(AssignmentNursehelperDao entity) {
        return null;
    }

    @Override
    public AssignmentNursehelperDao findById(long id) {
        return null;
    }

    @Override
    public List<AssignmentNursehelperDao> findAll() {
        return null;
    }

    @Override
    public void update(AssignmentNursehelperDao entity) {

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

    @Override
    public void addUserToAssignment(long nurseId, long assignmentId) {
        try (PreparedStatement ps = connection.prepareCall(INSERT_INTO_ASSIGNMENT_NURSEHELPER)) {
            ps.setLong(1, nurseId);
            ps.setLong(2, assignmentId);
            ps.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }
}
