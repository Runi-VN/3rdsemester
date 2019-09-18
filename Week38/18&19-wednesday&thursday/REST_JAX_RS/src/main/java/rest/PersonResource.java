package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Person;
import utils.EMF_Creator;
import facades.PersonFacadeImpl;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final PersonFacadeImpl facade = PersonFacadeImpl.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("/populate")
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
        EntityManager em = EMF.createEntityManager();
        List<Person> persons = new ArrayList(); //init

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
        JsonObject dbMsg = new JsonObject();
        dbMsg.addProperty("msg", "Populated DB");
        return GSON.toJson(dbMsg);
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPersonCount() {
        try {
            long count = facade.getPersonCount();
            JsonObject jasonsobject = new JsonObject();
            jasonsobject.addProperty("count", count);
            return GSON.toJson(jasonsobject);
        } catch (Exception ex) {
            return new PersonResource().jsonException(ex.getMessage());
        }
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPerson(@PathParam("id") int id) {
        try {
            return GSON.toJson(new PersonDTO(facade.getPerson(id)));
        } catch (Exception ex) {
            return new PersonResource().jsonException(ex.getMessage());
        }
    }

    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllPersons() {
        try {
            return GSON.toJson(new PersonsDTO(facade.getAllPersons()));
        } catch (Exception ex) {
            return new PersonResource().jsonException(ex.getMessage());
        }
    }

    @Path("/add")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addPerson(String person) {
        try {
            PersonDTO incoming = GSON.fromJson(person, PersonDTO.class);
            Person result = facade.addPerson(incoming.getfName(), incoming.getlName(), incoming.getPhone());
            PersonDTO response = new PersonDTO(); //ensure we live up to the assignment of DTO-display only
            response.setfName(result.getFirstName());
            response.setlName(result.getLastName());
            response.setPhone(result.getPhone());
            //return Response.ok(response).build();
            return GSON.toJson(response);
        } catch (Exception ex) {
            throw new IllegalArgumentException("You messed up the add method: " + ex.getMessage());
            //return new PersonResource().jsonException(ex.getMessage());
        }
    }

    @Path("/edit")
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String editPerson(String person) {
        try {
            PersonDTO incoming = GSON.fromJson(person, PersonDTO.class);
            Person target = facade.getPerson((int) incoming.getId());
            PersonDTO response = new PersonDTO(facade.editPerson(target));
            //return Response.ok(response).build();
            return GSON.toJson(response);
        } catch (Exception ex) {
            throw new IllegalArgumentException("You messed up the add method: " + ex.getMessage());
            //return new PersonResource().jsonException(ex.getMessage());
        }
    }

    private String jsonException(String errMsg) {
        JsonObject error = new JsonObject();
        error.addProperty("error_occured", errMsg);
        return GSON.toJson(error);
    }

}
