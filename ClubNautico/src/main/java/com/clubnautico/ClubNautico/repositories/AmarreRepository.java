package com.clubnautico.ClubNautico.repositories;

import com.clubnautico.ClubNautico.entities.Amarre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmarreRepository extends JpaRepository<Amarre, Long> {
}
