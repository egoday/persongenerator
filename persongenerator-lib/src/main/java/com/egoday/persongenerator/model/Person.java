package com.egoday.persongenerator.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
	
	private Gender gender;

	private String name;

	private String app1;

	private String app2;

	private String dni;
	
	public Person() {}
	
	public Person(Gender gender, String name, String app1, String app2, String dni) {
		this.gender = gender;
		this.name = name;
		this.app1 = app1;
		this.app2 = app2;
		this.dni = dni;
	}
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApp1() {
		return app1;
	}

	public void setApp1(String app1) {
		this.app1 = app1;
	}

	public String getApp2() {
		return app2;
	}

	public void setApp2(String app2) {
		this.app2 = app2;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String toString() {
		return String.format("%s %s %s %s", name, app1, app2, dni);
	}
	
	public String fullName() {
		return String.format("%s %s %s", name, app1, app2);
	}	
}