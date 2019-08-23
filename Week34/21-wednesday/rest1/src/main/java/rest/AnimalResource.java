package rest;

import com.google.gson.Gson;
import entities.Animal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author runin
 */
@Path("animal")
public class AnimalResource {

    @Context
    private UriInfo context;
    private List<Animal> animals = new ArrayList();

    /**
     * Creates a new instance of AnimalResource
     */
    public AnimalResource() {
    }

    /**
     * Retrieves representation of an instance of rest.AnimalResource
     *
     * @return an instance of java.lang.String
     */
    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "Hello from my first web service";
    }

    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandom() {
        animals.add(new Animal("Dog", 1905, "Bark"));
        animals.add(new Animal("Cat", 2005, "Meow"));
        animals.add(new Animal("Pig", 2024, "Oink"));
        animals.add(new Animal("Seagul", 3000, "Die"));
        Random rnd = new Random();
        Animal randomAnimal = animals.get(rnd.nextInt(4));
        return new Gson().toJson(randomAnimal);
    }

    /**
     * PUT method for updating or creating an instance of AnimalResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
