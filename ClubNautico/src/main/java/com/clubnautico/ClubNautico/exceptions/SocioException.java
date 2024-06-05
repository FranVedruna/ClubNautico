package com.clubnautico.ClubNautico.exceptions;

public class SocioException extends RuntimeException {

    public SocioException(String message) {
        super(message);
    }

    public SocioException(String message, Throwable cause) {
        super(message, cause);
    }
}
