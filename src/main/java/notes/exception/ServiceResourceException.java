package notes.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message, Exception e){
        super(message, e);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
