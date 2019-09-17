package manual_tests;

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

        //Setting hobbies
        c1.addHobby("Bicycling");
        c1.addHobby("Kickboxing");
        c2.addHobby("Parkour");
        c2.addHobby("Gaming");
        c3.addHobby("Nothing");
        c3.addHobby("Depression");

        //adding now-updated customers to collection of Entities
        customers.add(c1);
        customers.add(c2);
        customers.add(c3);

        //Persisting collection of Entities
        customers.forEach(customer -> tester.persist(customer));
    }

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

}
