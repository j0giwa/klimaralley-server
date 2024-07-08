package de.thowl.klimaralley.server.web.schema.wasserarm;

import de.thowl.klimaralley.server.web.schema.util.ResponseBody;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "Wasserarm-satt water response", description = "Water ammount of player")
public class WaterResponse extends ResponseBody {

	@Schema(description = "water in liters", example = "500")
	private int water;

}
