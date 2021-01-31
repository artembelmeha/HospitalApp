package commands;

public interface Constants {
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String USER = "user";
    public static final String LAST_NAME = "lastName";
    public static final String FIRST_NAME = "firstName";
    public static final String ERROR = "error";
    public static final String UNDEFINED = "undefined";
    public static final String NURSES = "nurses";
    public static final String DOCTORS = "doctors";








    public static final String PAGE_LOGIN = "/login.jsp";
    public static final String PAGE_REGISTRATION = "/registration.jsp";






    public static final String PREFIX_REDIRECT = "redirect:";

    public static final String REDIRECT_ADMIN_SUCCESS = PREFIX_REDIRECT+"/admin/success.jsp";
    public static final String REDIRECT_ADMIN_UNDEFINED = PREFIX_REDIRECT+"/admin/users.jsp";
    public static final String REDIRECT_ADMIN_NURSES = PREFIX_REDIRECT+"/admin/nurses.jsp";
    public static final String REDIRECT_ADMIN_DOCTORS = PREFIX_REDIRECT+"/admin/doctors.jsp";

    public static final String REDIRECT_DOCTOR_SUCCESS = PREFIX_REDIRECT+"/doctor/success.jsp";
    public static final String REDIRECT_INDEX = PREFIX_REDIRECT+"/index.jsp";
}
