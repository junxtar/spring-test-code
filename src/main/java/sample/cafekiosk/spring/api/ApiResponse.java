package sample.cafekiosk.spring.api;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;

@Getter
@NoArgsConstructor
public class ApiResponse <T>{

    private int code;
    private HttpStatus status;
    private String message;
    private T data;

    @Builder
    public ApiResponse(HttpStatus status, String message, T data) {
        this.code = status.value();
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public static <T> ApiResponse<T> of(HttpStatus status, String message,  T data) {
        return new ApiResponse<>(status, message, data);
    }

    public static <T> ApiResponse<T> of(HttpStatus status, T data) {
        return of(status, status.name(), data);
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(OK, OK.name(), data);
    }
}

