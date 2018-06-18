package notes.exception;

public class ServiceException extends RuntimeException {

    public ServiceException(String message, Exception e){
        super(message, e);
    }

    public ServiceException(String message) {
        super(message);
    }
}
