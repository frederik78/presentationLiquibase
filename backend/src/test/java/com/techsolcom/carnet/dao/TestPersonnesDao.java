package com.techsolcom.carnet.dao;

import com.techsolcom.carnet.DBTest;
import com.techsolcom.dto.carnet.Pays;
import com.techsolcom.dto.carnet.Personne;
import liquibase.exception.LiquibaseException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.extractProperty;

public class TestPersonnesDao extends DBTest {

    @Autowired
    private PersonneRepository personneRepository;

	@BeforeClass
	public static void init() throws ClassNotFoundException, SQLException,
			MalformedURLException, LiquibaseException {
		testDataResources = new String[] {"testdata/personne.xml", "testdata/adresseContact.xml"};
        prepareTestDatabase();
	}

    @Test
    public void find_everybody()
    {
//        final List<Personne> personnes = personneRepository.findAll();
//        assertThat(personnes).hasSize(2);
//        assertThat(extractProperty("pays.nom").from(personnes)).contains("COLOMBIA", "CONGO");
    }

	@Test
	public void find_contact_and_fetch_addresses_and_provider() {
        final Personne personne = personneRepository.findPersonAndContactAndProvider(10);
		assertThat(personne.getAdressesContact()).hasSize(3);
	}

}
