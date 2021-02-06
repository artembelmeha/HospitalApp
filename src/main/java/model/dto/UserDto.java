package model.dto;

import model.entity.Role;
import model.entity.User;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static model.entity.Role.UNDEFINE;

public class UserDto implements HttpSessionBindingListener {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String password;

    private static Map<UserDto, HttpSession> logins = new HashMap<>();

    public UserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.password = user.getPassword();
    }

    public UserDto(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public UserDto() {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        User user = new User();
        user.setPassword(this.getPassword());
        user.setRole(UNDEFINE);
        user.setEmail(this.getEmail());
        user.setLastName(this.getLastName());
        user.setFirstName(this.getFirstName());
        user.setOnTreatment(false);
        user.setPatientsNumber(0);
        return user;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return getId() == userDto.getId() && Objects.equals(getFirstName(), userDto.getFirstName()) && Objects.equals(getLastName(), userDto.getLastName()) && Objects.equals(getEmail(), userDto.getEmail()) && getRole() == userDto.getRole() && Objects.equals(getPassword(), userDto.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getEmail(), getRole(), getPassword());
    }
    public boolean isAdmin(){
        return this.role == Role.ADMIN;
    }
    public boolean isDoctor(){
        return this.role == Role.DOCTOR;
    }
    public boolean isNurse(){
        return this.role == Role.NURSE;
    }
    public boolean isUndefine(){
        return this.role == Role.UNDEFINE;
    }
    public boolean isPatient(){
        return this.role == Role.PATIENT;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        HttpSession session = logins.get(this);
        if(session != null) {
            session.invalidate();
        }
        logins.put(this, event.getSession());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        logins.remove(this);
    }
}