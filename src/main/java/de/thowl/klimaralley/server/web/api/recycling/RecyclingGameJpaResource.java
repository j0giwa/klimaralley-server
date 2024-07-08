package de.thowl.klimaralley.server.web.api.recycling;


import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.thowl.klimaralley.server.core.services.recycling.RecyclingGameService;
import de.thowl.klimaralley.server.storage.entities.recycling.RecyclingGame;
import de.thowl.klimaralley.server.storage.repository.recycling.RecyclingGameRepository;


@RestController
public class RecyclingGameJpaResource {

    private RecyclingGameService gameService;
    private RecyclingGameRepository gameRepository;

    public RecyclingGameJpaResource(RecyclingGameService gameService, RecyclingGameRepository gameRepository){
        this.gameService = gameService;
        this.gameRepository = gameRepository;
    }

    @GetMapping("users/{username}/games")
    public List<RecyclingGame> getGames(@PathVariable String username){
        return gameRepository.findByUsername(username);
    }

    @GetMapping("/users/{username}/games/{id}")
    public RecyclingGame getGame(@PathVariable String username, @PathVariable int id ) {
        return gameRepository.findById(id).get();
    }

    @DeleteMapping("/users/{username}/games/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable String username,@PathVariable int id ) {
        gameRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{username}/games/{id}")
    public RecyclingGame updateGame(@PathVariable String username, @PathVariable int id, @RequestBody RecyclingGame game ) {
         //todoService.updateTodo(todo);
         gameRepository.save(game);
        return game;

        
    }
    
}
