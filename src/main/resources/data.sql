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
 * - 2 = Nuts
 * - 3 = Meat and Poultry
 * - 4 = Fish and SeaFood
 * - 5 = Dairy Foods
 * - 6 = Drink
 */
INSERT INTO `wasserarm_shop_items` (`id`, `name`, `price`, `type`, `water`)
SELECT * FROM (
    SELECT 1 AS id, 'Röstkaffe' AS name, 50 AS price, 6 AS type, 21000 AS water UNION ALL
    SELECT 2, 'Rindfleisch', 200, 3, 15455 UNION ALL
    SELECT 3, 'Schokolade', 50, 5, 17196 UNION ALL
    SELECT 4, 'Käse', 100, 5, 5000 UNION ALL
    SELECT 5, 'Schweinefleisch', 100, 3, 15455 UNION ALL
    SELECT 6, 'Hühnerfleisch', 120, 3, 3900 UNION ALL
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
    SELECT 18, 'Milch', 50, 5, 1000 UNION ALL
    SELECT 19, 'Wein', 180, 6, 1000 UNION ALL
    SELECT 20, 'Apfelsaft', 30, 6, 950 UNION ALL
    SELECT 21, 'Mais', 40, 1, 900 UNION ALL
    SELECT 22, 'Orangensaft', 20, 6, 850 UNION ALL
    SELECT 23, 'Bier', 20, 6, 300 UNION ALL
    SELECT 24, 'Zwieblen', 50, 1, 280 UNION ALL
    SELECT 25, 'Kartoffeln', 60, 1, 255 UNION ALL
    SELECT 26, 'Tasse Kaffe', 15, 6, 140 UNION ALL
    SELECT 27, 'Karotten', 30, 1, 131 UNION ALL
    SELECT 28, 'Tomaten', 20, 1, 214 UNION ALL
    SELECT 29, 'Tasse Tee', 15, 6, 30 UNION ALL
    SELECT 30, 'Tomatenmark', 30, 1, 855 UNION ALL
    SELECT 31, 'Butter', 40, 5, 553
) AS new
WHERE NOT EXISTS (
    SELECT 1 
    FROM `wasserarm_shop_items` existing
    WHERE existing.id = new.id
);
