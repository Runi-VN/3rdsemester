package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dto.PersonDTO;
import dto.PersonDTO_create;
import dto.PersonsDTO;
import entities.Person;
import exceptions.ExceptionDTO;
import exceptions.MissingInputException;
import exceptions.PersonNotFoundException;
import utils.EMF_Creator;
import facades.PersonFacadeImpl;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final PersonFacadeImpl facade = PersonFacadeImpl.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        JsonObject welcomeMsg = new JsonObject();
        welcomeMsg.addProperty("msg", "Hello World");
        return GSON.toJson(welcomeMsg);
    }

    @GET
    @Path("/populate")
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() throws MissingInputException, PersonNotFoundException {
        facade.addPerson("Runi", "Vedel", "112");
        facade.addPerson("Knud", "Knudsen", "12345678");
        facade.addPerson("Per", "Pedersen", "55128922");
        facade.addPerson("Johnny", "Larsen", "10302040");

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
    public String getPerson(@PathParam("id") int id) throws PersonNotFoundException {
        return GSON.toJson(new PersonDTO(facade.getPerson(id)));
    }

    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllPersons() throws PersonNotFoundException {
        return GSON.toJson(new PersonsDTO(facade.getAllPersons()));
    }

    @Path("/add")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addPerson(String person_JSON) throws MissingInputException, PersonNotFoundException {
        PersonDTO person = GSON.fromJson(person_JSON, PersonDTO.class);
        Person result = facade.addPerson(person.getfName(), person.getlName(), person.getPhone());
        PersonDTO_create response = new PersonDTO_create(result); //ensure we live up to the assignment of DTO-display only
        return Response.ok(response).build();
    }

    @Path("/edit")
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response editPerson(String person_JSON) throws MissingInputException, PersonNotFoundException {
        Person person = GSON.fromJson(person_JSON, Person.class);
        PersonDTO edited = new PersonDTO(facade.editPerson(person));
        return Response.ok(edited).build();
    }

    @Path("/{id}/delete")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    public String deletePerson(@PathParam("id") int id) throws PersonNotFoundException {
        return GSON.toJson(new PersonDTO(facade.deletePerson(id)));

    }

    @Path("/fail")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String fail() {
        return String.valueOf(17/0);
    }

    /**
     * Old solution before we implemented custom exceptions.
     *
     * I decided to keep it and not implement custom exceptions for
     *
     * @getPersonCount.
     *
     * Nostalgia.
     *
     * @param errMsg
     * @return
     */
    private String jsonException(String errMsg) {
        JsonObject error = new JsonObject();
        error.addProperty("error_occured", errMsg);
        return GSON.toJson(error);
    }
}
