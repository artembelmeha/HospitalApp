package service;

public class ServiceFactory {
    private static ServiceFactory instance = new ServiceFactory();

    private UserService userService;
    private MedicalCardService cardService;
    private AssignmentService assignmentService;

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

    public AssignmentService getAssignmentService() {
        if (assignmentService == null) {
            assignmentService = new AssignmentService();
        }
        return assignmentService;
    }
}
