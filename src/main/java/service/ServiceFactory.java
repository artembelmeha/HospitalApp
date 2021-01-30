package service;

public class ServiceFactory {
    private static ServiceFactory instance = new ServiceFactory();

    private UserService userService;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }
}
