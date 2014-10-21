package com.egoday.persongenerator.service;

import java.io.InputStream;
import java.util.List;

import com.egoday.persongenerator.service.util.Lines;
import com.egoday.persongenerator.service.util.RandomLine;

class StringGenerator {
	
	private RandomLine randomLine;

	private List<String> values;
	
	public StringGenerator(String resource, double linesPercentage) {
		values = Lines.fromStream(inputStream(resource), linesPercentage);
		randomLine = new RandomLine(values);
	}
	
	public String nextString() {
		return randomLine.nextLine();
	}
	
	private InputStream inputStream(String resource) {
		return getClass().getResourceAsStream(resource);
	}
}