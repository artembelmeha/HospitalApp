package model.dao.impl;

import exception.UnknownSqlException;
import model.dao.AssignmentNursehelperDao;
import model.dao.JDBCDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCAssignmentNursehelperDao extends JDBCDao implements AssignmentNursehelperDao {

    private static final Logger LOGGER = Logger.getLogger(JDBCAssignmentNursehelperDao.class);

    public JDBCAssignmentNursehelperDao(Connection connection) {
        super(connection);
    }

    private static final String CREATE_ASSIGNMENT_NURSEHELPER_QUERY = "INSERT INTO assignment_nursehelper " +
            "(nurse_id, assignment_id) VALUES (? , ?)";
    private static final String DELETE_BY_ASSIGNMENT_ID_QUERY = "DELETE FROM assignment_nursehelper where assignment_id = ?;";


    @Override
    public AssignmentNursehelperDao create(AssignmentNursehelperDao entity) {
        return null;
    }

    @Override
    public AssignmentNursehelperDao findById(long id) {
        return null;
    }


    @Override
    public void update(AssignmentNursehelperDao entity) {
        //for future
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement ps = connection.prepareCall(DELETE_BY_ASSIGNMENT_ID_QUERY)) {
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }


    @Override
    public void addUserToAssignment(long nurseId, long assignmentId) {
        try (PreparedStatement ps = connection.prepareCall(CREATE_ASSIGNMENT_NURSEHELPER_QUERY)) {
            ps.setLong(1, nurseId);
            ps.setLong(2, assignmentId);
            ps.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException(e.getMessage());
        }
    }
}
