package com.clubnautico.ClubNautico.repositories;

import com.clubnautico.ClubNautico.entities.Barco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarcoRepository extends JpaRepository<Barco, Long> {

}
