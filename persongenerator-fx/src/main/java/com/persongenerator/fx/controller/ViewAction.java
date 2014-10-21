package com.persongenerator.fx.controller;

import javafx.beans.property.StringProperty;

public abstract class ViewAction {
	
	private StringProperty message;
	
	public ViewAction(StringProperty message) {
		this.message = message;
	}
	public abstract void action();
	
	public void beforeAction() {
		message.set("");
	}
	
	public void success() {
		message.set(successMsg());
	}
	
	public void fail(Exception e) {
		e.printStackTrace();
		message.set(e.getLocalizedMessage());
	}
	
	public String successMsg() { return ""; }

	public void doIt() {
		try {
			beforeAction();
			action();
			success();
		} catch (Exception e) {
			fail(e);
		}
	}
}
