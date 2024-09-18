package de.thowl.klimaralley.server.web.api.wasserarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import de.thowl.klimaralley.server.core.expections.wasserarm.InvalidGameException;
import de.thowl.klimaralley.server.core.services.score.ScoreboardService;
import de.thowl.klimaralley.server.core.services.wasserarm.WasserarmService;
import de.thowl.klimaralley.server.core.services.auth.AuthenticationService;
import de.thowl.klimaralley.server.core.utils.auth.Tokenizer;
import de.thowl.klimaralley.server.storage.entities.score.Game;
import de.thowl.klimaralley.server.storage.entities.wasserarm.Eater;
import de.thowl.klimaralley.server.storage.entities.wasserarm.WasserarmShopItem;
import de.thowl.klimaralley.server.web.schema.util.ResponseBody;
import de.thowl.klimaralley.server.web.schema.wasserarm.GameSubmission;
import de.thowl.klimaralley.server.web.schema.wasserarm.CoinResponse;
import de.thowl.klimaralley.server.web.schema.wasserarm.GameScoreResponse;
import de.thowl.klimaralley.server.web.schema.wasserarm.WaterResponse;

import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.ArraySchema;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * RESTful API of the Wasserarmsatt game.
 *
 * Contains Methods to Update the Players gamestate
 *
 * @author Jonas Schwind
 * @version 0.6.0
 */
@Slf4j
@RestController
@RequestMapping("/water")
@CrossOrigin(origins = "http://localhost")
@Tag(name = "Wasserarmsatt", description = "Wasserarmsatt API, Contains Methods to Update the Players gamestate")
public class WasserAPI {


	@Autowired
	private AuthenticationService authsvc;

	@Autowired
	private WasserarmService wassersvc;

	@Autowired
	private ScoreboardService scoreboardsvc;

