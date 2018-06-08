package notes.exception;

/**
 * @author Vladimir Ryazanov (v.ryazanov13@gmail.com)
 */
public class SQLAccessException extends RuntimeException {
    public SQLAccessException(String message, Exception e) {
        super(message, e);
    }

    public SQLAccessException(String message) {
        super(message);
    }
}
