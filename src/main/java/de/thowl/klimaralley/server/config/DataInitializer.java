package de.thowl.klimaralley.server.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import de.thowl.klimaralley.server.storage.entities.auth.User;
import de.thowl.klimaralley.server.storage.entities.recycling.Game;
import de.thowl.klimaralley.server.storage.entities.recycling.PlayerGame;
import de.thowl.klimaralley.server.storage.repository.recycling.GameRepository;
import de.thowl.klimaralley.server.storage.repository.recycling.PlayerGameRepository;
import de.thowl.klimaralley.server.storage.repository.recycling.PlayerRepository;
import jakarta.activation.DataSource;
import jakarta.annotation.PostConstruct;


/**
 * Diese Klasse dient zur Initialisierung der Daten bei der Anwendung.
 * 
 * Die Klasse wird verwendet, um beim Start der Anwendung notwendige SQL-Skripte auszuführen
 * und die Datenbank mit den benötigten Anfangsdaten zu versorgen. Sie verwendet die
 * Repositories {@link PlayerRepository}, {@link GameRepository} und {@link PlayerGameRepository}
 * sowie die {@link DataSource} zur Verbindung mit der Datenbank.
 * 
 * @author Jeffrey Böttcher
 * @version 1.0.0
 */
@Component
public class DataInitializer {

    private final DataSource dataSource;
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final PlayerGameRepository playerGameRepository;

    // Konstruktor für die Initialisierung der Daten
    @Autowired
    public DataInitializer(DataSource dataSource, PlayerRepository playerRepository,
                           GameRepository gameRepository, PlayerGameRepository playerGameRepository) {
        this.dataSource = dataSource;
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
        this.playerGameRepository = playerGameRepository;
    }


    // nicht mehr benötigt, es werden die Benutzer genommen die sich tatsächlich registrieren
    @PostConstruct
    public void initialize() {
        // try {
        //     // // Führen Sie SQL-Skripte aus, um Daten zu initialisieren
        //     // try (Connection connection = dataSource.getConnection()) {
        //     //     ScriptUtils.executeSqlScript(connection, new ClassPathResource("user.sql"));
        //     //     // Weitere SQL-Skripte können hier hinzugefügt werden, falls benötigt
        //     // }
        //     // Verarbeitet bereits vorhandene Spieler die in der Datenbank gespeichert sind
        //     processExistingPlayers();
            
        // } catch (SQLException e) {
        //     e.printStackTrace(); // 
        // }
    }

    private void processExistingPlayers() {
        List<User> players = playerRepository.findAll();
        
        for (User player : players) {
            initializePlayerGames(player);
        }
    }

    
    private void initializePlayerGames(User player) {
        Game quizGame = getOrCreateGame("Quiz Spiel");
        Game sortingGame = getOrCreateGame("Müll Sortieren");
        Game recyclingGame = getOrCreateGame("Recyclebar oder nicht");
        Game memoryGame = getOrCreateGame("Memory");

        // Erstellen und Speichern der PlayerGame Einträge
        savePlayerGame(player, quizGame);
        savePlayerGame(player, sortingGame);
        savePlayerGame(player, recyclingGame);
        savePlayerGame(player, memoryGame);
    }

    //Holt ein Spiel anhand seines Namens oder erstellt es, falls es nicht vorhanden ist.
    private Game getOrCreateGame(String gameName) {
        Optional<Game> optionalGame = gameRepository.findByName(gameName);
        if (optionalGame.isPresent()) {
            return optionalGame.get();
        } else {
            Game game = new Game(gameName);
            return gameRepository.save(game);
        }
    }

    private void savePlayerGame(User player, Game game) {
        Optional<PlayerGame> optionalPlayerGame = playerGameRepository.findByPlayerAndGame(player, game);
        if (optionalPlayerGame.isEmpty()) {
            PlayerGame playerGame = new PlayerGame(player, game, 0, false, false);
            playerGameRepository.save(playerGame);
        }
    }
}

