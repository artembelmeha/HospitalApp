package exception;

public class UnknownSqlException extends RuntimeException {

    public UnknownSqlException() {
    }

    public UnknownSqlException(String message) {
        super(message);
    }
}
