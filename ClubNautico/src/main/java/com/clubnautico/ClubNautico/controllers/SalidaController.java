package com.clubnautico.ClubNautico.controllers;

import com.clubnautico.ClubNautico.entities.Barco;
import com.clubnautico.ClubNautico.entities.Salida;
import com.clubnautico.ClubNautico.services.BarcoService;
import com.clubnautico.ClubNautico.services.SalidaService;
import com.clubnautico.ClubNautico.exceptions.SalidaException;
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
 * Clase de controlador para manejar solicitudes HTTP relacionadas con Salida.
 */
@RestController
@RequestMapping("/salida")
public class SalidaController {

    @Autowired
    private SalidaService salidaService;

    @Autowired
    private BarcoService barcoService;

    /**
     * Recupera una lista de todas las entidades Salida.
     *
     * @return una lista de todas las entidades Salida
     */
    @GetMapping
    public List<Salida> getAllSalidas() {
        return salidaService.getAllSalidas();
    }

    /**
     * Recupera una entidad Salida por su ID.
     *
     * @param id el ID de la entidad Salida a recuperar
     * @return la entidad Salida, si se encuentra, o una respuesta 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Salida> getSalidaById(@PathVariable Long id) {
        Optional<Salida> salida = salidaService.getSalidaById(id);
        return salida.map(ResponseEntity::ok).orElseThrow(() -> new SalidaException("Salida no encontrada con ID: " + id));
    }

    /**
     * Crea una nueva entidad Salida.
     *
     * @param salida la entidad Salida a crear
     * @param bindingResult el resultado de la validación
     * @return la entidad Salida creada, o una respuesta 400 Bad Request si hay errores de validación
     */
    @PostMapping
    public ResponseEntity<?> createSalida(@RequestBody Salida salida, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Barco barco = barcoService.getBarcoById(salida.getBarco().getBarco_id())
                .orElseThrow(() -> new SalidaException("Barco no encontrado"));
        salida.setBarco(barco);
        Salida savedSalida = salidaService.saveSalida(salida);
        return new ResponseEntity<>(savedSalida, HttpStatus.CREATED);
    }

    /**
     * Actualiza una entidad Salida existente.
     *
     * @param id el ID de la entidad Salida a actualizar
     * @param salida la entidad Salida actualizada
     * @param bindingResult el resultado de la validación
     * @return la entidad Salida actualizada, o una respuesta 404 Not Found si no se encuentra la entidad Salida
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSalida(@PathVariable Long id, @RequestBody Salida salida, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Optional<Salida> existingSalida = salidaService.getSalidaById(id);
        if (existingSalida.isPresent()) {
            Barco barco = barcoService.getBarcoById(salida.getBarco().getBarco_id())
                    .orElseThrow(() -> new SalidaException("Barco no encontrado"));
            salida.setSalida_id(id);
            salida.setBarco(barco);
            Salida updatedSalida = salidaService.saveSalida(salida);
            return ResponseEntity.ok(updatedSalida);
        } else {
            throw new SalidaException("Salida no encontrada con ID: " + id);
        }
    }

    /**
     * Elimina una entidad Salida por su ID.
     *
     * @param id el ID de la entidad Salida a eliminar
     * @return una respuesta 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalida(@PathVariable Long id) {
        Optional<Salida> optionalSalida = salidaService.getSalidaById(id);
        if (optionalSalida.isPresent()) {
            salidaService.deleteSalida(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new SalidaException("Salida no encontrada con ID: " + id);
        }
    }
}
