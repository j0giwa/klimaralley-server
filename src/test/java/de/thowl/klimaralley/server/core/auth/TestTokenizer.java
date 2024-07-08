package de.thowl.klimaralley.server.core.auth;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import de.thowl.klimaralley.server.core.utils.auth.Tokenizer;
import de.thowl.klimaralley.server.storage.entities.auth.User;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
public class TestTokenizer {
	
	private User user;

	@BeforeEach
	void setUp() {

		log.info("Setting up an Tokenizer test");

		user = new User();
		user.setId(1);
		user.setUsername("testuser");
		user.setEmail("user@test.org");
		user.setPassword("$2a$15$D3zQTzUx12LgCOHWOH8H4eSXbZ/lhO.l46wpR8vrA6qZ/uhOct..W");
	}

	@Test
	void testGenerateToken() {
		String token;
		Claims claims;
		
		log.info("entering test GenerateToken");

		token = Tokenizer.generateToken(user);
		assertNotNull(token);

		claims = Tokenizer.parseToken(token);
		assertNotNull(claims);
		assertEquals("1", claims.getSubject());
		assertEquals("user@test.org", claims.get("email"));
		assertEquals("testuser", claims.get("username"));
	}

	@Test
	void testParseTokenInvalid() {
		String invalidToken;

		log.info("entering test parseTokenInvalid");

		invalidToken = "invalidToken";

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			Tokenizer.parseToken(invalidToken);
		});

		assertEquals("Invalid token", exception.getMessage());
	}

	@Test
	void parseTokenIsEmpty() {
		Claims claims;

		log.info("entering test parseTokenIsEmpty");

		claims = Tokenizer.parseToken("");
		assertNull(claims);

		claims = Tokenizer.parseToken(null);
		assertNull(claims);
	}

	/**
	 * Test the getBearer method on (syntactly) valid Bearer-Token.
	 * A valid token follows a "Bearer <TOKEN>" syntax.
	 */
	@Test
	void getBearerIsValid() {
		String token;
		
		log.info("entering test getBearerIsValid");

		token = Tokenizer.getBearer("Bearer validToken");
		assertEquals("validToken", token);
	}

	/**
	 * Test the getBearer method on (syntactly) invalid Bearer-Token.
	 * Invalid tokens are either, {@code null}, empty, or malformed (don't follow the "Bearer <TOKEN>" syntax)
	 */
	@Test
	void getBearerBearerInvalid() {
		String token;

		log.info("entering test getBearerIsInvalid");

		token = Tokenizer.getBearer("BearerInvalidToken");
		assertNull(token);

		token = Tokenizer.getBearer("");
		assertNull(token);

		token = Tokenizer.getBearer(null);
		assertNull(token);
	}

	@AfterEach
	void cleanup() {
		log.info("Cleaning up an Tokenizer test");
		user = null;
	}
}
