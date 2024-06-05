package com.clubnautico.ClubNautico.controllers;

import com.clubnautico.ClubNautico.entities.Socio;
import com.clubnautico.ClubNautico.exceptions.SocioException;
import com.clubnautico.ClubNautico.services.SocioService;
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
 * Clase de controlador para manejar solicitudes HTTP relacionadas con Socio.
 */
@RestController
@RequestMapping("/socio")
public class SocioController {

    @Autowired
    private SocioService socioService;

    /**
     * Recupera una lista de todas las entidades Socio.
     *
     * @return una lista de todas las entidades Socio
     */
    @GetMapping
    public List<Socio> getAllSocios() {
        return socioService.getAllSocios();
    }

    /**
     * Recupera una entidad Socio por su ID.
     *
     * @param id el ID de la entidad Socio a recuperar
     * @return la entidad Socio, si se encuentra, o una respuesta 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Socio> getSocioById(@PathVariable Long id) {
        Optional<Socio> socio = socioService.getSocioById(id);
        return socio.map(ResponseEntity::ok).orElseThrow(() -> new SocioException("Socio no encontrado con ID: " + id));
    }

    /**
     * Crea una nueva entidad Socio.
     *
     * @param socio la entidad Socio a crear
     * @param bindingResult los resultados de la validación del cuerpo de la solicitud
     * @return la entidad Socio creada o una respuesta de error si la validación falla
     */
    @PostMapping
    public ResponseEntity<?> createSocio(@RequestBody Socio socio, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            throw new SocioException("Errores de validación: " + errors.toString());
        }

        Socio savedSocio = socioService.saveSocio(socio);
        return new ResponseEntity<>(savedSocio, HttpStatus.CREATED);
    }

    /**
     * Actualiza una entidad Socio existente.
     *
     * @param id el ID de la entidad Socio a actualizar
     * @param socio la entidad Socio actualizada
     * @param bindingResult los resultados de la validación del cuerpo de la solicitud
     * @return la entidad Socio actualizada, una respuesta de error si la validación falla, o una respuesta 404 Not Found si no se encuentra la entidad Socio
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSocio(@PathVariable Long id, @RequestBody Socio socio, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            throw new SocioException("Errores de validación: " + errors.toString());
        }

        Optional<Socio> existingSocio = socioService.getSocioById(id);
        if (existingSocio.isPresent()) {
            socio.setSocio_id(id);
            Socio updatedSocio = socioService.saveSocio(socio);
            return ResponseEntity.ok(updatedSocio);
        } else {
            throw new SocioException("Socio no encontrado con ID: " + id);
        }
    }

    /**
     * Elimina una entidad Socio por su ID.
     *
     * @param id el ID de la entidad Socio a eliminar
     * @return una respuesta 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSocio(@PathVariable Long id) {
        if (!socioService.getSocioById(id).isPresent()) {
            throw new SocioException("Socio no encontrado con ID: " + id);
        }
        socioService.deleteSocio(id);
        return ResponseEntity.noContent().build();
    }
}
