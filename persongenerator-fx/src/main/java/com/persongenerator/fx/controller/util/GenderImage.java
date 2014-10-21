package com.persongenerator.fx.controller.util;

import java.io.InputStream;

import javafx.scene.image.Image;

import com.egoday.persongenerator.model.Gender;

public class GenderImage {
	
	public static Image fromGender(Gender gender) {
		String iconName = gender != null ?
				String.format("/%s.png", gender.toString().toLowerCase()) :
					"/person-icon.png";

		InputStream stream = GenderImage.class.getResourceAsStream(iconName);

		return new Image(stream);
	}
}
