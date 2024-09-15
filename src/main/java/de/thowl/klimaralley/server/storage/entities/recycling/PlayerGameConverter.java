package de.thowl.klimaralley.server.storage.entities.recycling;

import org.springframework.stereotype.Component;

/**
 * Konverter-Klasse zur Umwandlung von {@link PlayerGame}-Objekten in {@link PlayerGameDTO}-Objekte.
 * 
 * Diese Klasse bietet eine Methode, um ein {@link PlayerGame}-Objekt in ein {@link PlayerGameDTO}
 * zu konvertieren. Der Konverter wird verwendet, um Daten zwischen der Datenbankebene und der 
 * Präsentationsebene zu übertragen.
 * 
 * @author Jeffrey Böttcher
 * @version 1.0.0
 */
@Component
public class PlayerGameConverter {

    /**
     * Konvertiert ein {@link PlayerGame}-Objekt in ein {@link PlayerGameDTO}-Objekt.
     * 
     * Wenn das übergebene {@link PlayerGame}-Objekt null ist, wird null zurückgegeben.
     * Andernfalls wird ein neues {@link PlayerGameDTO}-Objekt erstellt und mit den Werten
     * aus dem {@link PlayerGame}-Objekt initialisiert.
     * 
     * @param playerGame das {@link PlayerGame}-Objekt, das konvertiert werden soll
     * @return das konvertierte {@link PlayerGameDTO}-Objekt oder null, wenn das {@link PlayerGame} null ist
     */
    public PlayerGameDTO convertToDTO(PlayerGame playerGame) {
        if (playerGame == null) {
            return null;
        }
        return new PlayerGameDTO(
            playerGame.getPlayer().getId(), // Spieler-ID
            playerGame.getGame().getId(), // Spiel-ID
            playerGame.getPoints(), // Punkte
            playerGame.getIsCompleted(), // Abgeschlossen
            playerGame.getIsSuccessful(), // Erfolgreich
            playerGame.getGame().getName() // Spielname
            
        );
    }

        
}
