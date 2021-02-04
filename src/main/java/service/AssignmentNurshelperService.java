package service;

import exception.EntityNotFoundException;
import exception.UnknownSqlException;
import model.dao.AssignmentDao;
import model.dao.AssignmentNursehelperDao;
import model.dao.DaoFactory;
import org.apache.log4j.Logger;

public class AssignmentNurshelperService {
    private static final Logger LOGGER = Logger.getLogger(AssignmentNurshelperService.class);

    public void addNurseToAssignment(long nurseId,long assignmentId) {
        try (AssignmentNursehelperDao assignmentNursehelperDao =
                     DaoFactory.getInstance().createAssignmentNursehelperDao()) {
            assignmentNursehelperDao.addUserToAssignment(nurseId, assignmentId);
        } catch (EntityNotFoundException | UnknownSqlException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            throw new UnknownSqlException();
        }
    }


}
