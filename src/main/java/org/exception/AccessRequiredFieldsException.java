package org.exception;

import lombok.Getter;

@Getter
public class AccessRequiredFieldsException extends Exception {
    private final String code = "ARF_404";

    public AccessRequiredFieldsException(String message) {
        super(message);
    }
}
