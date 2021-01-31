package commands.authentication;

public interface Constants {
    public final static String EMAIL = "email";
    public final static String PASSWORD = "password";
    public final static String USER = "user";
    public final static String LAST_NAME = "lastName";
    public final static String FIRST_NAME = "firstName";
    public final static String ERROR = "error";








    public final static String PAGE_LOGIN = "/login.jsp";
    public final static String PAGE_REGISTRATION = "/registration.jsp";






    public final static String PREFIX_REDIRECT = "redirect:";
    public final static String REDIRECT_ADMIN_SUCCESS = PREFIX_REDIRECT+"/admin/success.jsp";
    public final static String REDIRECT_DOCTOR_SUCCESS = PREFIX_REDIRECT+"/doctor/success.jsp";
    public final static String REDIRECT_INDEX = PREFIX_REDIRECT+"/index.jsp";
}
