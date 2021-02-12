package service;

import org.apache.log4j.Logger;

import model.dao.AssignmentNursehelperDao;
import model.dao.DaoFactory;


public class AssignmentNurshelperService {
	private static final Logger LOGGER = Logger.getLogger(AssignmentNurshelperService.class);

	public void addNurseToAssignment(long nurseId, long assignmentId) {
		try (AssignmentNursehelperDao nurseHelperDao = DaoFactory.getInstance().createAssignmentNursehelperDao()) {
			nurseHelperDao.addUserToAssignment(nurseId, assignmentId);
			LOGGER.info("User id#[" + nurseId + "] assigned to Assignment id [" + assignmentId + "]");
		}
	}
}
