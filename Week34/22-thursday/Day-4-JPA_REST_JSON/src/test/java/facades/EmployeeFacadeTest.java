package facades;

import entities.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author runin
 */
@Ignore
public class EmployeeFacadeTest {

    private static EmployeeFacade ef;
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static List<Employee> employees;

    public EmployeeFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("putest");
        em = emf.createEntityManager();
        ef = EmployeeFacade.getEmployeeFacade(emf);

        Employee e1 = new Employee("Knud Knudsen", "Knudvej 42", 42000);
        Employee e2 = new Employee("Per Persen", "Pervej 122", 41999);
        Employee e3 = new Employee("Bo Bosen", "Bovej 23", 42001);
        Employee e4 = new Employee("William Williamsen", "Williamvej 8", 42001);
        Employee e5 = new Employee("William Williamsen", "Testvej 8", 1);
        employees = new ArrayList<>();
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        employees.add(e4);
        employees.add(e5);

        try {
            System.out.println("SetupClass kører");
            em.getTransaction().begin();
            em.persist(e1);
            em.persist(e2);
            em.persist(e3);
            em.persist(e4);
            em.persist(e5);
            em.getTransaction().commit();
            System.out.println(e1);
            System.out.println(e2);
            System.out.println(e3);
            System.out.println(e4);
            System.out.println(e5);
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    /**
     * Test of getEmployeesByName method, of class EmployeeFacade.
     */
    @Test
    public void testGetEmployeesByName() {
        System.out.println("getEmployeesByName");
        String name = "William Williamsen";
        EmployeeFacade instance = ef;
        List<Employee> expResult = new ArrayList();
        expResult.add(new Employee("William Williamsen", "Williamvej 8", 42001));
        expResult.add(new Employee("William Williamsen", "Testvej 8", 1));
        List<Employee> result = instance.getEmployeesByName(name);
        assertEquals(expResult.size(), result.size());
        //assertEquals(expResult, result);
    }

    /**
     * Test of getEmployeeByID method, of class EmployeeFacade.
     */
    @Test
    public void testGetEmployeeByID() {
        System.out.println("getEmployeeByID");
        long id = 3;
        EmployeeFacade instance = ef;
        Employee expResult = new Employee("Bo Bosen", "Bovej 23", 42001);
        Employee result = instance.getEmployeeByID(id);
        assertEquals(expResult.getName(), result.getName());
        assertEquals(expResult.getSalary(), result.getSalary());
        assertEquals(expResult.getAddress(), result.getAddress());
        //assertEquals(expResult, result);
    }

    /**
     * Test of getAllEmployees method, of class EmployeeFacade.
     */
    @Test
    public void testGetAllEmployees() {
        System.out.println("getAllEmployees");
        EmployeeFacade instance = ef;
        List<Employee> expResult = employees;
        List<Employee> result = instance.getAllEmployees();
        assertEquals(expResult.size(), result.size());
        //assertEquals(expResult, result);
    }

    /**
     * Test of getEmployeesWithHighestSalary method, of class EmployeeFacade.
     */
    @Test
    public void testGetEmployeesWithHighestSalary() {
        System.out.println("getEmployeesWithHighestSalary");
        EmployeeFacade instance = ef;
        //List<Employee> sortedEmployees = employees;
        //Collections.sort(sortedEmployees, (Employee e1, Employee e2) -> e2.getSalary() - e1.getSalary());
        List<Employee> sortedEmployees = new ArrayList<>();
        sortedEmployees.add(employees.get(2)); //both 42001 salary
        sortedEmployees.add(employees.get(3)); //both 42001 salary
        List<Employee> expResult = sortedEmployees;
        List<Employee> result = instance.getEmployeesWithHighestSalary();
        assertEquals(2, result.size()); //ensure only two results.
        assertEquals(expResult.get(0).getSalary(), result.get(0).getSalary());
        assertEquals(expResult.get(1).getSalary(), result.get(1).getSalary());
//        assertEquals(expResult.get(2).getSalary(), result.get(2).getSalary());
//        assertEquals(expResult.get(3).getSalary(), result.get(3).getSalary());
//        assertEquals(expResult.get(4).getSalary(), result.get(4).getSalary());
        //assertEquals(expResult, result);
    }

    /**
     * Test of createEmployee method, of class EmployeeFacade.
     */
    @Test
    public void testzCreateEmployee() { //added a z to enzure the test runs last.
        System.out.println("createEmployee");
        String name = "Per Persen";
        String address = "Pervej 122";
        int salary = 41999;
        EmployeeFacade instance = ef;
        Employee expResult = employees.get(1);
        Employee result = instance.createEmployee(name, address, salary);
        assertNotNull(result.getId());
        assertNotNull(expResult.getId());
        assertEquals(expResult.getName(), result.getName());
        assertEquals(expResult.getAddress(), result.getAddress());
        assertEquals(expResult.getSalary(), result.getSalary());
        //assertEquals(expResult, result); 
    }

}
