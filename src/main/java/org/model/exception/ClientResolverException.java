package org.model.exception;

import lombok.Getter;

@Getter
public class ClientResolverException extends Exception {
    private static final String
            EXCEPTION_MISSING_MASSAGE = "Configuration not found:";

    public ClientResolverException(String message) {
        super(EXCEPTION_MISSING_MASSAGE + "\n" + message);
    }
}
