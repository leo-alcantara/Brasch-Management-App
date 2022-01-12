package the.bug.tech.brasch_management_system.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import the.bug.tech.brasch_management_system.dto.Result;
import the.bug.tech.brasch_management_system.dto.Results;
import the.bug.tech.brasch_management_system.dto.Success;

public class Responses {

    public static <T> ResponseEntity<Result<T>> ok(T body) {
        return ResponseEntity.ok(new Success<>(body));
    }

    public static <T> ResponseEntity<Result<T>> internalServerError() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Results.internalServerError());
    }

    public static <T> ResponseEntity<Result<T>> unauthorized() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Results.unauthorized());
    }

    public static <T> ResponseEntity<Result<T>> tooLongInput(String message) {
        return ResponseEntity.badRequest().body(Results.tooLongInput(message));
    }

    public static <T> ResponseEntity<Result<T>> invalidTimeRange() {
        return ResponseEntity.badRequest().body(Results.invalidTimeRange());
    }

    public static <T> ResponseEntity<Result<T>> invalidArgument(String message) {
        return ResponseEntity.badRequest().body(Results.invalidArgument(message));
    }
}
