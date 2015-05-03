package com.techsolcom.carnet;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Classe de test générique permettant de charger avec Liquibase une base de données de test, et de la détruire après exécution des tests.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:db-context.xml")
public abstract class DBTest {

    public static final String DB_STRUCTURE = "structure.xml";
    public static final String DB_DATA = "donnees.xml";

    private static final ResourceAccessor LIQUIBASE_RESOURCE_ACCESSOR = new ClassLoaderResourceAccessor();

    protected static String[] testDataResources = new String[]{};

    private static final AtomicBoolean structureInit = new AtomicBoolean(false);

    private static Date structureCreated = null;

    //    @Value("${db.jdbc}")
    private static String IN_MEMORY_DB = "jdbc:h2:file:~/tmp/carnet;MODE=MySQL";
    //    @Value("${db.password}")
    private static String DB_PASSWORD = "sa";
    //    @Value("${db.user}")
    private static String DB_LOGIN = "sa";

    /**
     * Création d'une base de données de test, et chargement d'un jeu de données de test additionnel.
     *
     * @param testDataResources Fichier liquibase de chargement des données de test.
     */
    public DBTest(String... testDataResources) {
        this.testDataResources = testDataResources;
    }

    @BeforeClass
    public static void prepareTestDatabase() throws SQLException, LiquibaseException, ClassNotFoundException {

        if (!structureInit.getAndSet(true)) {
            createStructure();
            structureCreated = new Date();
        }
        insertTestData();
    }

    public static void createStructure() throws SQLException, LiquibaseException, ClassNotFoundException {

        Class.forName("org.h2.Driver");
        try (Connection connection = DriverManager.getConnection(IN_MEMORY_DB, DB_LOGIN, DB_PASSWORD)) {
            JdbcConnection jdbcConnection = new JdbcConnection(connection);
            Liquibase liquibase = new Liquibase(DB_STRUCTURE, LIQUIBASE_RESOURCE_ACCESSOR, jdbcConnection);
            liquibase.update("");

            liquibase = new Liquibase(DB_DATA, LIQUIBASE_RESOURCE_ACCESSOR, jdbcConnection);
            liquibase.update("");
        }
    }

    public static void insertTestData() throws SQLException, LiquibaseException, ClassNotFoundException {
        try (Connection connection = DriverManager.getConnection(IN_MEMORY_DB, DB_LOGIN, DB_PASSWORD)) {
            JdbcConnection jdbcConnection = new JdbcConnection(connection);
            for (String testDataResource : testDataResources) {
                Liquibase liquibase = new Liquibase(testDataResource, LIQUIBASE_RESOURCE_ACCESSOR, jdbcConnection);
                liquibase.update("");
            }
        }
    }

    @AfterClass
    public static void dropTestData() throws SQLException, LiquibaseException, ClassNotFoundException {
        // Le rollback se fait en ordre inverse d'application.
        ArrayUtils.reverse(testDataResources);
        try (Connection connection = DriverManager.getConnection(IN_MEMORY_DB, DB_LOGIN, DB_PASSWORD)) {
            JdbcConnection jdbcConnection = new JdbcConnection(connection);
            for (String testDataResource : testDataResources) {
                Liquibase liquibase = new Liquibase(testDataResource, LIQUIBASE_RESOURCE_ACCESSOR, jdbcConnection);
                liquibase.rollback(structureCreated, "");
            }
        }
    }
}
