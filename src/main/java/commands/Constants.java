package commands;

public interface Constants {
    String EMAIL = "email";
    String EMPTY_STRING = "";
    String PASSWORD = "password";
    String USER = "user";
    String ID = "id";
    String DATE = "date";
    String SEX = "sex";
    String LAST_NAME = "lastName";
    String FIRST_NAME = "firstName";
    String MEDICAL_CARD = "medicalCard";
    String ERROR = "error";
    String UNDEFINED = "undefined";
    String NURSES = "nurses";
    String FREE_NURSES = "freeNurses";
    String NUMBER_OF_PATIENT = "numberOfPatient";
    String DOCTORS = "doctors";
    String TELEPHONE_NUMBER = "telephoneNumber";
    String BIRTH_DATE = "birthDate";
    String DOCTOR = "doctor";
    String PATIENTS = "patients";
    String PATIENT = "patient";
    String DOCTOR_FULL_NAME = "doctorFullName";
    String QUALIFICATION = "qualification";
    String ASSIGNMENTS = "assignments";
    String ASSIGNMENT = "assignment";








    String PAGE_LOGIN = "/login.jsp";
    String PAGE_ACCESS_DENIED = "/api/access_denied.jsp";
    String PAGE_REGISTRATION = "/registration.jsp";
    String PAGE_SUCCESS= "/api/success.jsp";
    String PAGE_ADD_ASSIGNMENT= "/doctor/addAssignment.jsp";
    String PAGE_REGISTRATION_PATIENT= "/admin/registerPatient.jsp";


    String HREF_LIST_OF_NURSES = "/api/admin/nurses";
    String HREF_LIST_OF_DOCTORS = "/api/admin/doctors";
    String HREF_LIST_OF_PATIENTS = "/api/admin/patients";






    String PREFIX_REDIRECT = "redirect:";

    String REDIRECT_ADMIN_SUCCESS = PREFIX_REDIRECT+"/admin/success.jsp";
    String REDIRECT_ADMIN_UNDEFINED = PREFIX_REDIRECT+"/admin/users.jsp";
    String REDIRECT_ADMIN_NURSES = PREFIX_REDIRECT+"/admin/nurses.jsp";
    String REDIRECT_ADMIN_DOCTORS = PREFIX_REDIRECT+"/admin/doctors.jsp";
    String REDIRECT_ADMIN_PATIENTS = PREFIX_REDIRECT+"/admin/patients.jsp";
    String REDIRECT_ADMIN_PATIENT_INFO = PREFIX_REDIRECT+"/admin/patientInfo.jsp";
    String REDIRECT_ADMIN_REGISTER_DOCTOR = PREFIX_REDIRECT+"/admin/registerDoctor.jsp";
    String REDIRECT_ADMIN_REGISTER_PATIENT = PREFIX_REDIRECT+PAGE_REGISTRATION_PATIENT;
    String REDIRECT_ADMIN_MEDICAL_CARD = PREFIX_REDIRECT+"/admin/medicalCard.jsp";
    String REDIRECT_ADMIN_ASSIGNMENT_INFO = PREFIX_REDIRECT+"/admin/assignmentInfo.jsp";


    String REDIRECT_DOCTOR_SUCCESS = PREFIX_REDIRECT+"/doctor/success.jsp";
    String REDIRECT_DOCTOR_PATIENTS = PREFIX_REDIRECT+"/doctor/patients.jsp";
    String REDIRECT_DOCTOR_PATIENT_INFO = PREFIX_REDIRECT+"/doctor/patientInfo.jsp";
    String REDIRECT_DOCTOR_MEDICAL_CARD = PREFIX_REDIRECT+"/doctor/medicalCard.jsp";
    String REDIRECT_DOCTOR_ASSIGNMENT_INFO = PREFIX_REDIRECT+"/doctor/assignmentInfo.jsp";
    String REDIRECT_DOCTOR_ASSIGNMENT_INFO_ID = PREFIX_REDIRECT+"redirect:/doctor/assignmentInfo?id=";
    String REDIRECT_DOCTOR_MEDICAL_CARD_ID = PREFIX_REDIRECT+"redirect:/doctor/medicalCard?id=";
    String REDIRECT_DOCTOR_PATIENTS_HREF = PREFIX_REDIRECT+"/api/doctor/patients";



    String REDIRECT_NURSE_SUCCESS = PREFIX_REDIRECT+"/nurse/success.jsp";
    String REDIRECT_NURSE_ASSIGNMENT_INFO_ID = PREFIX_REDIRECT+"redirect:/nurse/assignmentInfo?id=";
    String REDIRECT_NURSE_MEDICAL_CARD = PREFIX_REDIRECT+"/nurse/medicalCard.jsp";
    String REDIRECT_NURSE_ASSIGNMENT_INFO = PREFIX_REDIRECT+"/nurse/assignmentInfo.jsp";
    String REDIRECT_NURSE_PATIENTS = PREFIX_REDIRECT+"/nurse/patients.jsp";
    String REDIRECT_NURSE_MEDICAL_CARD_ID = PREFIX_REDIRECT+"redirect:/nurse/medicalCard?id=";
    String REDIRECT_NURSE_PATIENT_INFO = PREFIX_REDIRECT+"/nurse/patientInfo.jsp";

    String REDIRECT_INDEX = PREFIX_REDIRECT+"/index.jsp";
    String REDIRECT_UNDEFINE_SUCCESS= PREFIX_REDIRECT+"/undefine/success.jsp";
    String REDIRECT_LOGIN= PREFIX_REDIRECT+PAGE_LOGIN;




    String PARAMETER_TYPE = "type";
    String PARAMETER_SEX= "sex";
    String PARAMETER_ID = "id";
    String PARAMETER_DATE = "date";
    String PARAMETER_NAME = "name";
    String PARAMETER_NOTES = "notes";
    String PARAMETER_QUANTITY = "quantity";
    String PARAMETER_CURRENT_DIAGNOSIS = "currentDiagnosis";
    String PARAMETER_FINAL_DIAGNOSIS = "finalDiagnosis";


    String ATTRIBUTE_NO_OF_PAGES = "noOfPages";
    String ATTRIBUTE_CURRENT_PAGE = "currentPage";
    String ATTRIBUTE_SORT_BY = "sortBy";
    String ATTRIBUTE_PAGE = "page";
}
