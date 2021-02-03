package service;

import exception.EntityNotFoundException;
import exception.UnknownSqlException;
import model.dao.AssignmentDao;
import model.dao.DaoFactory;
import model.entity.Assignment;
import org.apache.log4j.Logger;

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
}
