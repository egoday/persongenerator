package com.persongenerator.ee.rest.model;

import java.util.ArrayList;
import java.util.List;

public final class DniPattern {

	private final String value;
	
	public DniPattern(final String value) {
		this.value = value;
	}
	
	public List<String> toList() {
		final List<String> result = new ArrayList<>();

		String pos = null;

		for (int i=0; i<8; i++) {
			if (value != null && value.length() > i) {
				pos = String.valueOf(value.charAt(i));
			} else {
				pos = null;
			}

			result.add(pos);
		}
		
		return result;
	}
	
	public String toString() {
		return value;
	}

}
