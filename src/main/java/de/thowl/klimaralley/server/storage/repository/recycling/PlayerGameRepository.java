package de.thowl.klimaralley.server.storage.repository.recycling;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import de.thowl.klimaralley.server.storage.entities.auth.User;
import de.thowl.klimaralley.server.storage.entities.recycling.Game;
import de.thowl.klimaralley.server.storage.entities.recycling.PlayerGame;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@link PlayerGame} entities.
 * 
 * This repository provides standard CRUD operations and additional query methods
 * for {@link PlayerGame} entities, which represent the association between a 
 * player ({@link User}) and a game ({@link Game}) in the system.
 * 
 * The repository supports querying {@link PlayerGame} records based on player 
 * and game identifiers, and provides custom query methods to retrieve associations
 * based on specific criteria.
 * 
 * @author Jeffrey Böttcher
 * @version 1.0.0
 */

@Repository
public interface PlayerGameRepository extends JpaRepository<PlayerGame, Long> {

    //List<PlayerGame> findByPlayerId(Long playerId);

    //Findet eine {@link PlayerGame}-Entität basierend auf der Spieler-ID und der Spiel-ID.
    Optional<PlayerGame> findByPlayerIdAndGameId(Long playerId, Long gameId);

    //Findet alle {@link PlayerGame}-Entitäten, die mit einer bestimmten Spieler-ID verknüpft sind.
    Optional<PlayerGame> findByPlayerAndGame(User player, Game game);

    //Findet alle {@link PlayerGame}-Entitäten, die mit einer bestimmten Spieler-ID verknüpft sind.
    @Query("SELECT pg FROM PlayerGame pg WHERE pg.player.id = :playerId")
    List<PlayerGame> findByPlayerId(@Param("playerId") Long playerId);
}
