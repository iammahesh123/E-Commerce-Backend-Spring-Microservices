package org.example.productservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
public final class ExceptionMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    private final ZonedDateTime timestamp;
    private final HttpStatus httpStatus;
    private final String msg;
}
