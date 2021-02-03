package model.dao;

import exception.ErrorMessageKeysContainedException;
import exception.UnknownSqlException;
import model.dto.DoctorDto;
import model.dto.PatientDto;
import model.entity.Role;
import model.entity.User;

import java.util.List;

public interface UserDao extends GenericDao<User>{

    User findUserByEmail(String email) throws UnknownSqlException, ErrorMessageKeysContainedException;

    List<User> getUsersByRole(Role role) throws UnknownSqlException, ErrorMessageKeysContainedException;

    List<User> getUserByDoctorId(long id);

    void updateUserRole(long id, Role role);

    void updateUserToDoctor(DoctorDto doctorDto);

    void updateUserToPatient(PatientDto patientDto);

    void updateDoctorPatientsNumber(Long id, int patientsNumber);

    List<User> getNurserByAssignmentID(long id);
}
