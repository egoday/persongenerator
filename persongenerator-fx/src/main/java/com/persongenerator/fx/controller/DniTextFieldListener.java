package com.persongenerator.fx.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class DniTextFieldListener implements ChangeListener<String> {
	
	private TextField field;
	
	public DniTextFieldListener(TextField field) {
		this.field = field;
	}

	@Override
	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		boolean noDigit = newValue.length() > 1 ||
				(newValue.length() == 1 && newValue.charAt(0) < '0') ||
				(newValue.length() == 1 && newValue.charAt(0) > '9');
		
		if (noDigit) {
			field.setText(oldValue);
		}
	}
}
