package com.egoday.persongenerator.service;

import java.util.List;
import java.util.Random;

import com.egoday.persongenerator.model.Gender;
import com.egoday.persongenerator.model.Person;

public class PersonGenerator {
	
	public static final String NAME_FILE_M = "/names_m.txt";
	public static final String NAME_FILE_F = "/names_f.txt";
	public static final String APPS_FILE = "/apps.txt";

	public Person nextPerson(Gender gender,
			double namesPercentage,
			double appsPercentage,
			List<String> dniPattern) {

		if (gender == null) gender = randomGender();
		
		String nameFile = gender == Gender.FEMALE ? NAME_FILE_F : NAME_FILE_M;

		StringGenerator nameGenerator = new StringGenerator(nameFile, namesPercentage);
		StringGenerator appGenerator = new StringGenerator(APPS_FILE, appsPercentage);
		DniGenerator dniGenerator = new DniGenerator(dniPattern);
		
		String name = nameGenerator.nextString();
		String app1 = appGenerator.nextString();
		String app2 = appGenerator.nextString();
		String dni = dniGenerator.nextDni();
		
		return Person.create()
				.gender(gender).name(name)
				.app1(app1).app2(app2)
				.dni(dni);
	}
	
	private Gender randomGender() {
		return new Random().nextBoolean() ? Gender.MALE : Gender.FEMALE;
	}
}