package de.thowl.klimaralley.server.core.utils;

import java.util.HashMap;

public class Counter {
	
	/**
	 * Compares two Arrays and retuns the amount of duplicates.
	 * 
	 * @param array1 An Array to Compare
	 * @param array2 Another Array to Compare
	 * @return Number of elements present in both arrays.
	 */
	public static <T> int countDupliactes(T[] array1, T[] array2) {
		
		int identical;
		HashMap<T, Integer> countMap;

		countMap = new HashMap<>();

		for (T element : array1) {
			countMap.put(element, countMap.getOrDefault(element, 0) + 1);
		}

		identical = 0;

		for (T element : array2) {
			if (countMap.containsKey(element) && countMap.get(element) > 0) {
				identical++;
				countMap.put(element, countMap.get(element) - 1);
			}
		}
		return identical;
	}

}
