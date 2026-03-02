package org.example.paymentservice.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ApiExceptionHandler {

    @ExceptionHandler(value = {
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class,
    })
    public ResponseEntity<ExceptionMsg> handleValidationException(final BindException e) {
        log.error("Validation error: {}", e.getBindingResult().getFieldErrors());
        final var badRequest = HttpStatus.BAD_REQUEST;
        String message = e.getBindingResult().getFieldError() != null
                ? e.getBindingResult().getFieldError().getDefaultMessage()
                : "Validation failed";

        return new ResponseEntity<>(
                ExceptionMsg.builder()
                        .msg(message)
                        .httpStatus(badRequest)
                        .timestamp(ZonedDateTime.now(ZoneId.systemDefault()))
                        .build(), badRequest);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ExceptionMsg> handlePaymentNotFoundException(final PaymentNotFoundException e) {
        log.error("Payment not found: {}", e.getMessage());
        final var notFound = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(
                ExceptionMsg.builder()
                        .msg(e.getMessage())
                        .httpStatus(notFound)
                        .timestamp(ZonedDateTime.now(ZoneId.systemDefault()))
                        .build(), notFound);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ExceptionMsg> handleIllegalStateException(final IllegalStateException e) {
        log.error("Illegal state: {}", e.getMessage());
        final var badRequest = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(
                ExceptionMsg.builder()
                        .msg(e.getMessage())
                        .httpStatus(badRequest)
                        .timestamp(ZonedDateTime.now(ZoneId.systemDefault()))
                        .build(), badRequest);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionMsg> handleGlobalException(final Exception e) {
        log.error("Internal server error: {}", e.getMessage(), e);
        final var internalError = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(
                ExceptionMsg.builder()
                        .msg("An internal error occurred. Please try again later.")
                        .httpStatus(internalError)
                        .timestamp(ZonedDateTime.now(ZoneId.systemDefault()))
                        .build(), internalError);
    }
}
