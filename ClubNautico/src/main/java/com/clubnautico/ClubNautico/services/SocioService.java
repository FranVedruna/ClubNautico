package com.clubnautico.ClubNautico.services;

import com.clubnautico.ClubNautico.entities.Amarre;
import com.clubnautico.ClubNautico.entities.Barco;
import com.clubnautico.ClubNautico.entities.Patron;
import com.clubnautico.ClubNautico.entities.Socio;
import com.clubnautico.ClubNautico.repositories.AmarreRepository;
import com.clubnautico.ClubNautico.repositories.BarcoRepository;
import com.clubnautico.ClubNautico.repositories.PatronRepository;
import com.clubnautico.ClubNautico.repositories.SocioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class SocioService {

    @Autowired
    private SocioRepository socioRepository;

    @Autowired
    private BarcoRepository barcoRepository;

    @Autowired
    private AmarreRepository amarreRepository;

    @Autowired
    private PatronRepository patronRepository;

    public List<Socio> getAllSocios() {
        return socioRepository.findAll();
    }

    public Optional<Socio> getSocioById(Long id) {
        return socioRepository.findById(id);
    }

    @Transactional
    public Socio saveSocio(Socio socio) {
        if (socio.getSocio_id() != null) {
            throw new IllegalArgumentException("El ID del socio no debe estar presente al crear un nuevo socio.");
        }

        Barco barco = socio.getBarco();
        if (barco != null) {
            if (barco.getBarco_id() != null) {
                Optional<Barco> existingBarco = barcoRepository.findById(barco.getBarco_id());
                if (existingBarco.isPresent()) {
                    socio.setBarco(existingBarco.get());
                } else {
                    throw new IllegalArgumentException("El barco con el ID proporcionado no existe.");
                }
            } else {
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

                barco = barcoRepository.save(barco);
                socio.setBarco(barco);
            }
        }

        // Guardar Socio
        Socio savedSocio = socioRepository.save(socio);

        return savedSocio;
    }


    public void deleteSocio(Long id) {
        socioRepository.deleteById(id);
    }
}
