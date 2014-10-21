package com.egoday.persongenerator.service.util;

import java.util.*;

/**
 * Escoge un valor de una lista
 *
 */
public class RandomLine {
	
	private Random random;

	private List<String> lines;
	
	public RandomLine(List<String> lines) {
		this.random = new Random();
		this.lines = lines;
	}
	
	public String nextLine() {
		int nextIndex = random.nextInt(lines.size());
		return lines.get(nextIndex);
	}
}