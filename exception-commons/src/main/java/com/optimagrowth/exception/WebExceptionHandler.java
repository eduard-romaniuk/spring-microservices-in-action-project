package com.optimagrowth.exception;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebExceptionHandler {
    @ExceptionHandler(OGException.class)
    public ResponseEntity<OGErrorResponse> ogException(OGException exception) {
        return new ResponseEntity<>(
                new OGErrorResponse(
                        exception.getCode(),
                        exception.getMessage()
                ),
                exception.getHttpStatus()
        );
    }

    @ExceptionHandler(CallNotPermittedException.class)
    public ResponseEntity<OGErrorResponse> cbException(CallNotPermittedException exception) {
        return new ResponseEntity<>(
                new OGErrorResponse(
                        "CB",
                        exception.getMessage()
                ),
                HttpStatus.SERVICE_UNAVAILABLE
        );
    }
}
