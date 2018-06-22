package notes.exception;

public class ResourceException extends RuntimeException {
    private static final String ERR_NOT_FOUND = "Service error, resources not found!";

    public ResourceException(Exception e) {
        super(e);
        System.out.println(ERR_NOT_FOUND);
    }

    public ResourceException(){
        super();
        System.out.println(ERR_NOT_FOUND);
    }
}
