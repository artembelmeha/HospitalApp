package model.dao;




import model.entity.Assignment;
import model.entity.User;

import java.util.List;

public interface AssignmentDao extends GenericDao<Assignment> {

    List<Assignment> getAssignmentByMedicalCardId(long id);
}
