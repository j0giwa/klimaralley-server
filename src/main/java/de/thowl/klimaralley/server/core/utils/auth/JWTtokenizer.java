package de.thowl.klimaralley.server.core.utils.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

import de.thowl.klimaralley.server.storage.entities.auth.User;

public class JWTtokenizer {

	private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

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
			// Handle other JwtExceptions (e.g., expired token, invalid signature)
			throw new RuntimeException("Invalid token", e);
		}
	}

}
