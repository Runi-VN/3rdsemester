package rest;

import com.google.gson.Gson;
import facade.CustomerFacade;
import java.util.Random;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author runin
 */
@Path("customers")
public class CustomerResource {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    CustomerFacade cf = CustomerFacade.getCustomerFacade(emf);
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CustomerResource
     */
    public CustomerResource() {
    }

    /**
     * Retrieves representation of an instance of rest.CustomerResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCustomers() {
        return new Gson().toJson(cf.allCustomers());
    }
    
    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomCustomer() {
        int userCount = cf.getNumberOfCustomers();
        Random rnd = new Random();
        return new Gson().toJson(cf.findByID(rnd.nextInt(userCount)+1));
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomerById(@PathParam("id") int id) {
        return new Gson().toJson(cf.findByID(id-1)); //starts from 0, so id 1 is at location 0
    }

    /**
     * PUT method for updating or creating an instance of CustomerResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
