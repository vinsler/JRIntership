package notes.exception;

public class ServiceValidationException extends RuntimeException {

    public ServiceValidationException(String message) {
        super(message);
    }

    public ServiceValidationException(String message, Exception e) {
        super(message, e);
    }
}
