package com.egoday.persongenerator.service;

import java.util.List;
import java.util.Random;

class DniGenerator {
	
	private static final String LETTERS = "TRWAGMYFPDXBNJZSQVHLCKET";
	
	private static final String PATTERN = "%08d%s";
	
	private Random random;
	
	private List<String> dniPattern;

	public DniGenerator(List<String> dniPattern) {
		random = new Random();

		this.dniPattern = dniPattern;
	}
	
	public String nextDni() {
		int dni = randomDni();

		return String.format(PATTERN, dni, letter(pos(dni)));
	}
	
	private int randomDni() {
		String dni = "";
		
		for(String value : dniPattern) dni += digit(value);
		
		return Integer.valueOf(dni);
	}
	
	private String digit(String value) {
		return (value == null || value.isEmpty()) ?	randomDigit() :	value;
	}
	
	private String randomDigit() {
		return String.valueOf(random.nextInt(10));
	}
	
	private int pos(int dni) { return dni % 23; }
	
	private Character letter(int pos) { return LETTERS.charAt(pos);	}
}