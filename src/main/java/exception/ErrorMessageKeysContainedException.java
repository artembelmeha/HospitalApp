package exception;

import java.util.List;

public class ErrorMessageKeysContainedException extends RuntimeException {

    private List<String> errorMessageKeys;

    public ErrorMessageKeysContainedException(List<String> errorMessageKeys) {
        this.errorMessageKeys = errorMessageKeys;
    }

}
