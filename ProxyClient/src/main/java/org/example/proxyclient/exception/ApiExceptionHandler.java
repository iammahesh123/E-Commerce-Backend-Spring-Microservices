package org.example.proxyclient.exception;

import feign.FeignException;
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
            FeignException.FeignClientException.class,
            FeignException.FeignServerException.class,
            FeignException.class
    })
    public ResponseEntity<ExceptionMsg> handleFeignException(final FeignException e) {
        log.error("Feign exception: {}", e.getMessage());
        final HttpStatus status = HttpStatus.valueOf(e.status());
        return new ResponseEntity<>(
                ExceptionMsg.builder()
                        .msg("External service error: " + e.getMessage())
                        .httpStatus(status)
                        .timestamp(ZonedDateTime.now(ZoneId.systemDefault()))
                        .build(), status);
    }

    @ExceptionHandler(value = {
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class
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

    @ExceptionHandler(UserObjectNotFoundException.class)
    public ResponseEntity<ExceptionMsg> handleUserNotFoundException(final UserObjectNotFoundException e) {
        log.error("User not found: {}", e.getMessage());
        final var notFound = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(
                ExceptionMsg.builder()
                        .msg(e.getMessage())
                        .httpStatus(notFound)
                        .timestamp(ZonedDateTime.now(ZoneId.systemDefault()))
                        .build(), notFound);
    }

    @ExceptionHandler(CredentialNotFoundException.class)
    public ResponseEntity<ExceptionMsg> handleCredentialNotFoundException(final CredentialNotFoundException e) {
        log.error("Credential not found: {}", e.getMessage());
        final var notFound = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(
                ExceptionMsg.builder()
                        .msg(e.getMessage())
                        .httpStatus(notFound)
                        .timestamp(ZonedDateTime.now(ZoneId.systemDefault()))
                        .build(), notFound);
    }

    @ExceptionHandler(VerificationTokenNotFoundException.class)
    public ResponseEntity<ExceptionMsg> handleVerificationTokenNotFoundException(final VerificationTokenNotFoundException e) {
        log.error("Verification token not found: {}", e.getMessage());
        final var notFound = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(
                ExceptionMsg.builder()
                        .msg(e.getMessage())
                        .httpStatus(notFound)
                        .timestamp(ZonedDateTime.now(ZoneId.systemDefault()))
                        .build(), notFound);
    }

    @ExceptionHandler(FavouriteNotFoundException.class)
    public ResponseEntity<ExceptionMsg> handleFavouriteNotFoundException(final FavouriteNotFoundException e) {
        log.error("Favourite not found: {}", e.getMessage());
        final var notFound = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(
                ExceptionMsg.builder()
                        .msg(e.getMessage())
                        .httpStatus(notFound)
                        .timestamp(ZonedDateTime.now(ZoneId.systemDefault()))
                        .build(), notFound);
    }

    @ExceptionHandler(IllegalAuthenticationCredentialsException.class)
    public ResponseEntity<ExceptionMsg> handleIllegalAuthenticationCredentialsException(final IllegalAuthenticationCredentialsException e) {
        log.error("Authentication error: {}", e.getMessage());
        final var unauthorized = HttpStatus.UNAUTHORIZED;
        return new ResponseEntity<>(
                ExceptionMsg.builder()
                        .msg(e.getMessage())
                        .httpStatus(unauthorized)
                        .timestamp(ZonedDateTime.now(ZoneId.systemDefault()))
                        .build(), unauthorized);
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
