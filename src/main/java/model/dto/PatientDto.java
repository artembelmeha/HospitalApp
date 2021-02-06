package model.dto;

import model.entity.Sex;
import model.entity.User;

import java.time.LocalDate;

public class PatientDto extends UserDto {

    private long doctorId;
    private long cardId;
    private boolean isOnTreatment;
    private LocalDate birthDate;
    private Sex sex;
    private String telephoneNumber;

    public PatientDto(User user) {
        super(user);
        this.doctorId = user.getDoctorId();
        this.cardId = user.getCardId();
        this.isOnTreatment = user.isOnTreatment();
        this.birthDate = user.getBirthDate();
        this.sex = user.getSex();
        this.telephoneNumber = user.getTelephoneNumber();
    }

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public boolean getIsOnTreatment() {
        return isOnTreatment;
    }

    public void setOnTreatment(boolean onTreatment) {
        isOnTreatment = onTreatment;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "isOnTreatment=" + isOnTreatment;
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
