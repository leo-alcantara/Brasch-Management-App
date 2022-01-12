package the.bug.tech.brasch_management_system.dto;

public class Success<T> implements Result<T> {

    public static final Result<Void> empty = new Success<>(null);
    private final T data;

    public Success(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Success{" +
                "data=" + data +
                '}';
    }
}
