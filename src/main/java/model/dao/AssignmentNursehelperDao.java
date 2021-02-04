package model.dao;

public interface AssignmentNursehelperDao extends GenericDao<AssignmentNursehelperDao>{

    void addUserToAssignment(long nurseId, long assignmentId);
}
