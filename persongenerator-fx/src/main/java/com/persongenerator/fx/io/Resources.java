package com.persongenerator.fx.io;

import java.util.Locale;
import java.util.ResourceBundle;

public class Resources {

	private static final String MESSAGES_BUNDLE = "MessagesBundle";
	
	private static ResourceBundle messages;

	public static final ResourceBundle messages() {
		if (messages == null) {
			synchronized (ResourceBundle.class) {
				if (messages == null) {
					messages = ResourceBundle.getBundle(MESSAGES_BUNDLE, Locale.getDefault());
				}
			}
		}

		return messages;
	}
}
