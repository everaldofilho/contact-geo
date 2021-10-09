package com.contact.geo.contactgeo.exception;

public class GoogleMapsException extends RuntimeException {
    public static final String MESSAGE = "Error when trying to consult the Google Maps.";

    public GoogleMapsException() {
        super(MESSAGE);
    }
}
