package notes.exception;

public class ResourceException extends RuntimeException {
    private static final String ERR_NOT_FOUND = "Service error, resources not found!";

    public ResourceException(String message) {
        super(message);
    }

    public ResourceException(){
        super();
        System.out.println(ERR_NOT_FOUND);
    }
}
