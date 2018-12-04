package org.exception;

public class AccessRequiredFieldsException extends Exception {
    private final String code = "ARF_404";

    public AccessRequiredFieldsException(String message) {
        super(message);
    }
}
