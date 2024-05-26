package de.thowl.klimaralley.server.core.services.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

import de.thowl.klimaralley.server.storage.entities.auth.AccessToken;


/**
 * @author Jonas Schwind
 * @version 1.0.0 
 */
public class JWTtokenizer {

	private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

	/**
	 * Generates a jwt token from an Accesstoken in the db.
	 * 
	 * @param accessToken
	 * @return jwt token
	 */
	public static String generateToken(AccessToken accessToken) {
		return Jwts.builder()
			.setSubject(String.valueOf(accessToken.getUserId()))
			.claim("usid", accessToken.getUsid())
			.claim("lastActive", accessToken.getLastActive())
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
			.signWith(key)
			.compact();
	}

	public static Claims parseToken(String token) {
		try {
			return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
		} catch (JwtException e) {
			throw new RuntimeException("Invalid token", e);
		}
	}
}
