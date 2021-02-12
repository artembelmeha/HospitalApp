package service;

import exception.EntityNotFoundException;
import exception.UnknownSqlException;
import model.dao.AssignmentNursehelperDao;
import model.dao.DaoFactory;
import org.apache.log4j.Logger;

public class AssignmentNurshelperService {
    private static final Logger LOGGER = Logger.getLogger(AssignmentNurshelperService.class);

    public void addNurseToAssignment(long nurseId, long assignmentId) {
        try (AssignmentNursehelperDao assignmentNursehelperDao =
                     DaoFactory.getInstance().createAssignmentNursehelperDao()) {
            assignmentNursehelperDao.addUserToAssignment(nurseId, assignmentId);
            LOGGER.info("User id#[" + nurseId + "] assigned to Assignment id [" + assignmentId + "]");
        }
    }


}
