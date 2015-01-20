package com.persongenerator.ee.rest.resources;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.egoday.persongenerator.model.Gender;
import com.egoday.persongenerator.model.Person;
import com.egoday.persongenerator.service.PersonGenerator;
import com.persongenerator.ee.rest.model.DniPattern;

@Path("/generator")
public class PersonGeneratorResource {
	
	private PersonGenerator personGenerator = new PersonGenerator();

    @GET
    @Path("/next")
    @Produces(MediaType.APPLICATION_JSON)
    public Person next(
    		@QueryParam("gender") Gender gender,
    		@DefaultValue("100") @QueryParam("namesPercentage") Double namesPercentage,
    		@DefaultValue("100") @QueryParam("appsPercentage") Double appsPercentage,
    		@DefaultValue("") @QueryParam("dniPattern") DniPattern dniPattern) {
    	
    	return personGenerator.nextPerson(gender, namesPercentage, appsPercentage, dniPattern.toList());
    }
}
