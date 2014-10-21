package com.persongenerator.fx.controller;

import javafx.util.StringConverter;

import com.egoday.persongenerator.model.Gender;
import com.persongenerator.fx.io.Resources;

public class GenderConverter extends StringConverter<Gender> {

	@Override public Gender fromString(String value) {
		return Gender.valueOf(value);
	}

	@Override public String toString(Gender gender) {
		if (gender != null)
			return Resources.messages().getString(gender.toString());
		else
			return "-";
	}
}
