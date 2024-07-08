package de.thowl.klimaralley.server.web.schema.util;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Generic response body to send to client aplication.
 * 
 * @author Jonas Schwind
 * @version 1.0.0
 */
@Data
@Schema(name = "Generic Response", description = "Client Message")
public class ResponseBody {

	@Schema(description = "Message", example = "lorem ipsum")
	private String message;

}
