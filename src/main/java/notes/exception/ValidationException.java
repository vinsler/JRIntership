package notes.exception;

import java.util.HashMap;

public class ValidationException extends RuntimeException {

    private static final HashMap<String, String> errorMap = new HashMap<>();

    static {
        errorMap.put("ERR_ID", "Service error, check id!");
        errorMap.put("ERR_USER_ID", "Service error, check user id!");
        errorMap.put("ERR_NAME", "Service error, check name!");
        errorMap.put("ERR_LOGIN", "Service error, check login!");
        errorMap.put("ERR_PASSWORD", "Service error, check password!");
        errorMap.put("ERR_UPDATE", "Service error, check id for update!");
        errorMap.put("ERR_DELETE", "Service error, check id for delete!");
        errorMap.put("ERR_NOT_FOUND", "Service error, resources not found!");
        errorMap.put("ERR_USER_NOT_FOUND", "Service error, User object not found!");
        errorMap.put("ERR_NOTE_NOT_FOUND", "Service error, Note object not found!");
    }

    public ValidationException(String key) {
        super();
        System.out.println(errorMap.get(key));
    }

    public ValidationException(String key, Exception e) {
        super(key, e);
        System.out.println(errorMap.get(key));
    }
}


