// FlutRepository.java
package de.thowl.klimaralley.server.storage.repository.flut;

import org.springframework.data.jpa.repository.JpaRepository;
import de.thowl.klimaralley.server.storage.entities.flut.FlutMaxLevel;
import java.util.Optional;
/*****
 * For @Entitie MaxFlutLevel
 * 
 * @author Cedric Bourgeois
 * @version 1.0.0
 */
public interface FlutRepository extends JpaRepository<FlutMaxLevel, Long> {
    Optional<FlutMaxLevel> findByUserId(Long userId); 
}
