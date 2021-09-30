package com.contact.geo.contactgeo.exception;

public class ZipcodeException extends RuntimeException {
    public static final String MESSAGE = "Error when trying to consult the zip code.";

    public ZipcodeException() {
        super(MESSAGE);
    }
}
