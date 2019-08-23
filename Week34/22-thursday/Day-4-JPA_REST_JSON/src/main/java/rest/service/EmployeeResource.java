package rest.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("employee")
public class EmployeeResource {

    public EmployeeResource() {
    }

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EmployeeFacade ef = EmployeeFacade.getEmployeeFacade(emf);

    @GET
    @Path("/populate") //used to populate the database
    public String populate() {
        Employee e1 = new Employee("Knud Knudsen", "Knudvej 42", 42000);
        Employee e2 = new Employee("Per Persen", "Pervej 122", 41999);
        Employee e3 = new Employee("Bo Bosen", "Bovej 23", 42001);
        Employee e4 = new Employee("William Williamsen", "Williamvej 8", 42001);
        Employee e5 = new Employee("William Williamsen", "Testvej 8", 1);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(e1);
            em.persist(e2);
            em.persist(e3);
            em.persist(e4);
            em.persist(e5);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return "Succesfully populated the database";
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllEmployees() {
        List<Employee> employees = ef.getAllEmployees();
        List<EmployeeDTO> employeeDTOs = new ArrayList();
        for (Employee e : employees) {
            employeeDTOs.add(new EmployeeDTO(e));
        }
        return gson.toJson(employeeDTOs);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeById(@PathParam("id") Long id) {
//        Employee emp = ef.getEmployeeByID(id - 1); //Made by THA
//        EmployeeDTO empDTO = new EmployeeDTO(emp);
//        return Response.ok().entity(gson.toJson(empDTO)).build(); //what

        return gson.toJson(new EmployeeDTO(ef.getEmployeeByID(id)));
    }

    @GET
    @Path("/highestpaid")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHighestPaidEmployee() {
        List<Employee> employees = ef.getEmployeesWithHighestSalary();
        List<EmployeeDTO> employeeDTOs = new ArrayList();
        for (Employee e : employees) {
            employeeDTOs.add(new EmployeeDTO(e));
        }
        return gson.toJson(employeeDTOs);
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeByName(@PathParam("name") String name) {
        List<Employee> employees = ef.getEmployeesByName(name);
        List<EmployeeDTO> employeeDTOs = new ArrayList();
        for (Employee e : employees) {
            employeeDTOs.add(new EmployeeDTO(e));
        }
        return gson.toJson(employeeDTOs);
    }
}
