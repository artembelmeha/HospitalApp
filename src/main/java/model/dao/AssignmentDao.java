package model.dao;




import model.entity.Assignment;

import java.util.List;

public interface AssignmentDao extends GenericDao<AssignmentDao> {

    List<Assignment> getAssignmentByMedicalCardId(long id);
}
