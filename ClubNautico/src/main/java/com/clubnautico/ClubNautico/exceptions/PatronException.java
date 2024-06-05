package com.clubnautico.ClubNautico.exceptions;

public class PatronException extends RuntimeException {

    public PatronException(String message) {
        super(message);
    }

    public PatronException(String message, Throwable cause) {
        super(message, cause);
    }
}
