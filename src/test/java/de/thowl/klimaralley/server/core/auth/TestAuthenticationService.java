package de.thowl.klimaralley.server.core.auth;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import de.thowl.klimaralley.server.core.services.auth.AuthenticationService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class TestAuthenticationService {

	@Autowired
	private AuthenticationService authsvc;

	@Test
	void testValidatePassword() {
		log.info("entering test testValidatePassword");
		assertFalse(this.authsvc.validatePassword(null), "'NULL' should not match the requirements");
		assertFalse(this.authsvc.validatePassword("1234"), "'1234' should not match the requirements");
		assertFalse(this.authsvc.validatePassword("lorem"), "'lorm' should not match the requirements");
		assertFalse(this.authsvc.validatePassword("ipsum"), "'ipsum' should not match the requirements");
		assertTrue(this.authsvc.validatePassword("gr3at@3wdsG"), "'gr3at@3wds' should match the requirements");
		assertTrue(this.authsvc.validatePassword("P@ssw0rd"), "'P@ssw0rd' should match the requirements");
		assertFalse(this.authsvc.validatePassword("abcdefghijklmnopqrst"), "should not match the requirements");
	}

	@Test
	void testValidateEmail() {
		log.info("entering test testValidateEmail");
		assertFalse(this.authsvc.validateEmail(null), "'NULL' should not match the requirements");
		assertFalse(this.authsvc.validateEmail("john doe"), "'john doe' should not match the requirements");
		assertFalse(this.authsvc.validateEmail("john doe@test.org"),
				"'john doe@test.org' should not match the requirements");
		assertFalse(this.authsvc.validateEmail("@test.org"), "'@test.org' should not match the requirements");
		assertFalse(this.authsvc.validateEmail("jonh-doe test.org"),
				"'john-doe test.org' should not match the requirements");
		assertTrue(this.authsvc.validateEmail("johndoe@test.org"),
				"'johndoe@test.org' This should match the requirements");
	}

	/**
	 * Testing if a login whith valid credentials returns an accesstoken.
	 */
	@Test
	void testCheckPassword() {
		log.info("entering test testValidatePassword");
		assertTrue(this.authsvc.checkPassword("$2a$15$D3zQTzUx12LgCOHWOH8H4eSXbZ/lhO.l46wpR8vrA6qZ/uhOct..W", "P@ssw0rd"));
		assertFalse(this.authsvc.checkPassword("$2a$15$D3zQTzUx12LgCOHWOH8H4eSXbZ/lhO.l46wpR8vrA6qZ/uhOct..W","Password"));
		assertFalse(this.authsvc.checkPassword("$2a$15$D3zQTzUx12LgCOHWOH8H4eSXbZ/lhO.l46wpR8vrA6qZ/uhOct..W",null));
	}

}
