package com.persongenerator.fx.controller.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import com.persongenerator.fx.controller.util.Pref;

public class PrefUtil {
	
	private static final Logger logger = Logger.getLogger(PrefUtil.class.getSimpleName());

	public static void load(Object object) {
		try {
			Preferences prefs = Preferences.userNodeForPackage(object.getClass());
			
			for (Field field : object.getClass().getDeclaredFields()) {
				for (Assignation assignation : getAssignations()) {
					if (assignation.assign(field)) {
						assignation.load(object, field, prefs);
					}
				}
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}
	
	public static void save(Object object) {
		try {
			Preferences prefs = Preferences.userNodeForPackage(object.getClass());
			
			for (Field field : object.getClass().getDeclaredFields()) {
				for (Assignation assignation : getAssignations()) {
					if (assignation.assign(field)) {
						assignation.save(object, field, prefs);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}
	
	private static List<Assignation> getAssignations() {
		List<Assignation> assignations = new ArrayList<Assignation>();
		assignations.add(new BaseFXMLAssignation());
		return assignations;
	}
	
	private static class BaseFXMLAssignation implements Assignation {
		
		@Override
		public boolean assign(Field field) {
			return field.isAnnotationPresent(Pref.class);
		}

		@Override
		public void load(Object object, Field field, Preferences preferences) throws Exception {
			field.setAccessible(true);

			Object fieldObj = field.get(object);
			
			if (fieldObj instanceof ChoiceBox<?>) {
				Integer value = preferences.getInt(field.getName(), 0);
				((ChoiceBox<?>) fieldObj).getSelectionModel().select(value);
			} else if (fieldObj instanceof TextField) {
				String value = preferences.get(field.getName(), "");
				((TextField) fieldObj).setText(value);
			} else if (fieldObj instanceof Slider) {
				Double value = preferences.getDouble(field.getName(), 1);
				((Slider) fieldObj).setValue(value);
			}
		}

		@Override
		public void save(Object object, Field field, Preferences preferences) throws Exception {
			field.setAccessible(true);

			Object fieldObj = field.get(object);
			
			if (fieldObj instanceof ChoiceBox<?>) {
				Integer value = ((ChoiceBox<?>) fieldObj).getSelectionModel().getSelectedIndex();
				preferences.putInt(field.getName(), value);
			} else if (fieldObj instanceof TextField) {
				String value = ((TextField) fieldObj).getText();
				preferences.put(field.getName(), value);
			} else if (fieldObj instanceof Slider) {
				Double value = ((Slider) fieldObj).getValue();
				preferences.putDouble(field.getName(), value);
			}
		}
	}
	
	private interface Assignation {
		boolean assign(Field field);

		void load(Object object, Field field, Preferences preferences) throws Exception;
		void save(Object object, Field field, Preferences preferences) throws Exception;
	}
}