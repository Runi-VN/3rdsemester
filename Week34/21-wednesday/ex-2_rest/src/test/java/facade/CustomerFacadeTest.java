package facade;

import entity.Customer;
import static facade.CustomerFacade.getCustomerFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author runin
 */
public class CustomerFacadeTest {

    private static CustomerFacade cf;
    private static EntityManagerFactory emf;
    private static EntityManager em;

    public CustomerFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("putest");
        em = emf.createEntityManager();
        cf = getCustomerFacade(emf);

        Customer c1 = new Customer("UnitTester", "1");
        Customer c2 = new Customer("UnitTester", "2");

        try {
            System.out.println("SetupClass kører");
            em.getTransaction().begin();
            em.persist(c1);
            em.persist(c2);
            em.getTransaction().commit();

            System.out.println(c1);
            System.out.println(c2);
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    /**
     * Test of findByID method, of class CustomerFacade.
     */
    @Test
    public void testFindByID() {
        System.out.println("findByID");
        int id = 1;
        Customer expResult = new Customer("UnitTester", "1");
        expResult.setId(1L);
        Customer result = cf.findByID(id);
        assertEquals(expResult.getId(), result.getId());
    }

    /**
     * Test of findByLastName method, of class CustomerFacade.
     */
    @Test
    public void testFindByLastName() {
        System.out.println("findByLastName");
        String lastName = "1";
        CustomerFacade instance = cf;
        List<Customer> expResult = new ArrayList();
        Customer c = new Customer("UnitTester", "1");
        c.setId(1L);
        expResult.add(c);
        List<Customer> result = instance.findByLastName(lastName);
        assertEquals(expResult.get(0).getFirstName(), result.get(0).getFirstName());
        assertEquals(expResult.get(0).getLastName(), result.get(0).getLastName());
    }

    /**
     * Test of getNumberOfCustomers method, of class CustomerFacade.
     */
    @Test
    public void testGetNumberOfCustomers() {
        System.out.println("getNumberOfCustomers");
        CustomerFacade instance = cf;
        int expResult = 2;
        int result = instance.getNumberOfCustomers();
        assertEquals(expResult, result);
    }

    /**
     * Test of allCustomers method, of class CustomerFacade.
     */
    @Test
    public void testAllCustomers() {
        System.out.println("allCustomers");
        CustomerFacade instance = cf;
        List<Customer> expResult = new ArrayList();
        expResult.add(new Customer());
        expResult.add(new Customer());
        List<Customer> result = instance.allCustomers();
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of addCustomer method, of class CustomerFacade.
     */
    @Test
    public void testzAddCustomer() { //tests are run alphabetically (and numbered) so I used 'z' to ensure this test was run last (as to not corrupt other tests)
        System.out.println("addCustomer");
        String fName = "UnitTester";
        String lName = "3";
        CustomerFacade instance = cf;
        Customer expResult = new Customer("UnitTester", "3");
        expResult.setId(3L);
        Customer result = instance.addCustomer(fName, lName);
        assertEquals(expResult, result);
    }
}
