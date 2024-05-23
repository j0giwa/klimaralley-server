/* 
 * These inserts a a bit hacky, 
 * because this is initdata ans needs to be inserted ONLY ONCE
 */

/*
 * Init data for wasserarm satt game
 *
 * Entry format: (id, name, price, type, water)
 * 
 * Attribute help:
 * 
 * Price: pounits
 * water: litres per unit (kilo unless stated otherwise)
 *
 * Types:
 * - 0 = FRUIT
 * - 1 = Vegetable
 * - 2 = Meat
 * - 3 = Animal Product (or anything that involves Animals)
 * - 4 = Drink
 */
INSERT INTO `wasserarm_shop_items` (
	`id`, `name`, `price`, `type`, `water`
)
SELECT 
	new.id,
	new.name,
	new.price,
	new.type,
	new.water
FROM (
	SELECT 1 AS id, 'Röstkaffe' AS name, 50 AS price, 4 AS type, 21000 AS water UNION ALL
	SELECT 2, 'Rindfleisch', 200, 2, 15455 UNION ALL
	SELECT 3, 'Schokolade', 50, 5, 17196 UNION ALL
	SELECT 4, 'Käse', 100, 3, 5000 UNION ALL
	SELECT 5, 'Schweinefleisch', 100, 2, 15455 UNION ALL
	SELECT 6, 'Hühnerfleisch', 120, 2, 3900 UNION ALL
	SELECT 7, 'Reis', 60, 1, 3470 UNION ALL
	SELECT 8, 'Ei', 10, 3, 3300 UNION ALL
	SELECT 9, 'Olivenöl', 200, 1, 14725 UNION ALL
	SELECT 10, 'Rapsöl', 150, 1, 4300 UNION ALL
	SELECT 11, 'Avocado', 200, 1, 2000 UNION ALL
    	SELECT 12, 'Salat', 100, 1, 237 UNION ALL
    	SELECT 13, 'Gurke', 100, 1, 353 UNION ALL
    	SELECT 14, 'Zucker', 30, 1, 1500 UNION ALL
    	SELECT 15, 'Spargel', 80, 1, 1473 UNION ALL
	SELECT 16, 'Brot', 120, 3, 1340 UNION ALL
	SELECT 17, 'Weizen', 30, 1, 1000 UNION ALL
	SELECT 18, 'Milch', 50, 3, 1000 UNION ALL
	SELECT 19, 'Wein', 180, 4, 1000 UNION ALL
	SELECT 20, 'Apfelsaft', 30, 4, 950 UNION ALL
	SELECT 21, 'Mais', 40, 4, 900 UNION ALL
	SELECT 22, 'Orangensaft', 20, 4, 850 UNION ALL
	SELECT 23, 'Bier', 20, 4, 300 UNION ALL
	SELECT 24, 'Zwieblen', 50, 4, 280 UNION ALL
	SELECT 25, 'Kartoffeln', 60, 4, 255 UNION ALL
	SELECT 26, 'Tasse Kaffe', 15, 4, 140 UNION ALL
	SELECT 27, 'Karotten', 30, 4, 131 UNION ALL
	SELECT 28, 'Tomaten', 20, 4, 214 UNION ALL
	SELECT 29, 'Tasse Tee', 15, 4, 30 UNION ALL
	SELECT 30, 'Tomatenmark', 30, 4, 855 UNION ALL
	SELECT 31, 'Butter', 40, 4, 553
) AS new
WHERE NOT EXISTS (
	SELECT 1 
	FROM `wasserarm_shop_items` existing
	WHERE existing.id = new.id
);
