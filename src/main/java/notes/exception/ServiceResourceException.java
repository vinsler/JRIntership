package notes.exception;

public class ServiceResourceException extends RuntimeException {

    public ServiceResourceException(String message, Exception e){
        super(message, e);
    }

    public ServiceResourceException(String message) {
        super(message);
    }
}
