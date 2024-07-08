package de.thowl.klimaralley.server.web.schema.wasserarm;

import de.thowl.klimaralley.server.web.schema.util.ResponseBody;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "Wasserarm-satt game score", description = "Score revieced at the end of the game")
public class GameScoreResponse extends ResponseBody {

	@Schema(description = "earned points", example = "9500") // IT'S OVER 9000!!!
	private int score;

}
