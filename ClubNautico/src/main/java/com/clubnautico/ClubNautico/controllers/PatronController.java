package com.clubnautico.ClubNautico.controllers;

import com.clubnautico.ClubNautico.entities.Patron;
import com.clubnautico.ClubNautico.exceptions.PatronException;
import com.clubnautico.ClubNautico.services.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Clase de controlador para manejar solicitudes HTTP relacionadas con Patron.
 */
@RestController
@RequestMapping("/patron")
public class PatronController {

    @Autowired
    private PatronService patronService;

    /**
     * Recupera una lista de todas las entidades Patron.
     *
     * @return una lista de todas las entidades Patron
     */
    @GetMapping
    public List<Patron> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    /**
     * Recupera una entidad Patron por su ID.
     *
     * @param id el ID de la entidad Patron a recuperar
     * @return la entidad Patron, si se encuentra, o una excepción PatronException si no se encuentra
     */
    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {
        Optional<Patron> patron = patronService.getPatronById(id);
        return patron.map(ResponseEntity::ok).orElseThrow(() -> new PatronException("Patrón no encontrado con ID: " + id));
    }

    /**
     * Crea una nueva entidad Patron.
     *
     * @param patron la entidad Patron a crear
     * @param bindingResult el resultado de la validación del binding
     * @return la entidad Patron creada o un error de validación
     */
    @PostMapping
    public ResponseEntity<?> createPatron(@RequestBody Patron patron, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        Patron savedPatron = patronService.savePatron(patron);
        return new ResponseEntity<>(savedPatron, HttpStatus.CREATED);
    }

    /**
     * Actualiza una entidad Patron existente.
     *
     * @param id el ID de la entidad Patron a actualizar
     * @param patron la entidad Patron actualizada
     * @param bindingResult el resultado de la validación del binding
     * @return la entidad Patron actualizada, o una excepción PatronException si no se encuentra
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatron(@PathVariable Long id, @RequestBody Patron patron, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Optional<Patron> existingPatron = patronService.getPatronById(id);
        if (existingPatron.isPresent()) {
            patron.setPatron_id(id);
            Patron updatedPatron = patronService.savePatron(patron);
            return ResponseEntity.ok(updatedPatron);
        } else {
            throw new PatronException("Patrón no encontrado con ID: " + id);
        }
    }

    /**
     * Elimina una entidad Patron por su ID.
     *
     * @param id el ID de la entidad Patron a eliminar
     * @return una respuesta 204 No Content si se elimina exitosamente, o una excepción PatronException si no se encuentra
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        Optional<Patron> optionalPatron = patronService.getPatronById(id);
        if (optionalPatron.isPresent()) {
            patronService.deletePatron(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new PatronException("Patrón no encontrado con ID: " + id);
        }
    }
}
