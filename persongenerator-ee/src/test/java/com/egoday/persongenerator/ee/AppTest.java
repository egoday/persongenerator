package com.egoday.persongenerator.ee;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.egoday.persongenerator.service.PersonGenerator;

@RunWith(Arquillian.class)
public class AppTest {

	@Inject
	private PersonGenerator personGenerator;
	
	@Deployment
    public static JavaArchive createDeployment() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
        	.addPackages(true, "com.egoday")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

		return jar;
    }

	@Test
	public void appTest() {
		System.out.println(personGenerator);
		Assert.assertNotNull(personGenerator);
	}
}
