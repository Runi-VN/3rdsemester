package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author runin
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerFacadeTest {

    private static CustomerFacade cf;
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static List<BankCustomer> customers;
    private static List<CustomerDTO> customerDTOs;

    public CustomerFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("pu");
        em = emf.createEntityManager();
        cf = CustomerFacade.getFacadeExample(emf);

        //Collections
        customers = new ArrayList();
        customers.add(new BankCustomer("John", "Johnson", "100", 1000));
        customers.add(new BankCustomer("Knud", "Knudsen", "101", 500));
        customers.add(new BankCustomer("Line", "Linesen", "102", 27.25));
        customers.add(new BankCustomer("Anders", "Andersen", "1012770", -5));

        //Database
        try {
            System.out.println("SetupClass kører");

            for (BankCustomer c : customers) {
                em.getTransaction().begin();
                em.persist(c);
                em.getTransaction().commit();
            }
//            em.persist(new BankCustomer("John", "Johnson", "100", 1000));
//            em.persist(new BankCustomer("Knud", "Knudsen", "101", 500));
//            em.persist(new BankCustomer("Line", "Linesen", "102", 27.25));
//            em.persist(new BankCustomer("Anders", "Andersen", "1012770", -5));

        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        customerDTOs = new ArrayList<>();
        for (BankCustomer c : customers) {
            customerDTOs.add(new CustomerDTO(c));
        }
        //sort collections
        Collections.sort(customerDTOs, (CustomerDTO c1, CustomerDTO c2) -> c1.getCustomerID() - c2.getCustomerID());
        //Collections.sort(customers, (BankCustomer c1, BankCustomer c2) -> c1.getId().compareTo(c2.getId()));

    }

    @Before
    public void setUp() {

    }

    /**
     * Test of getCustomerByID method, of class CustomerFacade.
     */
    @Test
    public void testGetCustomerByID() {
        System.out.println("getCustomerByID");
        int id = 1;
        CustomerFacade instance = cf;
        CustomerDTO expResult = customerDTOs.get(0); //id 1 must be at space 0 because I sorted it
        CustomerDTO result = instance.getCustomerByID(id);
        System.out.println("expresult: " + expResult);
        System.out.println("result: " + result);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCustomerByName method, of class CustomerFacade.
     */
    @Test
    public void testGetCustomerByName() {
        System.out.println("getCustomerByName");
        String name = "Knudsen";
        CustomerFacade instance = cf;
        List<CustomerDTO> expResult = new ArrayList<>();
        expResult.add(customerDTOs.get(1));
        List<CustomerDTO> result = instance.getCustomerByName(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of addCustomer method, of class CustomerFacade.
     */
    @Test
    public void testzAddCustomer()  { //add 'z' to ensure run last
        System.out.println("addCustomer");
        BankCustomer cust = (new BankCustomer("John", "Johnson", "100", 1000));
        CustomerFacade instance = cf;
        BankCustomer expResult = customers.get(0);
        BankCustomer result = instance.addCustomer(cust);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllBankCustomers method, of class CustomerFacade.
     */
    @Test
    public void testaGetAllBankCustomers() { //add 'a' to ensure run before 'z'
        System.out.println("getAllBankCustomers");
        CustomerFacade instance = cf;
        List<BankCustomer> expResult = customers;
        List<BankCustomer> result = instance.getAllBankCustomers();
        for (int i = 0; i < customers.size(); i++) {
            System.out.println("EXPRESULT: " + expResult.get(i));
            System.out.println("RESULT: " + result.get(i));
        }
        assertEquals(expResult, result);
    }

}
