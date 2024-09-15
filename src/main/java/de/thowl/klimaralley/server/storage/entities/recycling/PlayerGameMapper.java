package de.thowl.klimaralley.server.storage.entities.recycling;

import org.springframework.stereotype.Component;

import de.thowl.klimaralley.server.storage.entities.auth.User;


/**
 * Mapper-Klasse zur Konvertierung zwischen {@link PlayerGame} und {@link PlayerGameDTO}.
 * 
 * Diese Klasse enthält Methoden zum Umwandeln von {@link PlayerGame} Objekten in {@link PlayerGameDTO} und umgekehrt.
 * 
 * @author Jeffrey Böttcher
 * @version 1.0.0
 */
@Component
public class PlayerGameMapper {

    // PlayerGame zu PlayerGameDTO konvertieren
    public PlayerGameDTO toDTO(PlayerGame playerGame) {
        return new PlayerGameDTO(
                playerGame.getPlayer().getId(),
                playerGame.getGame().getId(),
                playerGame.getPoints(),
                playerGame.getIsCompleted(),
                playerGame.getIsSuccessful(),
                playerGame.getGame().getName()
        );
    }

    // PlayerGameDTO zu PlayerGame konvertieren
    public PlayerGame toEntity(PlayerGameDTO dto, User player, Game game) {
        return new PlayerGame(
                player,
                game,
                dto.getPoints(),
                dto.getIsCompleted(),
                dto.getIsSuccessful()
        );
    }
}
