package org.exception;

public class MetaDateAnnotationNotFoundException extends Exception{
    private final String code = "MDANF_404";

    public MetaDateAnnotationNotFoundException(String message) {
        super(message);
    }
}
