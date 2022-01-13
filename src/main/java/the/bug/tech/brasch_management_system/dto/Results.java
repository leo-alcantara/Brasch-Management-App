package the.bug.tech.brasch_management_system.dto;

public class Results {

    public static <T> Failure<T> internalServerError() {
        return new Failure<>(1500, "Internal server error");
    }

    public static <T> Failure<T> unauthorized() {
        return new Failure<>(1401, "Unauthorized");
    }

    public static <T> Failure<T> tooLongInput(String message) {
        return new Failure<>(1000, message);
    }

    public static <T> Failure<T> invalidTimeRange() {
        return new Failure<>(1001, "Invalid time range");
    }

    public static <T> Failure<T> invalidArgument(String message) {
        return new Failure<>(1003, message);
    }

    public static <T> Result<T> success(T t) {
        return new Success<>(t);
    }
}
