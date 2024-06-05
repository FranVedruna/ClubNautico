package com.clubnautico.ClubNautico.repositories;

import com.clubnautico.ClubNautico.entities.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Long> {
}
