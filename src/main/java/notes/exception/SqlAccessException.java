package notes.exception;

public class SqlAccessException extends RuntimeException{

    public SqlAccessException(String message, Exception e) {
        super(message, e);
    }

    public SqlAccessException(String message) {
        super(message);
    }
}
