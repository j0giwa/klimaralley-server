package de.thowl.klimaralley.server.web.api.recycling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.thowl.klimaralley.server.core.services.recycling.PlayerGameService;
import de.thowl.klimaralley.server.storage.entities.recycling.PlayerGame;
import de.thowl.klimaralley.server.storage.entities.recycling.PlayerGameDTO;

import java.util.List;
import java.util.Optional;

/**
 * Controller für die Verwaltung von {@link PlayerGame} Entitäten.
 * 
 * Diese Klasse bietet REST-API-Endpunkte für CRUD-Operationen und spezielle Abfragen bezüglich {@link PlayerGame}.
 * 
 * @author Jeffrey Böttcher
 * @version 1.0.0
 */
@RestController
@RequestMapping("/recyclingapi/player-games")
public class PlayerGameController {

    @Autowired
    private PlayerGameService playerGameService;

    //Gibt eine Liste aller {@link PlayerGame} Objekte zurück.
    @GetMapping
    public List<PlayerGame> getAllPlayerGames() {
        return playerGameService.getAllPlayerGames();
    }
    //Gibt alle {@link PlayerGame} Objekte für einen bestimmten Spieler zurück.
    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<PlayerGame>> getGamesByPlayer(@PathVariable Long playerId) {
        List<PlayerGame> playerGames = playerGameService.getGamesByPlayer(playerId);
        return ResponseEntity.ok(playerGames);
    }
    //Gibt ein {@link PlayerGame} Objekt anhand seiner ID zurück.
    @GetMapping("/{id}")
    public ResponseEntity<PlayerGame> getPlayerGameById(@PathVariable Long id) {
        Optional<PlayerGame> playerGame = playerGameService.getPlayerGameById(id);
        return playerGame.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Speichert ein neues {@link PlayerGame} Objekt.
    @PostMapping
    public ResponseEntity<PlayerGame> savePlayerGame(@RequestBody PlayerGame playerGame) {
        PlayerGame savedPlayerGame = playerGameService.savePlayerGame(playerGame);
        return ResponseEntity.ok(savedPlayerGame);
    }

     // Endpunkt zum Abrufen der Game ID durch die ID des PlayerGame
     @GetMapping("/{id}/game-id")
     public ResponseEntity<Long> getGameIdFromPlayerGame(@PathVariable Long id) {
         Long gameId = playerGameService.getGameIdFromPlayerGame(id);
         return (gameId != null) ? ResponseEntity.ok(gameId) : ResponseEntity.notFound().build();
     }


     //DTO gibt die Games eines Spielers zurück
     @GetMapping("/dto/{playerId}")
    public ResponseEntity<List<PlayerGameDTO>> getPlayerGamesByPlayerId(@PathVariable Long playerId) {
        List<PlayerGameDTO> playerGameDTOs = playerGameService.getPlayerGamesByPlayerId(playerId);
        if (playerGameDTOs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(playerGameDTOs);
    }

    // Endpunkt zum Aktualisieren eines PlayerGame des DTO
    @PutMapping("/dto/{playerId}/{gameId}")
    public ResponseEntity<PlayerGameDTO> updatePlayerGame(
            @PathVariable Long playerId,
            @PathVariable Long gameId,
            @RequestBody PlayerGameDTO playerGameDTO) {
        
        PlayerGameDTO updatedGame = playerGameService.updatePlayerGame(playerId, gameId, playerGameDTO);
        
        if (updatedGame == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedGame);
    }
     

    
}