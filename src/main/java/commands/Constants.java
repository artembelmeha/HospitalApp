package commands;

public interface Constants {
    public static final String EMAIL = "email";
    public static final String EMPTY_STRING = "";
    public static final String PASSWORD = "password";
    public static final String USER = "user";
    public static final String ID = "id";
    public static final String DATE = "date";
    public static final String SEX = "sex";
    public static final String LAST_NAME = "lastName";
    public static final String FIRST_NAME = "firstName";
    public static final String MEDICAL_CARD = "medicalCard";
    public static final String ERROR = "error";
    public static final String UNDEFINED = "undefined";
    public static final String NURSES = "nurses";
    public static final String FREE_NURSES = "freeNurses";
    public static final String DOCTORS = "doctors";
    public static final String TELEPHONE_NUMBER = "telephoneNumber";
    public static final String BIRTH_DATE = "birthDate";
    public static final String DOCTOR = "doctor";
    public static final String PATIENTS = "patients";
    public static final String PATIENT = "patient";
    public static final String DOCTOR_FULL_NAME = "doctorFullName";
    public static final String QUALIFICATION = "qualification";
    public static final String ASSIGNMENTS = "assignments";
    public static final String ASSIGNMENT = "assignment";








    public static final String PAGE_LOGIN = "/login.jsp";
    public static final String PAGE_ACCESS_DENIED = "/api/access_denied.jsp";
    public static final String PAGE_REGISTRATION = "/registration.jsp";



    public static final String HREF_LIST_OF_NURSES = "/api/admin/nurses";
    public static final String HREF_LIST_OF_DOCTORS = "/api/admin/doctors";
    public static final String HREF_LIST_OF_PATIENTS = "/api/admin/patients";






    public static final String PREFIX_REDIRECT = "redirect:";

    public static final String REDIRECT_ADMIN_SUCCESS = PREFIX_REDIRECT+"/admin/success.jsp";
    public static final String REDIRECT_ADMIN_UNDEFINED = PREFIX_REDIRECT+"/admin/users.jsp";
    public static final String REDIRECT_ADMIN_NURSES = PREFIX_REDIRECT+"/admin/nurses.jsp";
    public static final String REDIRECT_ADMIN_DOCTORS = PREFIX_REDIRECT+"/admin/doctors.jsp";
    public static final String REDIRECT_ADMIN_PATIENTS = PREFIX_REDIRECT+"/admin/patients.jsp";
    public static final String REDIRECT_ADMIN_PATIENT_INFO = PREFIX_REDIRECT+"/admin/patientInfo.jsp";
    public static final String REDIRECT_ADMIN_REGISTER_DOCTOR = PREFIX_REDIRECT+"/admin/registerDoctor.jsp";
    public static final String REDIRECT_ADMIN_REGISTER_PATIENT = PREFIX_REDIRECT+"/admin/registerPatient.jsp";
    public static final String REDIRECT_ADMIN_MEDICAL_CARD = PREFIX_REDIRECT+"/admin/medicalCard.jsp";
    public static final String REDIRECT_ADMIN_ASSIGNMENT_INFO = PREFIX_REDIRECT+"/admin/assignmentInfo.jsp";


    public static final String REDIRECT_DOCTOR_SUCCESS = PREFIX_REDIRECT+"/doctor/success.jsp";
    public static final String REDIRECT_DOCTOR_PATIENTS = PREFIX_REDIRECT+"/doctor/patients.jsp";
    public static final String REDIRECT_DOCTOR_PATIENT_INFO = PREFIX_REDIRECT+"/doctor/patientInfo.jsp";
    public static final String REDIRECT_DOCTOR_MEDICAL_CARD = PREFIX_REDIRECT+"/doctor/medicalCard.jsp";
    public static final String REDIRECT_DOCTOR_ASSIGNMENT_INFO = PREFIX_REDIRECT+"/doctor/assignmentInfo.jsp";
    public static final String REDIRECT_DOCTOR_ASSIGNMENT_INFO_ID = PREFIX_REDIRECT+"redirect:/doctor/assignmentInfo?id=";



    public static final String REDIRECT_NURSE_ASSIGNMENT_INFO_ID = PREFIX_REDIRECT+"redirect:/nurse/assignmentInfo?id=";

    public static final String REDIRECT_INDEX = PREFIX_REDIRECT+"/index.jsp";
}
