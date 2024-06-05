package de.thowl.klimaralley.server.web.schema.wasserarm;

import de.thowl.klimaralley.server.web.schema.util.ResponseBody;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Wasserarm-satt game score", description = "Score revieced at the end of the game")
public class WasserarmGameScore extends ResponseBody {

	@Schema(description = "earned points", example = "9500") // IT'S OVER 9000!!!
	private int score;

}
