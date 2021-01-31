package model.dto;

import model.entity.Qualification;
import model.entity.User;

public class DoctorDto extends UserDto{
    private Qualification qualification;
    private int patientsNumber;

    public DoctorDto(User user) {
        super(user);
        this.qualification = user.getQualification();
        this.patientsNumber = user.getPatientsNumber();
    }
}
