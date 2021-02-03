package model.dao.impl;

import model.dao.AssignmentNursehelperDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class JDBCAssignmentNursehelperDao implements AssignmentNursehelperDao {

    private static final Logger LOGGER = Logger.getLogger(JDBCAssignmentNursehelperDao.class);

    private Connection connection;

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

    }
}
