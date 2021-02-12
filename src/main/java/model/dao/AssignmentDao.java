package model.dao;


import model.entity.Assignment;

import java.util.List;

public interface AssignmentDao extends GenericDao<Assignment> {

    List<Assignment> getAssignmentsByMedicalCardId(long id);

    void cleanupAssignmentRefWhenCompleted(long id);
}
