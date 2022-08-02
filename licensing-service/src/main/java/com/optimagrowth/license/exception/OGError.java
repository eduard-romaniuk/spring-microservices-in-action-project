package com.optimagrowth.license.exception;

import com.optimagrowth.exception.OGException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum OGError {
    LICENSE_NOT_FOUND(HttpStatus.NOT_FOUND, "LS1", "License not found");
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    public OGException toException() {
        return new OGException(httpStatus, code, message);
    }
}
