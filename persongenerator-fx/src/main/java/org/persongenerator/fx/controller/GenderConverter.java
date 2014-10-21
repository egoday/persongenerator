package org.persongenerator.fx.controller;

import javafx.util.StringConverter;

import org.persongenerator.fx.io.Resources;

import com.egoday.persongenerator.model.Gender;

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
