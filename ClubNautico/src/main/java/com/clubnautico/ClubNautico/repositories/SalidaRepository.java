package com.clubnautico.ClubNautico.repositories;

import com.clubnautico.ClubNautico.entities.Salida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalidaRepository extends JpaRepository<Salida, Long> {
}
