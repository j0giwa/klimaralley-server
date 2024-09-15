package de.thowl.klimaralley.server.storage.repository.recycling;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.thowl.klimaralley.server.storage.entities.auth.User;

/**
 * Repository-Interface für die Verwaltung von {@link User}-Entitäten.
 * 
 * Dieses Repository bietet Standard-CRUD-Operationen und zusätzliche Abfragemethoden
 * für {@link User}-Entitäten, die Benutzer im System repräsentieren.
 * 
 * Es unterstützt die Suche nach Benutzern anhand ihrer E-Mail-Adresse und ihres Namens.
 * 
 * @author Jeffrey Böttcher
 * @version 1.0.0
 */
@Repository
public interface PlayerRepository extends JpaRepository<User, Long> {
    
    //Findet einen {@link User} basierend auf der E-Mail-Adresse.
    User findByEmail(String email);
    
    //Findet einen {@link User} basierend auf dem Namen
    User findByName(String name);

}
