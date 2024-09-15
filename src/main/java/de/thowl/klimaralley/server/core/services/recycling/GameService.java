package de.thowl.klimaralley.server.core.services.recycling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.thowl.klimaralley.server.storage.entities.recycling.Game;
import de.thowl.klimaralley.server.storage.repository.recycling.GameRepository;

import java.util.List;
import java.util.Optional;


/**
 * Service-Klasse für die Verwaltung von {@link Game}-Entitäten.
 * 
 * Dieser Service bietet Geschäftslogik für {@link Game}-Entitäten und verwendet
 * das {@link GameRepository} zur Interaktion mit der Datenbank.
 * 
 * @author Jeffrey Böttcher
 * @version 1.0.0
 */
@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    //Gibt eine Liste aller {@link Game}-Entitäten zurück.
    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }
    //Findet ein {@link Game} basierend auf der angegebenen ID.
    public Game findGameById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    //Speichert eine {@link Game}-Entität in der Datenbank.
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }
     
    // Spiel nach Namen finden (optional)
     public Optional<Game> getGameByName(String name) {
        return gameRepository.findByName(name);
    }
}
