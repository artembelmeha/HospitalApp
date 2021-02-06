package model.dao;

import model.dto.DoctorDto;
import model.dto.PatientDto;
import model.entity.Role;
import model.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends GenericDao<User>{

    User findUserByEmail(String email);

    List<User> getUsersByRole(Role role);

    List<User> getUserByDoctorId(long id);

    void updateUserRole(long id, Role role);

    void updateUserToDoctor(DoctorDto doctorDto);

    void updateUserToPatient(PatientDto patientDto) throws SQLException;

    void updateDoctorPatientsNumber(Long id, int patientsNumber);

    List<User> getNurserByAssignmentID(long id);

    User getUserByMedicalCardId(long medicalCardId);

     void dischargePatient(User user) throws SQLException;


    List<User> getUsersByNurseId(long id);
}
