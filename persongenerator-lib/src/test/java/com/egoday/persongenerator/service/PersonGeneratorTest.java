package com.egoday.persongenerator.service;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.egoday.persongenerator.model.Person;

public class PersonGeneratorTest {

	@Test
	public void testGenerate() {
		
		PersonGenerator personGenerator = new PersonGenerator();
		
		Person person = personGenerator.nextPerson(null, 10, 10, Arrays.asList("", "", "", "", "", "", "", ""));
		
		Assert.assertTrue(person != null);
	}
}