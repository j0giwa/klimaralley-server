package de.thowl.klimaralley.server.core.services.recycling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.thowl.klimaralley.server.storage.entities.auth.User;
import de.thowl.klimaralley.server.storage.entities.recycling.PlayerGame;
import de.thowl.klimaralley.server.storage.repository.recycling.PlayerGameRepository;
import de.thowl.klimaralley.server.storage.repository.recycling.PlayerRepository;

import java.util.List;

/**
 * Service-Klasse für die Verwaltung von {@link User}-Entitäten und {@link PlayerGame}-Entitäten.
 * 
 * Dieser Service bietet Geschäftslogik für {@link User}- und {@link PlayerGame}-Entitäten und verwendet
 * die Repositories {@link PlayerRepository} und {@link PlayerGameRepository} zur Interaktion
 * mit der Datenbank.
 * 
 * @author Jeffrey Böttcher
 * @version 1.0.0
 */
@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerGameRepository playerGameRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    //Gibt eine Liste von {@link PlayerGame}-Entitäten zurück, die mit einer bestimmten Spieler-ID verknüpft sind.
    public List<PlayerGame> findPlayerGamesByPlayerId(Long playerId) {
        return playerGameRepository.findByPlayerId(playerId);
    }
    //Speichert ein {@link User}-Objekt in der Datenbank.
    public User savePlayer(User player) {
        return playerRepository.save(player);
    }
    //Findet einen {@link User} basierend auf der angegebenen ID.
    public User findPlayerById(Long playerId) {
        return playerRepository.findById(playerId).orElse(null);
    }
    //Findet einen {@link User} basierend auf dem Namen.
    public User findByName(String name) {
        return playerRepository.findByName(name);
    }
    //Gibt die Spiel-ID aus einem {@link PlayerGame} basierend auf der angegebenen ID zurück.
    public Long getGameIdFromPlayerGame(Long id) {
        return playerGameRepository.findById(id)
            .map(playerGame -> playerGame.getGame().getId())
            .orElse(null);
    }
}

