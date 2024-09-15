package de.thowl.klimaralley.server.web.api.recycling;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.thowl.klimaralley.server.core.services.recycling.PlayerService;
import de.thowl.klimaralley.server.storage.entities.auth.User;
import de.thowl.klimaralley.server.storage.entities.recycling.PlayerGame;

/**
 * Controller-Klasse für die Verwaltung von Spielerressourcen.
 * 
 * Diese Klasse enthält Endpunkte für die folgenden Operationen:
 * - Abrufen aller Spiele eines Spielers
 * - Abrufen eines Spielers anhand des Benutzernamens
 * 
 * @author Jeffrey Böttcher
 * @version 1.0.0
 */
@RestController
@RequestMapping("/recyclingapi/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    //Endpunkt zum Abrufen aller Spiele eines Spielers anhand der Spieler-ID
    @GetMapping("/{playerId}/games")
    public ResponseEntity<List<PlayerGame>> getPlayerGames(@PathVariable Long playerId) {
        List<PlayerGame> playerGames = playerService.findPlayerGamesByPlayerId(playerId);
        return new ResponseEntity<>(playerGames, HttpStatus.OK);
    }
    //Endpunkt zum Abrufen eines Spielers anhand des Benutzernamens.
    @GetMapping("/username/{name}")
    public ResponseEntity<User> getPlayerByUsername(@PathVariable String name) {
        User player = playerService.findByName(name);
        return player != null ? ResponseEntity.ok(player) : ResponseEntity.notFound().build();
    }
}
