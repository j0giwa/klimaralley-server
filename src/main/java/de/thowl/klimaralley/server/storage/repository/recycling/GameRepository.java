package de.thowl.klimaralley.server.storage.repository.recycling;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.thowl.klimaralley.server.storage.entities.recycling.Game;

/**
 * Repository interface for managing {@link Game} entities.
 * 
 * This repository provides standard CRUD operations and additional query methods
 * for {@link Game} entities, which represent different games in the system.
 * 
 * {@link Game} entities are stored in the database with unique identifiers and
 * names, which can be queried through this repository.
 * 
 * @author Jeffrey Böttcher
 * @version 1.0.0
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    
    // Standard-CRUD-Methoden
    Optional<Game> findByName(String name);

}
