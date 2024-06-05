package com.clubnautico.ClubNautico.controllers;

import com.clubnautico.ClubNautico.entities.Barco;
import com.clubnautico.ClubNautico.services.BarcoService;
import com.clubnautico.ClubNautico.services.SocioService;
import com.clubnautico.ClubNautico.exceptions.BarcoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Clase de controlador para manejar solicitudes HTTP relacionadas con Barco.
 */
@RestController
@RequestMapping("/barco")
public class BarcoController {

    @Autowired
    private BarcoService barcoService;

    /**
     * Recupera una lista de todas las entidades Barco.
     *
     * @return una lista de todas las entidades Barco
     */
    @GetMapping
    public List<Barco> getAllBarcos() {
        return barcoService.getAllBarcos();
    }

    /**
     * Recupera una entidad Barco por su ID.
     *
     * @param id el ID de la entidad Barco a recuperar
     * @return la entidad Barco, si se encuentra, o una respuesta 404 Not Found
     */
    @GetMapping("/{id}")
    public Barco getBarcoById(@PathVariable Long id) {
        return barcoService.getBarcoById(id).orElseThrow(() -> new BarcoException("Barco no encontrado con ID: " + id));
    }

    /**
     * Crea una nueva entidad Barco.
     *
     * @param barco la entidad Barco a crear
     * @return la entidad Barco creada
     */
    @PostMapping
    public Barco createBarco(@RequestBody Barco barco) {
        return barcoService.saveBarco(barco);
    }

    /**
     * Actualiza una entidad Barco existente.
     *
     * @param id el ID de la entidad Barco a actualizar
     * @param barco la entidad Barco actualizada
     * @return la entidad Barco actualizada, o una respuesta 404 Not Found si no se encuentra la entidad Barco
     */
    @PutMapping("/{id}")
    public ResponseEntity<Barco> updateBarco(@PathVariable Long id, @RequestBody Barco barco) {
        Optional<Barco> optionalBarco = barcoService.getBarcoById(id);
        if (optionalBarco.isPresent()) {
            barco.setBarco_id(id);
            Barco updatedBarco = barcoService.saveBarco(barco);
            return ResponseEntity.ok(updatedBarco);
        } else {
            throw new BarcoException("Barco no encontrado con ID: " + id);
        }
    }

    /**
     * Elimina una entidad Barco por su ID.
     *
     * @param id el ID de la entidad Barco a eliminar
     * @return una respuesta 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBarco(@PathVariable Long id) {
        Optional<Barco> optionalBarco = barcoService.getBarcoById(id);
        if (optionalBarco.isPresent()) {
            barcoService.deleteBarco(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new BarcoException("Barco no encontrado con ID: " + id);
        }
    }
}
