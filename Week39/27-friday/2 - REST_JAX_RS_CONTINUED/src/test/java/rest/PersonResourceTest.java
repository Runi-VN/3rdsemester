package rest;

import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Person;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import io.restassured.parsing.Parser;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.Matchers.equalTo;
import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class PersonResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api/";
    //Read this line from a settings-file  since used several places
    //private static final String TEST_DB = "jdbc:mysql://localhost:3307/startcode_test";
    private static List<Person> persons;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.CREATE);

        //NOT Required if you use the version of EMF_Creator.createEntityManagerFactory used above        
        //System.setProperty("IS_TEST", TEST_DB);
        //We are using the database on the virtual Vagrant image, so username password are the same for all dev-databases
        httpServer = startServer();

        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;

        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        persons = new ArrayList(); //init

        persons.add(new Person("Runi", "Vedel", "112"));
        persons.add(new Person("Knud", "Knudsen", "12345678"));
        persons.add(new Person("Per", "Pedersen", "55128922"));
        persons.add(new Person("Johnny", "Larsen", "10302040"));

        try {
            //reset DB tables, AutoIncrement-counter
            em.getTransaction().begin();
            Query query = em.createNativeQuery("truncate table Week5Day3_test.PERSON;");
            query.executeUpdate();
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

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDemo() throws Exception {
        given()
                .contentType("application/json")
                .get("/person").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("msg", equalTo("Hello World"));
    }

    /**
     * Test of getPersonCount method, of class PersonResource.
     */
    @Test
    public void testGetPersonCount() {
        given()
                .contentType("application/json")
                .get("/person/count").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("count", equalTo(persons.size()));
    }

    /**
     * Test of getPerson method, of class PersonResource.
     */
    @Test
    public void testGetPerson() {
        //Arrange
        Person expResult = persons.get(2);

        //Act
        PersonDTO result = get("/person/{id}", expResult.getId()).then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .extract()
                .as(PersonDTO.class);

        //Assert
        MatcherAssert.assertThat((result), equalTo(new PersonDTO(expResult)));
    }

    @Test
    public void testGetPersonException() {
        int id = 1000;
        given()
                .get("/person/{id}", id).then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode()).
                body("code", equalTo(404)).
                body("message", equalTo("No person with provided id found"));

    }

    /**
     * Test of getAllPersons method, of class PersonResource.
     */
    @Test
    public void testGetAllPersons() {
        //Arrange
        PersonsDTO expResult = new PersonsDTO(persons);

        //Act
        PersonsDTO result = get("/person/all").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .extract()
                .as(PersonsDTO.class);

        //Assert
        MatcherAssert.assertThat((result), equalTo(expResult));
    }

    @Test
    public void testGetAllPersonsException() {
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

        given()
                .get("/person/all").then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode()).
                body("code", equalTo(404)).
                body("message", equalTo("Problem finding all persons"));

    }

    /**
     * Test of addPerson method, of class PersonResource.
     *
     */
    @Test
    public void testAddPerson() {
        //Arrange
        PersonDTO expResult = new PersonDTO("John", "Test", "12344321");

        //Act
        PersonDTO result
                = with()
                        .body(expResult) //include object in body
                        .contentType("application/json")
                        .when().request("POST", "/person/add").then() //post REQUEST
                        .assertThat()//.log().body()
                        .statusCode(HttpStatus.OK_200.getStatusCode())
                        .extract()
                        .as(PersonDTO.class); //extract result JSON as object

////        given().
////                with().body(expResult)
////                .when()
////                .request("POST", "/person/add")
////                .then()
////                .statusCode(HttpStatus.OK_200.getStatusCode());
        //Assert
        MatcherAssert.assertThat((result), equalTo(expResult));
    }
    
    @Test
    public void testAddPersonException() {
         //Arrange
        PersonDTO expResult = new PersonDTO("", null, "");
        
        //Act
        given() 
                .contentType("application/json")
                .body(expResult)
                .post("/person/add").then()
                .assertThat()
                //.statusCode(HttpStatus.NOT_FOUND_404.getStatusCode()).
                .body("code", equalTo(404)).
                body("message", equalTo("First Name and/or Last Name is missing"));

    }

    /**
     * Test of editPerson method, of class PersonResource.
     */
    @Test
    public void testEditPerson() {
        //Arrange
        /*TRICKY: Must be sent as a person but returns as a PersonDTO */
        Person expResult = persons.get(3);
        expResult.setFirstName("Buzz");
        expResult.setLastName("Lightyear");
        expResult.setPhone("idk");

        //Act
        PersonDTO result
                = with()
                        .body(expResult) //include object in body
                        .contentType("application/json")
                        .when().request("PUT", "/person/edit").then() //put REQUEST
                        .assertThat()//.log().body()
                        .statusCode(HttpStatus.OK_200.getStatusCode())
                        .extract()
                        .as(PersonDTO.class); //extract result JSON as object

        //Assert
        MatcherAssert.assertThat((result), equalTo(new PersonDTO(expResult))); //convert to personDTO
    }
    
    @Test
    public void testEditPersonException() {
         //Arrange
        Person expResult = new Person();
        //expResult.setFirstName(null);
        //expResult.setLastName("");
        //expResult.setPhone("");
        
        //Act
        given() 
                .contentType("application/json")
                .body(expResult)
                .put("/person/edit").then()
                .assertThat()
                //.statusCode(HttpStatus.NOT_FOUND_404.getStatusCode()).
                .body("code", equalTo(404)).
                body("message", equalTo("First Name and/or Last Name is missing"));

    }

    /**
     * Test of deletePerson method, of class PersonResource.
     */
    @Test
    public void testDeletePerson() {
        //Arrange
        Person person = persons.get(3);
        int id = person.getId();
        PersonDTO expResult = new PersonDTO(persons.get(3));

        //Act
        PersonDTO result
                = with()
                        .contentType("application/json")
                        .when()
                        .request("DELETE", "/person/{id}/delete", id) //DELETE REQUEST
                        .then()
                        .assertThat()//.log().body()
                        //.statusCode(HttpStatus.OK_200.getStatusCode())
                        .extract()
                        .as(PersonDTO.class); //extract result JSON as object

        //Assert
        MatcherAssert.assertThat((result), equalTo(expResult)); //convert to personDTO

    }

    @Test
    public void testDeletePersonException() {
        int id = 1000;
        given()
                .delete("/person/{id}/delete", id).then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode()).
                body("code", equalTo(404)).
                body("message", equalTo("Could not delete, provided id does not exist"));
    }

    @Test
    public void testGetFail() {
        given()
                .get("/person/fail").then()
                .assertThat()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR_500.getStatusCode()).
                body("code", equalTo(500)).
                body("message", equalTo("Internal Server Problem. We are sorry for the inconvenience"));
    }
}
