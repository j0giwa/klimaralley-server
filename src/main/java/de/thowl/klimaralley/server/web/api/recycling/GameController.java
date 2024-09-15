package de.thowl.klimaralley.server.web.api.recycling;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.thowl.klimaralley.server.core.services.recycling.GameService;
import de.thowl.klimaralley.server.storage.entities.recycling.Game;

/**
 * Controller-Klasse für die Verwaltung von Spielressourcen.
 * 
 * Diese Klasse enthält Endpunkte für die folgenden Operationen:
 * - Abrufen aller Spiele
 * - Abrufen eines Spiels nach ID
 * - Aktualisieren eines Spiels
 * - Suchen eines Spiels nach Name
 * 
 * @author Jeffrey Böttcher
 * @version 1.0.0
 */
@RestController
@RequestMapping("/recyclingapi/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    //Endpunkt zum Abrufen aller Spiele.
    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameService.findAllGames();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    //Endpunkt zum Abrufen eines Spiels anhand der angegebenen ID.
    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGameById(@PathVariable Long gameId) {
        Game game = gameService.findGameById(gameId);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    //Endpunkt zum Aktualisieren eines Spiels.
    @PostMapping
    public ResponseEntity<Game> UpdateGame(@RequestBody Game game) {
        Game savedGame = gameService.saveGame(game);
        return ResponseEntity.ok(savedGame);
    }

    //Endpunkt zum Suchen eines Spiels mit dem Namen.
    @GetMapping("/search")
    public ResponseEntity<Game> getGameByName(@RequestParam String name) {
        Optional<Game> game = gameService.getGameByName(name);
        return game.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
}
