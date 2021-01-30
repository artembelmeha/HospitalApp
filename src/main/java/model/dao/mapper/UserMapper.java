package model.dao.mapper;

import model.entity.Qualification;
import model.entity.Role;
import model.entity.Sex;
import model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper {

    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setRole(Role.valueOf(rs.getString("role")));
        user.setBirthDate(rs.getDate("birth_date") == null ?
                null : rs.getDate("birth_date").toLocalDate());
        user.setCardId(rs.getInt("card_id"));
        user.setDoctorId(rs.getInt("doctor_id"));
        user.setSex(rs.getString("sex") == null ?
                null : Sex.valueOf(rs.getString("sex")));
        user.setQualification(rs.getString("qualification") == null ?
                null : Qualification.valueOf(rs.getString("qualification")));
        user.setOnTreatment(rs.getBoolean("on_treatment"));
        user.setPatientsNumber(rs.getInt("patients_number"));
        user.setTelephoneNumber(rs.getString("telephone_number") == null ?
                null : rs.getString("telephone_number"));
        return user;
    }

    public User makeUnique(Map<Long, User> cache, User User) {
        cache.putIfAbsent(User.getId(), User);
        return cache.get(User.getId());
    }

}