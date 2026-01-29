package xyz.manojraw.ecommerce.common.exception;

import org.springframework.http.HttpStatus;

public record APIError(
        String message,
        String errorCode,
        HttpStatus status
) {
}
