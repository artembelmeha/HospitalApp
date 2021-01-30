package model.entity;

import java.time.LocalDate;

public class User {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private Qualification qualification;
    private long doctorId;
    private long cardId;
    private boolean isOnTreatment = false;
    private LocalDate birthDate;
    private Sex sex;
    private String telephoneNumber;
    private int patientsNumber;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
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

    public boolean isOnTreatment() {
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

    public int getPatientsNumber() {
        return patientsNumber;
    }

    public void setPatientsNumber(int patientsNumber) {
        this.patientsNumber = patientsNumber;
    }

}
