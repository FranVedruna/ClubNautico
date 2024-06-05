package com.clubnautico.ClubNautico.services;

import com.clubnautico.ClubNautico.entities.Amarre;
import com.clubnautico.ClubNautico.entities.Barco;
import com.clubnautico.ClubNautico.repositories.AmarreRepository;
import com.clubnautico.ClubNautico.repositories.BarcoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmarreService {

    @Autowired
    private AmarreRepository amarreRepository;

    @Autowired
    private BarcoRepository barcoRepository;

    @Autowired
    private BarcoService barcoService;



    public List<Amarre> getAllAmarres() {
        return amarreRepository.findAll();
    }

    public Optional<Amarre> getAmarreById(Long id) {
        return amarreRepository.findById(id);
    }

    public Amarre saveAmarre(Amarre amarre) {
        return amarreRepository.save(amarre);
    }

    public void eliminarAmarre(Long id) {
        amarreRepository.deleteById(id);
    }

    public void deleteAmarre(Long amarreId) {
        // Verificar si algún Barco tiene este Amarre
        List<Barco> barcosConAmarre = barcoService.findByAmarreId(amarreId);
        for (Barco barco : barcosConAmarre) {
            barco.setAmarre(null);
            barcoService.saveBarco(barco);
        }
        eliminarAmarre(amarreId);
    }
}
