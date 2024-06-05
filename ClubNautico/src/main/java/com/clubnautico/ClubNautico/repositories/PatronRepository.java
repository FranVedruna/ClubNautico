package com.clubnautico.ClubNautico.repositories;

import com.clubnautico.ClubNautico.entities.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long>{
}
