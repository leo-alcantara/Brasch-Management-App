package the.bug.tech.brasch_management_system.dto;


import java.util.Collections;
import java.util.List;

public class Failure <T> implements Result<T>{

    private final List<Error> errors;
    private final long timestamp;

    public Failure(int code, String message) {
        this.errors = Collections.singletonList(new Error(code, message));
        this.timestamp = System.currentTimeMillis();
    }

    public Failure (List<Error> errors){
        this.errors = errors;
        this.timestamp = System.currentTimeMillis();
    }

    public List<Error> getErrors(){
        return errors;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public static class Error {
        private final int code;
        private final String message;

        public Error(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
