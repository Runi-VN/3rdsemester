package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import entities.Person;
import utils.EMF_Creator;
import facades.PersonFacade;
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

//Todo Remove or change relevant parts before ACTUAL use
@Path("Week6Day4")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final PersonFacade facade = PersonFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("/populate")
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
        facade.addPerson("Runi Vedel", "Elev");
        facade.addPerson("Knud", "Smartass");
        facade.addPerson("Per", "LektieLaver");
        facade.addPerson("Johnny Madsen", "Ved ikke");

        JsonObject dbMsg = new JsonObject();
        dbMsg.addProperty("msg", "Populated DB");
        return GSON.toJson(dbMsg);
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPerson(@PathParam("id") int id) {
        return GSON.toJson(facade.getPerson((long) id));
    }

    @Path("/add")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addPerson(String person_JSON) {
        Person person = GSON.fromJson(person_JSON, Person.class);
        Person result = facade.addPerson(person.getName(), person.getOccupation());
        return Response.ok(result).build();
    }

    @Path("/edit")
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response editPerson(String person_JSON) {
        Person person = GSON.fromJson(person_JSON, Person.class);
        Person edited = facade.editPerson(person);
        return Response.ok(edited).build();
    }

    @Path("/{id}/delete")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    public String deletePerson(@PathParam("id") int id) {
        return GSON.toJson(facade.deletePerson((long)id));

    }

}
