package com.optimagrowth.organization.exception;

import lombok.Getter;

@Getter
public class OGException extends RuntimeException {
    private final OGError ogError;

    public OGException(OGError ogError, Throwable cause) {
        super(ogError.getMessage(), cause);
        this.ogError = ogError;
    }

    public Dto toDto() {
        return new Dto(ogError.getCode(), ogError.getMessage());
    }

    public record Dto(String code, String message) {}
}
