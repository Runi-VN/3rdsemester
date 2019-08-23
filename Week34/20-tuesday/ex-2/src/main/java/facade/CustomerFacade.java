package facade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author runin
 */
public class CustomerFacade {

    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    private CustomerFacade() {
    }

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    public Customer findByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer c = em.find(Customer.class, (long) id); //needs to be long as we are storing IDs as Long
            return c;
        } finally {
            em.close();
        }
    }

    List<Customer> findByLastName(String lastName) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query
                    = em.createQuery("SELECT c FROM Customer c WHERE c.lastName = :lastName", Customer.class).setParameter("lastName", lastName);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    int getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            Query q1 = em.createQuery("SELECT COUNT(c) FROM Customer c");

            return (int) ((Long) q1.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    List<Customer> allCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query
                    = em.createQuery("Select c FROM Customer c", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }

    }

    Customer addCustomer(String fName, String lName) {
        Customer c = new Customer(fName, lName);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
        } finally {
            em.close();
        }
    }

//    public static void main(String[] args) {
//        //CustomerFacade cf = new CustomerFacade();
//        CustomerFacade cf = CustomerFacade.getCustomerFacade(Persistence.createEntityManagerFactory("pu"));
//
//        cf.populate();
//        System.out.println("---Created Users (populated)---");
//        cf.allCustomers().forEach(el -> System.out.println(el.getFirstName() + " " + el.getLastName()));
//        System.out.println("---Printed all users---");
//        System.out.println("@@@ USER COUNT: " + cf.getNumberOfCustomers() + " @@@");
//        System.out.println("---Printed user count---");
//        System.out.println("Users named \"Testie\" (Lastname):");
//        cf.findByLastName("Testie").forEach(rs -> System.out.println(rs.getId() + " " + rs.getLastName() + ", " + rs.getFirstName()));
//        System.out.println("---Printed all users named 'Testie'---");
//        System.out.println("Searching users by ID for first 3 IDs");
//        for (int i = 1; i <= 3; i++) {
//            Customer c = cf.findByID(i);
//            System.out.println("User #" + c.getId()
//                    + " |  User name: " + c.getFirstName() + " " + c.getLastName()
//                    + " |  Creation date: " + c.getCreated());
//        }
//        System.out.println("---Printed first 3 users based on their IDs.");
//        System.out.println("\n        DONE\n");
//    }

    public void populate() {
        addCustomer("Test", "Testson");
        addCustomer("Testdrengen", "Testerson");
        addCustomer("Testpigen", "Testie");
        addCustomer("Testdamen", "Testie");
    }

}
