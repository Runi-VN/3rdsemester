package manual_tests;

import entity.Address;
import entity.Customer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author runi1
 */
public class Tester {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    public static void main(String[] args) {
        //init
        Tester tester = new Tester(); //to access persist method
        List<Customer> customers = new ArrayList();

        //Adding users
        Customer c1 = new Customer("firstName1", "lastName1");
        Customer c2 = new Customer("firstName2", "lastName2");
        Customer c3 = new Customer("firstName3", "lastName3");

        //Setting hobbies (exercise 1)
//        c1.addHobby("Bicycling");
//        c1.addHobby("Kickboxing");
//        c2.addHobby("Parkour");
//        c2.addHobby("Gaming");
//        c3.addHobby("Nothing");
//        c3.addHobby("Depression");
        //Setting phone numbers (exercise 1)
//        c1.addPhone("12345678", "home");
//        c1.addPhone("p3202582", "work");
//        c2.addPhone("93859285", "work");
//        c2.addPhone("12753986", "home");
//        c3.addPhone("73721237", "cellphone");
//        c3.addPhone("20202020", "contact-person");
/* */
        //creating addresses (exercise 2)
        Address a1 = new Address("Byvej 1", "Byen");
        Address a2 = new Address("Et sted hvor der bor flere", "En anden by");
        Address a3 = new Address("Et tredje sted", "Langt væk");

        //Setting addresses (exercise 3)
        c1.addAddress(a1);
        c1.addAddress(a2);
        c2.addAddress(a2);
        c3.addAddress(a2);
        c3.addAddress(a3);

        //setting customers on addresses (exercise 4)
//        a1.setCustomer(c1);
//        a2.setCustomer(c1);
//        a2.setCustomer(c2);
//        a2.setCustomer(c3);
//        a3.setCustomer(c3);
        //adding multiple addresses (exercise 5)
        a1.addCustomer(c1);
        a2.addCustomer(c1);
        a2.addCustomer(c2);
        a2.addCustomer(c3);
        a3.addCustomer(c3);

        //adding now-updated customers to collection of Entities
        customers.add(c1);
        customers.add(c2);
        customers.add(c3);

        //Persisting collection of Entities
        customers.forEach(customer -> tester.persist(customer));
    }

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        Persistence.generateSchema("pu", null);
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

}
