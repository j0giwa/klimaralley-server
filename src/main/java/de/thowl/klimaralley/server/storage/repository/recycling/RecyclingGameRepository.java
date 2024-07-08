package de.thowl.klimaralley.server.storage.repository.recycling;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.thowl.klimaralley.server.storage.entities.recycling.RecyclingGame;

public interface RecyclingGameRepository extends JpaRepository<RecyclingGame, Integer> {

    List<RecyclingGame> findByUsername(String username);
    
}
