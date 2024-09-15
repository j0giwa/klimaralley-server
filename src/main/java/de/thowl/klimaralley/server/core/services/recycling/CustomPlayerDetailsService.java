package de.thowl.klimaralley.server.core.services.recycling;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.thowl.klimaralley.server.storage.entities.auth.User;
import de.thowl.klimaralley.server.storage.repository.recycling.PlayerRepository;


/**
 * Service-Klasse für die Verwaltung von {@link User}-Entitäten.
 * 
 * Dieser Service implementiert das {@link UserDetailsService}-Interface von Spring Security
 * und bietet die Geschäftslogik für die Benutzer-Authentifizierung. Er verwendet
 * das {@link PlayerRepository} zur Interaktion mit der Datenbank und zum Abrufen von Benutzerdaten.
 * 
 * @author Jeffrey Böttcher
 * @version 1.0.0
 */
@Service
public class CustomPlayerDetailsService implements UserDetailsService {

    @Autowired 
    private PlayerRepository playerRepository; 

    @Override 
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException { // Lädt Benutzerdetails anhand des Benutzernamens
        User player = playerRepository.findByName(name); // Sucht den Benutzer in der Datenbank anhand des Namens 
        if (player == null) { // Wenn der Benutzer nicht gefunden wurde
            throw new UsernameNotFoundException("User not found with name: " + name); // Löst eine Ausnahme aus
        }
        return new org.springframework.security.core.userdetails.User( // Gibt ein UserDetails-Objekt zurück, das von Spring Security verwendet wird
            player.getEmail(), // E-Mail-Adresse
            player.getPassword(), // Passwort
            true, // accountNonExpired: Gibt an, ob das Konto nicht abgelaufen ist
            true, // credentialsNonExpired: Gibt an, ob die Anmeldeinformationen nicht abgelaufen sind
            true, // accountNonLocked: Gibt an, ob das Konto nicht gesperrt ist
            true, // enabled: Gibt an, ob das Konto aktiviert ist
            new ArrayList<>() // authorities: Eine leere Liste von Berechtigungen (Rollen), die in diesem Beispiel nicht verwendet wird
        );
    }
}
