package com.clubnautico.ClubNautico.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase global para manejar excepciones en los controladores.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja excepciones de tipo SocioException.
     *
     * @param ex la excepción lanzada
     * @param request el objeto de la solicitud web
     * @return una respuesta con detalles del error y estado HTTP 400
     */
    @ExceptionHandler(SocioException.class)
    public ResponseEntity<?> handleSocioException(SocioException ex, WebRequest request) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja excepciones de tipo BarcoException.
     *
     * @param ex la excepción lanzada
     * @param request el objeto de la solicitud web
     * @return una respuesta con detalles del error y estado HTTP 400
     */
    @ExceptionHandler(BarcoException.class)
    public ResponseEntity<?> handleBarcoException(BarcoException ex, WebRequest request) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja excepciones de tipo SalidaException.
     *
     * @param ex la excepción lanzada
     * @param request el objeto de la solicitud web
     * @return una respuesta con detalles del error y estado HTTP 400
     */
    @ExceptionHandler(SalidaException.class)
    public ResponseEntity<?> handleSalidaException(SalidaException ex, WebRequest request) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja excepciones de tipo PatronException.
     *
     * @param ex la excepción lanzada
     * @param request el objeto de la solicitud web
     * @return una respuesta con detalles del error y estado HTTP 400
     */
    @ExceptionHandler(PatronException.class)
    public ResponseEntity<?> handlePatronException(PatronException ex, WebRequest request) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja excepciones de tipo AmarreException.
     *
     * @param ex la excepción lanzada
     * @param request el objeto de la solicitud web
     * @return una respuesta con detalles del error y estado HTTP 400
     */
    @ExceptionHandler(AmarreException.class)
    public ResponseEntity<?> handleAmarreException(AmarreException ex, WebRequest request) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja excepciones globales.
     *
     * @param ex la excepción lanzada
     * @param request el objeto de la solicitud web
     * @return una respuesta con detalles del error y estado HTTP 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", "Error interno del servidor");
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
