package com.clubnautico.ClubNautico.controllers;

import com.clubnautico.ClubNautico.entities.Amarre;
import com.clubnautico.ClubNautico.exceptions.AmarreException;
import com.clubnautico.ClubNautico.services.AmarreService;
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
 * Clase de controlador para manejar solicitudes HTTP relacionadas con Amarre.
 */
@RestController
@RequestMapping("/amarre")
public class AmarreController {

    @Autowired
    private AmarreService amarreService;

    /**
     * Recupera una lista de todas las entidades Amarre.
     *
     * @return una lista de todas las entidades Amarre
     */
    @GetMapping
    public List<Amarre> getAllAmarres() {
        return amarreService.getAllAmarres();
    }

    /**
     * Recupera una entidad Amarre por su ID.
     *
     * @param id el ID de la entidad Amarre a recuperar
     * @return la entidad Amarre, si se encuentra, o una respuesta 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Amarre> getAmarreById(@PathVariable Long id) {
        Optional<Amarre> amarre = amarreService.getAmarreById(id);
        return amarre.map(ResponseEntity::ok).orElseThrow(() -> new AmarreException("Amarre no encontrada con ID: " + id));
    }

    /**
     * Crea una nueva entidad Amarre.
     *
     * @param amarre la entidad Amarre a crear
     * @param bindingResult los resultados de la validación del cuerpo de la solicitud
     * @return la entidad Amarre creada o una respuesta de error si la validación falla
     */
    @PostMapping
    public ResponseEntity<?> createAmarre(@RequestBody Amarre amarre, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Amarre savedAmarre = amarreService.saveAmarre(amarre);
        return new ResponseEntity<>(savedAmarre, HttpStatus.CREATED);
    }

    /**
     * Actualiza una entidad Amarre existente.
     *
     * @param id el ID de la entidad Amarre a actualizar
     * @param amarre la entidad Amarre actualizada
     * @param bindingResult los resultados de la validación del cuerpo de la solicitud
     * @return la entidad Amarre actualizada, una respuesta de error si la validación falla, o una respuesta 404 Not Found si no se encuentra la entidad Amarre
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAmarre(@PathVariable Long id, @RequestBody Amarre amarre, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Optional<Amarre> existingAmarre = amarreService.getAmarreById(id);
        if (existingAmarre.isPresent()) {
            amarre.setAmarre_id(id);
            Amarre updatedAmarre = amarreService.saveAmarre(amarre);
            return ResponseEntity.ok(updatedAmarre);
        } else {
            throw new AmarreException("Amarre no encontrado con ID: " + id);
        }
    }

    /**
     * Elimina una entidad Amarre por su ID.
     *
     * @param id el ID de la entidad Amarre a eliminar
     * @return una respuesta 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAmarre(@PathVariable Long id) {
        Optional<Amarre> optionalAmarre = amarreService.getAmarreById(id);
        if (optionalAmarre.isPresent()) {
            amarreService.deleteAmarre(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new AmarreException("Amarre no encontrada con ID: " + id);
        }
    }
}
