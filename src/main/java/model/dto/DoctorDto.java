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

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public int getPatientsNumber() {
        return patientsNumber;
    }

    public void setPatientsNumber(int patientsNumber) {
        this.patientsNumber = patientsNumber;
    }

    @Override
    public String toString() {
        return "DoctorDto{" +
                "qualification=" + qualification +
                ", patientsNumber=" + patientsNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
