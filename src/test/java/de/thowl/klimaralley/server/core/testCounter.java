package de.thowl.klimaralley.server.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.thowl.klimaralley.server.core.utils.Counter;

public class testCounter {

	@Test
	void testCountDuplicates() {
		Integer[] array1 = { 1 ,4 ,3, 6, 5, 2, 8 };
		Integer[] array2 = { 10, 4, 19, 6, 4, 2, 9 };

		assertEquals(3, Counter.countDupliactes(array1, array2), "Didnt cout correctly, should be 3");
	}
}