	/**
	 * Get all shop items from the Database
	 *
	 * curl -X 'GET' 'http://localhost:8080/water/items'
	 *
	 * @return {@link WasserarmShopItem}[] (code {@code 200 OK})
	 */
	@Operation(
		summary = "Retrieve all Wasserarm-satt shop items",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Wasserarmsatt shop items",
				content = @Content(array = @ArraySchema(schema = @Schema(implementation = WasserarmShopItem.class)))),
	})
	@RequestMapping(value = "/items", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAllItems() {
		log.info("entering getAllItems (GET-Method: /water/items)");
		return ResponseEntity.status(HttpStatus.OK).body(wassersvc.getAll());
	}

	/**
	 * Retrieves a {@link Eater} to serve.
	 *
	 * curl -X 'GET' 'http://localhost:8080/water/eater'
	 *
	 * @return repsonse (code {@code 200}) with all shop items as JSON
	 */
	@Operation(
		summary = "Retrieves a Eater to serve",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Wasserarmsatt Eater information",
				content = @Content(schema = @Schema(implementation = Eater.class))),
	})
	@RequestMapping(value = "/eater", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getEater() {

		Eater eater;

		log.info("entering getAllItems (GET-Method: /water/eater)");

		eater = this.wassersvc.generateEater();

		return ResponseEntity.status(HttpStatus.OK).body(eater);
	}

	/**
	 * Gets water from given user
	 *
	 * NOTE: The swagger ui generates a wrong curl, call with:
	 * curl -X 'GET' \
	 *   'localhost:8080/water/water' \
	 *   -H 'accept text/plain'
	 *   -H 'Authorization: Bearer <TOKEN>'
	 *
	 * @return repsonse (code {@code 200}) with the water ammount
	 */
	@Operation(
		summary = "Get water amount of user",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Water in Liters",
				content = @Content(
					schema = @Schema(
						implementation = WaterResponse.class),
						examples = @ExampleObject(value = "{ 'message': 'Retrieved water ammount', 'water': 500 }")))
		},
		security = @SecurityRequirement(name = "bearerAuth")
	)
	@RequestMapping(value = "/water", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getWater(
		@Parameter(hidden = true)
		@RequestHeader(name = "Authorization", required = false)
        String token
	) {
		int water = 0;
		Claims claims;
		ResponseBody body;

		log.info("entering getScore (GET-Method: /water/water)");

		claims = Tokenizer.parseToken(Tokenizer.getBearer(token));

		if (this.authsvc.isValid(token)) {
			int userId = Integer.parseInt(claims.getSubject());
			log.info("Authenticated user ID: {}", userId);
			water = this.wassersvc.getWater(userId);
		} else {
			water = 25000;
		}

		body = new WaterResponse();
		body.setMessage("Retrieved water amount");
		((WaterResponse) body).setWater(water);
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}

	/**
	 * Get coin amount of given user
	 *
	 * NOTE: The swagger ui generates a wrong curl, call with:
	 * curl -X 'POST' \
	 * 'localhost:8080/water/water?Amount=500' \
	 * -H 'accept text/plain'
	 * -H 'Authorization: Bearer <TOKEN>'
	 *
	 * @return repsonse (code {@code 200}) with the coin amount
	 */
	@Operation(summary = "Get coin amount of user", responses = {
			@ApiResponse(responseCode = "200", description = "Water ammount ", content = @Content(schema = @Schema(implementation = CoinResponse.class), examples = @ExampleObject(value = "{ 'message': 'Retrieved coin ammount', 'coins': 500 }"))),
			@ApiResponse(responseCode = "401", description = "User not authentificated", content = @Content(schema = @Schema(implementation = ResponseBody.class), examples = @ExampleObject(value = "{ 'message': 'Authorisation token needed, get one from /auth/login' }"))),
	}, security = @SecurityRequirement(name = "bearerAuth"))
	@RequestMapping(value = "/coins", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getCoins(
			@Parameter(hidden = true) @RequestHeader(name = "Authorization", required = false) String token) {

		int coins = 0;
		Claims claims;
		ResponseBody body;

		log.info("entering getCoins (GET-Method: /water/coins)");

		body = new ResponseBody();
		claims = Tokenizer.parseToken(Tokenizer.getBearer(token));

		if (this.authsvc.isValid(token)) {
			log.info("Authenticated user ID: {}", claims.getSubject());
			long userId = Long.parseLong(claims.getSubject());
			coins = this.wassersvc.getCoins(userId);
		} else {
			coins = 5000;
		}

		body = new CoinResponse();
		body.setMessage("Retrieved coin ammount");
		((CoinResponse) body).setCoins(coins);
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}

	/**
	 * Increase water of given user
	 *
	 * NOTE: The swagger ui generates a wrong curl, call with:
	 * curl -X 'POST' \
	 *   'localhost:8080/water/water?Amount=500' \
	 *   -H 'accept text/plain'
	 *   -H 'Authorization: Bearer <TOKEN>'
	 *
	 * @return repsonse (code {@code 200}) with a confirmation message
	 */
	@Operation(
		summary = "Increase water amount of user (Authentification required)",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Water ammount increased",
				content = @Content(
					schema = @Schema(implementation = WaterResponse.class),
					examples = @ExampleObject(value = "{ 'message': 'Increased water ammount', 'water': 500 }"))),
			@ApiResponse(
				responseCode = "401",
				description = "User not authentificated",
				content = @Content(
					schema = @Schema(implementation = ResponseBody.class),
					examples = @ExampleObject(value = "{ 'message': 'Authorisation token needed, get one from /auth/login' }"))),
		},
		security = @SecurityRequirement(name = "bearerAuth")
	)
	@RequestMapping(value = "/water", method = RequestMethod.PATCH, produces = "application/json")
	public ResponseEntity<Object> setWater(
		@Parameter(hidden = true)
		@RequestHeader(name = "Authorization", required = false)
		String token,

		@RequestParam(name = "Amount")
		int amount
	) {
		int water = 0;
		Claims claims;
		ResponseBody body;

		log.info("entering getScore (PATCH-Method: /water/water)");

		body = new ResponseBody();
		claims = Tokenizer.parseToken(Tokenizer.getBearer(token));

		if (this.authsvc.isValid(token)) {
			log.info("Authenticated user ID: {}", claims.getSubject());
			long userId = Long.parseLong(claims.getSubject());
			this.wassersvc.addWater(userId, amount);
			water = this.wassersvc.getWater(userId);
		} else {
			log.error("Unauthorised call of PATCH-Method: /water/water");
			body = new ResponseBody();
			body.setMessage("Authorisation token needed, get one from /auth/login");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
		}

		body = new WaterResponse();
		body.setMessage("Increased water ammount");
		((WaterResponse) body).setWater(water);
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}

	/**
	 * Calculates score bases on given items
	 *
	 * NOTE: The swagger ui generates a wrong curl, call with:
	 * curl -X 'POST' \
	 *   'localhost:8080/water/score' \
	 *   -H 'accept text/plain'
	 *   -H 'Authorization: Bearer <TOKEN>'
	 *
	 * @return repsonse (code {@code 200}) with the calculated score
	 */
	@Operation(
		summary = "Retrieves game score based on items",
		responses = {
			@ApiResponse(
				responseCode="501",
				description="Wasserarmsatt score",
				content= @Content(
					schema = @Schema(implementation = GameScoreResponse.class),
					examples = @ExampleObject(
						value = "{ 'message': 'Its over 9000!!!', 'score': 9500 }")))
			},
		security = @SecurityRequirement(name = "bearerAuth")
	)
	@RequestMapping(value = "/score", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> getScore(
		@Parameter( hidden = true )
		@RequestHeader(name = "Authorization", required = false)
		String token,

		@Parameter(
			description = "Wasserarm-satt game submission schema containing eaterId and items",
			required = true,
			content = @Content(schema = @Schema(implementation = GameSubmission.class)))
		@RequestBody
		GameSubmission schema
	) {
		long userId;
		int score, itemc, water, coins;
		Claims claims;
		ResponseBody body;
		WasserarmShopItem[] items;
		boolean scoreboard;

		log.info("entering getScore (POST-Method: /water/score)");

		userId = 0; //HACK: Dafault value to satisfy compiler
		score = 0;
		scoreboard = false;

		claims = Tokenizer.parseToken(Tokenizer.getBearer(token));

		if (this.authsvc.isValid(token)) {
			userId = Long.parseLong(claims.getSubject());
			water = this.wassersvc.getWater(userId);
			coins = this.wassersvc.getCoins(userId);
			scoreboard = true;
		} else {
			// TODO: Move magic numbers to conf file
			water = 25000;
			coins = 2000;
		}

		try {
			itemc = schema.getItems().size();
			items = schema.getItems().toArray(new WasserarmShopItem[itemc]);
			score = this.wassersvc.getScore(schema.getEaterId(), items, coins, water);
		} catch (InvalidGameException e) {
			log.error("Invalid Wasserarm-satt Game");
		}

		if (scoreboard) {
			this.scoreboardsvc.addEntry(userId, score, Game.WASSERARM);
		}

		body = new GameScoreResponse();
		body.setMessage("Good job");
		((GameScoreResponse) body).setScore(score);;
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}

}
