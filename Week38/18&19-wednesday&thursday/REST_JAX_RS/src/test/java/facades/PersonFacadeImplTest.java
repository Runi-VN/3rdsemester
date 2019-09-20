package facades;

import entities.Address;
import utils.EMF_Creator;
import entities.Person;
import exceptions.MissingInputException;
import exceptions.PersonNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
public class PersonFacadeImplTest {

    private static EntityManagerFactory emf;
    private static PersonFacadeImpl facade;
    private static List<Person> persons;

    public PersonFacadeImplTest() {
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = PersonFacadeImpl.getPersonFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        persons = new ArrayList(); //init

        Address a1 = new Address("Mindevej 12", "2300", "København S");
        Address a2 = new Address("Vej 28A", "2200", "Nørrebro?");
        Address a3 = new Address("Et sted 5", "1200", "Yikes");
        Address a4 = new Address("Langt væk 22", "8900", "Jepby");

        persons.add(new Person("Runi", "Vedel", "112", a1));
        persons.add(new Person("Knud", "Knudsen", "12345678", a2));
        persons.add(new Person("Per", "Pedersen", "55128922", a3));
        persons.add(new Person("Johnny", "Larsen", "10302040", a4));

        try {
            //reset DB tables, AutoIncrement-counter
            em.getTransaction().begin();
            //Query query = em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0; \n"
                    //+ "TRUNCATE table Week5Day3_test.PERSON; \n"
                    //+ "SET FOREIGN_KEY_CHECKS = 1;");
            //query.executeUpdate();
            em.createNamedQuery("Person.deleteAllRows");
            em.createNamedQuery("Address.deleteAllRows");
            //Query query = em.createNativeQuery("truncate table Week5Day3_test.PERSON;");
            //query.executeUpdate();
            em.getTransaction().commit();
            //add collection to DB
            persons.forEach(p -> {
                em.getTransaction().begin();
                em.persist(p);
                em.getTransaction().commit();
            });
        } finally {
            em.close();
        }
    }

    /**
     * Test of getPersonCount method, of class PersonFacadeImpl.
     */
    @org.junit.jupiter.api.Test
    public void testGetPersonCount() {
        //Assert
        assertEquals(persons.size(), facade.getPersonCount()); //4
    }

    /**
     * Test of addPerson method, of class PersonFacadeImpl.
     *
     * @throws exceptions.MissingInputException
     */
    @org.junit.jupiter.api.Test
    public void testAddPerson() throws MissingInputException {
        //Arrange
        String fName = "Test";
        String lName = "Testerson";
        String phone = "1-800-test";
        Address address = new Address("Upper test St. 12", "500", "London");
        Person expResult = new Person(fName, lName, phone, address);
        Person result;

        //Act
        result = facade.addPerson(fName, lName, phone, address);
        expResult.setId(result.getId()); //has no ID until now. haxx.

        //Assert
        Assertions.assertNotNull(result);
        assertEquals(expResult, result);
    }

    @org.junit.jupiter.api.Test
    public void testAddPersonException() {
        //Arrange
        String fName = "";
        String lName = "";
        String phone = null;
        Address address = null;

        //Act
        //Assert
        Assertions.assertThrows(MissingInputException.class, () -> {
            facade.addPerson(fName, lName, phone, address);
        });
    }

    /**
     * Test of deletePerson method, of class PersonFacadeImpl.
     *
     * @throws exceptions.PersonNotFoundException
     */
    @org.junit.jupiter.api.Test
    public void testDeletePerson() throws PersonNotFoundException {
        //Arrange
        int elementPosition = 3;
        Person expResult = persons.get(elementPosition);
        int expectedId = expResult.getId();
        Person result;

        //Act
        result = facade.deletePerson(expectedId);

        //Assert
        Assertions.assertNotNull(result);
        assertEquals(expResult, result);
    }

    @org.junit.jupiter.api.Test
    public void testDeletePersonException() {
        //Arrange
        int expectedId = 1000;

        //Act
        //Assert
        Assertions.assertThrows(PersonNotFoundException.class, () -> {
            facade.deletePerson(expectedId);
        });
    }

    /**
     * Test of getPerson method, of class PersonFacadeImpl.
     *
     * @throws exceptions.PersonNotFoundException
     */
    @org.junit.jupiter.api.Test
    public void testGetPerson() throws PersonNotFoundException {
        //Arrange
        int elementPosition = 2;
        Person expResult = persons.get(elementPosition);
        int expectedId = expResult.getId();
        Person result;

        //Act
        result = facade.getPerson(expectedId);

        //Assert
        Assertions.assertNotNull(result);
        assertEquals(expResult, result);
    }

    @org.junit.jupiter.api.Test
    public void testGetPersonException() throws PersonNotFoundException {
        //Arrange
        int expectedId = 1000;

        //Act
        //Assert
        Assertions.assertThrows(PersonNotFoundException.class, () -> {
            facade.getPerson(expectedId);
        });
    }

    /**
     * Test of getAllPersons method, of class PersonFacadeImpl.
     */
    @org.junit.jupiter.api.Test
    public void testGetAllPersons() throws PersonNotFoundException {
        //Arrange
        List<Person> expResult = persons;
        List<Person> result;

        //Act
        result = facade.getAllPersons();

        //Assert
        Assertions.assertNotNull(result);
        assertEquals(expResult, result);
    }

    @org.junit.jupiter.api.Test
    public void testGetAllPersonsException() {
        //Arrange
        EntityManager em = emf.createEntityManager();
        try {
            //reset DB tables, AutoIncrement-counter
            em.getTransaction().begin();
            Query query = em.createNativeQuery("truncate table Week5Day3_test.PERSON;");
            query.executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        //Act
        //Assert
        Assertions.assertThrows(PersonNotFoundException.class, () -> {
            facade.getAllPersons();
        });
    }

    /**
     * Test of editPerson method, of class PersonFacadeImpl.
     *
     * @throws exceptions.MissingInputException
     */
    @org.junit.jupiter.api.Test
    public void testEditPerson() throws MissingInputException {
        //Arrange
        Person expResult = persons.get(3);
        Person result;

        //Act
        expResult.setFirstName("Bigfoot");
        result = facade.editPerson(expResult);
        //expResult.setId(result.getId());

        //Assert
        Assertions.assertNotNull(result);
        assertEquals(expResult, result);
    }

    @org.junit.jupiter.api.Test
    public void testEditPersonException() {
        //Arrange
        Person expResult = null;

        //Act
        //Assert
        Assertions.assertThrows(MissingInputException.class, () -> {
            facade.editPerson(expResult);
        });
    }

}
