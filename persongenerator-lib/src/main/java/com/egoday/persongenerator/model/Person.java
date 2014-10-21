package com.egoday.persongenerator.model;

public class Person {
	
	private Gender gender;
	private String name;
	private String app1;
	private String app2;
	private String dni;
	
	private Person() {}
	
	public static Person create() {
		return new Person();
	}
	
	public Person gender(Gender gender) { this.gender = gender; return this; }
	public Person name(String name) { this.name = name; return this; }
	public Person app1(String app1) { this.app1 = app1; return this; }
	public Person app2(String app2) { this.app2 = app2; return this; }
	public Person dni(String dni) { this.dni = dni; return this; }
	
	public Gender gender() { return gender; }
	public String name() { return name; }
	public String app1() { return app1; }
	public String app2() { return app2; }
	public String dni() { return dni; }

	public String toString() {
		return String.format("%s %s %s %s", name, app1, app2, dni);
	}
	
	public String fullName() {
		return String.format("%s %s %s", name, app1, app2);
	}	
}