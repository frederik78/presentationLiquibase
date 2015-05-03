package com.techsolcom.carnet.dao;

import static org.fest.assertions.api.Assertions.assertThat;

import java.net.MalformedURLException;
import java.sql.SQLException;

import liquibase.exception.LiquibaseException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.techsolcom.carnet.DBTest;

public class TestPaysDao extends DBTest {

    @Autowired
    private PaysRepository paysRepository;

	@BeforeClass
	public static void init() throws ClassNotFoundException, SQLException,
			MalformedURLException, LiquibaseException {
//		testDataResources = new String[] {};
        prepareTestDatabase();
	}

	@Test
	public void count_number_of_countries() {
        final long nbPays = paysRepository.count();
		assertThat(nbPays).isEqualTo(243);
	}

}
