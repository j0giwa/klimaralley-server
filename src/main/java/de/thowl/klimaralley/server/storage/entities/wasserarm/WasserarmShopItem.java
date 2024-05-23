package de.thowl.klimaralley.server.storage.entities.wasserarm;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@Table(name = "Items")
@AllArgsConstructor
@NoArgsConstructor
public class WasserarmShopItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@NonNull
	private String name;
	
	@NotNull
	@NonNull
	private WasserarmShopItemType type;

	@NotNull
	private int water;

	@NotNull
	private int price;
}
