package service;

import exception.EntityNotFoundException;
import exception.UnknownSqlException;
import model.dao.AssignmentDao;
import model.dao.DaoFactory;
import model.entity.Assignment;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class AssignmentService {

    private static final Logger LOGGER = Logger.getLogger(AssignmentService.class);

    public List<Assignment> getAssignmentByMedicalCardId(long id) {
        try (AssignmentDao assignmentDao = DaoFactory.getInstance().createAssignmentDao()) {
            return assignmentDao.getAssignmentByMedicalCardId(id);
        } catch (EntityNotFoundException | UnknownSqlException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException();
        }
    }

    public Assignment getAssignmentById(long id) {
        try (AssignmentDao assignmentDao = DaoFactory.getInstance().createAssignmentDao()) {
            return assignmentDao.findById(id);
        } catch (EntityNotFoundException | UnknownSqlException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException();
        }
    }

    public void addOneExecutionById(long id) {
        try (AssignmentDao assignmentDao = DaoFactory.getInstance().createAssignmentDao()) {
            Assignment assignment = assignmentDao.findById(id);
            assignment.setDoneTimes(assignment.getDoneTimes() + 1);
            assignmentDao.update(assignment);
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException();
        }
    }

    public void addAssignmentToMedicalCard(Assignment assignment) {
        try (AssignmentDao assignmentDao = DaoFactory.getInstance().createAssignmentDao()) {
            assignmentDao.create(assignment);
        }catch (EntityNotFoundException | UnknownSqlException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
    }
}
