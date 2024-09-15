package de.thowl.klimaralley.server.core.services.recycling;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.thowl.klimaralley.server.storage.entities.recycling.PlayerGame;
import de.thowl.klimaralley.server.storage.entities.recycling.PlayerGameConverter;
import de.thowl.klimaralley.server.storage.entities.recycling.PlayerGameDTO;
import de.thowl.klimaralley.server.storage.entities.recycling.PlayerGameMapper;
import de.thowl.klimaralley.server.storage.repository.recycling.GameRepository;
import de.thowl.klimaralley.server.storage.repository.recycling.PlayerGameRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Service-Klasse für die Verwaltung von {@link PlayerGame}-Entitäten.
 * 
 * Dieser Service bietet Geschäftslogik für {@link PlayerGame}-Entitäten und verwendet
 * die Repositories {@link GameRepository} und {@link PlayerGameRepository} zur Interaktion
 * mit der Datenbank. Er enthält auch Konverter und Mapper zur Umwandlung zwischen 
 * {@link PlayerGame}-Objekten und {@link PlayerGameDTO}-Objekten.
 * 
 * @author Jeffrey Böttcher
 * @version 1.0.0
 */
@Service
public class PlayerGameService {

     private final GameRepository gameRepository; // Dein Repository für Spiele

    public PlayerGameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Autowired
    private PlayerGameRepository playerGameRepository;

    @Autowired
    private PlayerGameConverter playerGameConverter; 

     @Autowired
    private PlayerGameMapper playerGameMapper; 

    //Konvertiert das PlayerGame-Objekt in ein DTO, wird von der Controller-Klasse aufgerufen
    
    public List<PlayerGameDTO> getPlayerGamesByPlayerId(Long playerId) {
        List<PlayerGame> playerGames = playerGameRepository.findByPlayerId(playerId);
        if (playerGames == null || playerGames.isEmpty()) {
            return Collections.emptyList(); // oder werfen Sie eine benutzerdefinierte Ausnahme
        }

        // Konvertieren Sie die Liste von PlayerGame in eine Liste von PlayerGameDTO
        return playerGames.stream()
                .map(playerGameConverter::convertToDTO)
                .collect(Collectors.toList());
    }

     //Update Methode zum Aktualisieren eines Spiels
     
     public PlayerGameDTO updatePlayerGame(Long playerId, Long gameId, PlayerGameDTO playerGameDTO) {
        Optional<PlayerGame> optionalPlayerGame = playerGameRepository.findByPlayerIdAndGameId(playerId, gameId);
        
        if (optionalPlayerGame.isPresent()) {
            PlayerGame playerGame = optionalPlayerGame.get();
            playerGame.setPoints(playerGameDTO.getPoints());
            playerGame.setIsCompleted(playerGameDTO.getIsCompleted());
            playerGame.setIsSuccessful(playerGameDTO.getIsSuccessful());

            PlayerGame updatedGame = playerGameRepository.save(playerGame);
            return playerGameMapper.toDTO(updatedGame);
        } else {
            return null;  // Spiel nicht gefunden
        }
    }  

    public List<PlayerGame> getAllPlayerGames() {
        return playerGameRepository.findAll();
    }

    public List<PlayerGame> getGamesByPlayer(Long playerId) {
        return playerGameRepository.findByPlayerId(playerId);
    }

    public Optional<PlayerGame> getPlayerGameById(Long id) {
        return playerGameRepository.findById(id);
    }
    
    public PlayerGame savePlayerGame(PlayerGame playerGame) {
        return playerGameRepository.save(playerGame);
    }

    public List<PlayerGame> findByPlayerId(Long playerId) {
        return playerGameRepository.findByPlayerId(playerId);
    }

    // public PlayerGame findPlayerGameByPlayerIdAndGameId(Long playerId, Long gameId) {
    //     return playerGameRepository.findByPlayerIdAndGameId(playerId, gameId);
    // }

    // Methode zum Abrufen der game_id
    
    
    public Long getGameIdFromPlayerGame(Long id) {
        return playerGameRepository.findById(id)
            .map(playerGame -> playerGame.getGame().getId())
            .orElse(null);
    }



}
