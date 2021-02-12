package service;

import java.sql.SQLException;
import java.util.List;

import exception.UnknownSqlException;
import model.dao.AssignmentDao;
import model.dao.DaoFactory;
import model.entity.Assignment;
import org.apache.log4j.Logger;

public class AssignmentService {

    private static final int ONE_EXECUTION = 1;
    private static final Logger LOGGER = Logger.getLogger(AssignmentService.class);

    public List<Assignment> getAssignmentByMedicalCardId(long id) {
        try (AssignmentDao assignmentDao = DaoFactory.getInstance().createAssignmentDao()) {
            return assignmentDao.getAssignmentByMedicalCardId(id);
        }
    }

    public Assignment getAssignmentById(long id) {
        try (AssignmentDao assignmentDao = DaoFactory.getInstance().getAssignmentDao()) {
            return assignmentDao.findById(id);
        }
    }

    public void addOneExecutionById(long id) {
        try (AssignmentDao assignmentDao = DaoFactory.getInstance().getAssignmentDao()) {
            Assignment assignment = assignmentDao.findById(id);
            assignment.setDoneTimes(assignment.getDoneTimes() + ONE_EXECUTION);

            if(assignment.getDoneTimes() == assignment.getQuantity()) {
                assignment.setIsComplete(true);
                assignmentDao.cleanupAssignmentRefWhenCompleted(id);
            }
            assignmentDao.update(assignment);
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
    }

    public void createAssignment(Assignment assignment) {
        try (AssignmentDao assignmentDao = DaoFactory.getInstance().getAssignmentDao()) {
            assignmentDao.create(assignment);
        }
    }
}