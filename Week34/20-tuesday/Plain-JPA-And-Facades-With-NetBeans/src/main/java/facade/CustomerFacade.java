package facade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author runin
 */
public class CustomerFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    public Customer createCustomer(Customer c) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        return c;
    }

    public List<Customer> getAllCustomers() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT c FROM Customer c").getResultList();
    }

    public static void main(String[] args) {
        CustomerFacade cf = new CustomerFacade();
//        cf.createCustomer(new Customer(4L,"Hans Hansen", "Vejen 1"));
//        cf.createCustomer(new Customer(5L, "Niels Nielsen", "Gadekæret 2"));
//        cf.createCustomer(new Customer(6L, "Poul Poulsen", "Mindevej 3"));
        cf.createCustomer(new Customer("Hans Hansen", "Vejen 1"));
        cf.createCustomer(new Customer("Niels Nielsen", "Gadekæret 2"));
        cf.createCustomer(new Customer("Poul Poulsen", "Mindevej 3"));
        cf.getAllCustomers().forEach(c -> System.out.println(c));
    }
}
