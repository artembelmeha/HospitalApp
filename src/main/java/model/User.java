package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class User {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
//    private Role role; //todo add later
//    private Qualification qualification; //todo add later
    private User doctor;
    private List<User> patients = new ArrayList<>();
    private boolean isOnTreatment = false;
    private LocalDate birthDate;
//    private Sex sex;  //todo add later
    private String telephoneNumber;
//    private MedicalCard medicalCard;//todo add later
//    private Set<Assignment> assignmentList;//todo add later
    private int patientsNumber;
}
