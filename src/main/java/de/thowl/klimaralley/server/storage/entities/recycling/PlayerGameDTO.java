package de.thowl.klimaralley.server.storage.entities.recycling;


/**
 * Data Transfer Object (DTO) für die Eingabe- und Ausgabeoperationen von {@link PlayerGame}-Daten.
 * 
 * Dieses DTO dient der Übertragung von Daten, die mit einem {@link PlayerGame} verknüpft sind. 
 * Es enthält Informationen wie die Spieler-ID, die Spiel-ID, die Punkte, den Abschlussstatus und
 * den Erfolgstatus sowie den Namen des Spiels.
 * 
 * @author Jeffrey Böttcher
 * @version 1.0.0
 */

public class PlayerGameDTO {

    private Long playerId;
    private Long gameId;
    private Integer points;
    private Boolean isCompleted;
    private Boolean isSuccessful;
    private String gameName;

    public PlayerGameDTO() {}

    public PlayerGameDTO(Long playerId, Long gameId, Integer points, Boolean isCompleted, Boolean isSuccessful, String gameName) {
        this.playerId = playerId;
        this.gameId = gameId;
        this.points = points;
        this.isCompleted = isCompleted;
        this.isSuccessful = isSuccessful;
        this.gameName = gameName;
    }

    // Getter und Setter
    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Boolean getIsSuccessful() {
        return isSuccessful;
    }

    public void setIsSuccessful(Boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

   
}

