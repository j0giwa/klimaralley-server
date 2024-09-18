package de.thowl.klimaralley.server.core.utils.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;

import de.thowl.klimaralley.server.storage.entities.auth.User;

/**
 * Generate and parses JWT Bearer-tokens.
 * 
 * @author Jonas Schwind
 * @version 1.2.0
 */
@Slf4j
public class Tokenizer {

	private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

	/**
	 * Generates a JWT token for a {@link User}.
	 *
	 * @param user The {@link User} that shall be associated with the Token.
	 *
	 * @return a JWT token
	 */ 
	public static String generateToken(User user) {
		return Jwts.builder()
			.setSubject(String.valueOf(user.getId()))
			.claim("email", user.getEmail())
			.claim("username", user.getUsername())
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
			.signWith(key)
			.compact();
	}

	/**
	 * Parses a JWT token and extracts its claims.
	 *
	 * @return The token's claims 
	 */
	public static Claims parseToken(String token) {
		// Check if the token is null or empty
		if (token == null || token.isEmpty()) {
			return null;
		}

		try {
			return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
		} catch (JwtException e) {
			log.error("Invalid token: {}", token);
		} catch (SecurityException e) {
			log.error("Invalid JWT signature for token: {}", token);
    		} catch (Exception e) {
        		log.error("We dont know what went wrong :(");
    		}

		return null;
	}

	/**
	 * Extracts JWT-Token from Bearer-Token.
	 * 
	 * @param bearer Bearer-Token
	 * @return JWT-Token or {@code null} if there was no Bearer-Token.
	 */
	public static String getBearer(String bearer) {

		String token;
		
		if (bearer == null) {
			return null;
		}

		token = null;
		if (bearer.startsWith("Bearer")) {
			String[] slices = bearer.split(" ");

			if (slices.length == 2) {
				token = slices[1];
			}
		}
		
		return token;
	}

}
