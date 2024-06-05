package com.clubnautico.ClubNautico.services;

import com.clubnautico.ClubNautico.entities.Amarre;
import com.clubnautico.ClubNautico.entities.Barco;
import com.clubnautico.ClubNautico.entities.Patron;
import com.clubnautico.ClubNautico.entities.Socio;
import com.clubnautico.ClubNautico.repositories.AmarreRepository;
import com.clubnautico.ClubNautico.repositories.BarcoRepository;
import com.clubnautico.ClubNautico.repositories.PatronRepository;
import com.clubnautico.ClubNautico.repositories.SocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class BarcoService {

    @Autowired
    private BarcoRepository barcoRepository;

    @Autowired
    private AmarreRepository amarreRepository;

    @Autowired
    private SocioRepository socioRepository;

    @Autowired
    private PatronRepository patronRepository;

    @Transactional
    public Barco saveBarco(Barco barco) {

        // Guardar y asociar Amarre si es necesario
        Amarre amarre = barco.getAmarre();
        if (amarre != null) {
            if (amarre.getAmarre_id() != null) {
                Optional<Amarre> existingAmarre = amarreRepository.findById(amarre.getAmarre_id());
                if (existingAmarre.isPresent()) {
                    barco.setAmarre(existingAmarre.get());
                } else {
                    throw new IllegalArgumentException("El amarre con el ID proporcionado no existe.");
                }
            } else {
                amarre = amarreRepository.save(amarre);
                barco.setAmarre(amarre);
            }
        }

        // Guardar y asociar Patron si es necesario
        Patron patron = barco.getPatron();
        if (patron != null) {
            if (patron.getPatron_id() != null) {
                Optional<Patron> existingPatron = patronRepository.findById(patron.getPatron_id());
                if (existingPatron.isPresent()) {
                    barco.setPatron(existingPatron.get());
                } else {
                    throw new IllegalArgumentException("El patrón con el ID proporcionado no existe.");
                }
            } else {
                patron = patronRepository.save(patron);
                barco.setPatron(patron);
            }
        }

        // Guardar Barco
        Barco savedBarco = barcoRepository.save(barco);
        return savedBarco;
    }


public List<Barco> getAllBarcos() {
    return barcoRepository.findAll();
}

public Optional<Barco> getBarcoById(Long id) {
    return barcoRepository.findById(id);
}


public void deleteBarco(Long id) {
    barcoRepository.deleteById(id);
}

    public List<Barco> findByAmarreId(Long amarreId){
        List<Barco> listaBarcos = new LinkedList<>();
        for (Barco barco : barcoRepository.findAll()){
            if (barco.getAmarre()!= null) {
                if (Objects.equals(barco.getAmarre().getAmarre_id(), amarreId)) {
                    listaBarcos.add(barco);
                    break; // Termina el bucle después de encontrar el amarre correspondiente
                }
            }
        }
        return listaBarcos;
    }
}