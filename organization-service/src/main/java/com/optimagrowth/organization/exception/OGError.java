package com.optimagrowth.organization.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum OGError {
    LICENSE_NOT_FOUND(HttpStatus.NOT_FOUND, "OS1", "Organization not found");
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    public OGException toException() {
        return new OGException(this, null);
    }
}
