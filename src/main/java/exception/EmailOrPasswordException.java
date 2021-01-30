package exception;

public class EmailOrPasswordException extends RuntimeException {
    public EmailOrPasswordException() {
    }

    public EmailOrPasswordException(String message) {
        super(message);
    }
}
