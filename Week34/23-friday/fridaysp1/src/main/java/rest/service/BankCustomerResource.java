package rest.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.BankCustomer;
import facades.CustomerFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("bankcustomer")
public class BankCustomerResource {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    CustomerFacade cf = CustomerFacade.getFacadeExample(emf);
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("/populate") //used to populate the database
    public String populate() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new BankCustomer("John", "Johnson", "100", 1000, 1, "Nice"));
            em.persist(new BankCustomer("Knud", "Knudsen", "101", 500, 2, "Mindre nice"));
            em.persist(new BankCustomer("Line", "Linesen", "102", 27.25, 3, "Hva faen"));
            em.persist(new BankCustomer("Anders", "Andersen", "1012770", -5, 10, "Ud"));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return "Succesfully populated the database";
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCustomerById(@PathParam("id") int id) {
        return gson.toJson(cf.getCustomerByID(id));
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllCustomers() {
        return gson.toJson(cf.getAllBankCustomers());
    }
}
