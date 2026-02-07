package xyz.manojraw.ecommerce.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionController {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<APIError> handleCustomerNotFoundException(ApiException ex) {
        APIError apiError = new APIError(
                ex.getMessage(),
                ex.getErrorCode(),
                ex.getStatus()
        );
        return new ResponseEntity<>(apiError, apiError.status());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIError> handleGenericException(Exception ex) {
        APIError apiError = new APIError(
                ex.getMessage(),
                "INTERNAL_SERVER_ERROR",
                org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
        );
        return new ResponseEntity<>(apiError, apiError.status());
    }
}
