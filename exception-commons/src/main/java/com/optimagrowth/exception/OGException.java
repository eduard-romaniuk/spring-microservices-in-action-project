package com.optimagrowth.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class OGException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    public OGException(HttpStatus httpStatus, String code, String message, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public OGException(HttpStatus httpStatus, String code, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
