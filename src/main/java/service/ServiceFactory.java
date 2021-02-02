package service;

public class ServiceFactory {
    private static ServiceFactory instance = new ServiceFactory();

    private UserService userService;
    private MedicalCardService cardService;

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

    public MedicalCardService getCardService() {
        if(cardService == null) {
            cardService = new MedicalCardService();
        }
        return cardService;
    }
}
