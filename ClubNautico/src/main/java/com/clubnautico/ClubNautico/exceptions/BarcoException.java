package com.clubnautico.ClubNautico.exceptions;

public class BarcoException extends RuntimeException {

    public BarcoException(String message) {
        super(message);
    }

    public BarcoException(String message, Throwable cause) {
        super(message, cause);
    }
}
