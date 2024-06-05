package com.clubnautico.ClubNautico.services;

import com.clubnautico.ClubNautico.entities.Salida;
import com.clubnautico.ClubNautico.repositories.SalidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SalidaService {

    @Autowired
    private SalidaRepository salidaRepository;

    public List<Salida> getAllSalidas() {
        return salidaRepository.findAll();
    }

    public Optional<Salida> getSalidaById(Long id) {
        return salidaRepository.findById(id);
    }

    public Salida saveSalida(Salida salida) {
        return salidaRepository.save(salida);
    }

    public void deleteSalida(Long id) {
        salidaRepository.deleteById(id);
    }
}
