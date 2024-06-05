package com.clubnautico.ClubNautico.exceptions;

public class SalidaException extends RuntimeException {

    public SalidaException(String message) {
        super(message);
    }

    public SalidaException(String message, Throwable cause) {
        super(message, cause);
    }
}
