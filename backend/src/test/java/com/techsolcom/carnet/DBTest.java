package com.groupemre.simba2;

import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;
import org.apache.commons.lang.ArrayUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Classe de test générique permettant de charger avec Liquibase une base de données de test, et de la détruire après exécution des tests.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:db-context.xml")
public abstract class DBTest  {

  public static final String DB_STRUCTURE = "structure.xml";

  private static final ResourceAccessor LIQUIBASE_RESOURCE_ACCESSOR = new ClassLoaderResourceAccessor();

  private String[] testDataResources;

  private static final AtomicBoolean structureInit = new AtomicBoolean(false);

  private static Date structureCreated = null;

  @Autowired
  private DataSource dataSource;

  /**
   * Création d'une base de données de test, et chargement d'un jeu de données de test additionnel.
   *
   * @param testDataResources Fichier liquibase de chargement des données de test.
   */
  public DBTest(String... testDataResources) {
    this.testDataResources = testDataResources;
  }

  @BeforeClass
  public void prepareTestDatabase() throws SQLException, LiquibaseException {
    if (!structureInit.getAndSet(true)) {
      createStructure();
      structureCreated = new Date();
    }
    insertTestData();
  }

  public void createStructure() throws SQLException, LiquibaseException {
    try (Connection connection = dataSource.getConnection()) {
      JdbcConnection jdbcConnection = new JdbcConnection(connection);
      Liquibase liquibase = new Liquibase(DB_STRUCTURE, LIQUIBASE_RESOURCE_ACCESSOR, jdbcConnection);
      liquibase.update("");
    }
  }

  public void insertTestData() throws SQLException, LiquibaseException {
    try (Connection connection = dataSource.getConnection()) {
      JdbcConnection jdbcConnection = new JdbcConnection(connection);
      for (String testDataResource : testDataResources) {
        Liquibase liquibase = new Liquibase(testDataResource, LIQUIBASE_RESOURCE_ACCESSOR, jdbcConnection);
        liquibase.update("");
      }
    }
  }

  @AfterClass
  public void dropTestData() throws SQLException, LiquibaseException {
    // Le rollback se fait en ordre inverse d'application.
    ArrayUtils.reverse(testDataResources);
    try (Connection connection = dataSource.getConnection()) {
      JdbcConnection jdbcConnection = new JdbcConnection(connection);
      for (String testDataResource : testDataResources) {
        Liquibase liquibase = new Liquibase(testDataResource, LIQUIBASE_RESOURCE_ACCESSOR, jdbcConnection);
        liquibase.rollback(structureCreated, "");
      }
    }
  }
}
