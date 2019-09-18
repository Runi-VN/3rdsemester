package facade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author runi1
 */
public class CustomerFacade {

    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    private CustomerFacade() {
    }

    /**
     *
     * @return an instance of this facade class.
     */
    public static CustomerFacade getFacadeExample() {
        if (instance == null) {
            emf = Persistence.createEntityManagerFactory("pu");
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Customer getCustomer(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Customer.class, (long) id);
        } finally {
            em.close();
        }
    }

    public List<Customer> getCustomers() { //(Check out the hints below)
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Customer c").getResultList();
        } finally {
            em.close();
        }
    }

    public Customer addCustomer(Customer cust) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
            return cust;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new IllegalStateException("Could not succeed in adding customer: " + ex.getMessage());
        }
    }

    /**
     * Not the requested one
     *
     * @param id
     */
    public void deleteCustomerVoid(int id) {
        EntityManager em = getEntityManager();
        try {
            em.createQuery("DELETE FROM Customer c where c.id = :id").setParameter("id", id).executeUpdate();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new IllegalStateException("Could not succeed in deleting customer: " + ex.getMessage());
        }
    }

    /**
     * The requested one.
     *
     * @param id
     * @return
     */
    public Customer deleteCustomer(int id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Customer result = em.find(Customer.class, (long) id);
            if (result != null) em.remove(result);
            em.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new IllegalStateException("Could not succeed in deleting customer: " + ex.getMessage());
        } finally{
            em.close();
        }
    }

    public Customer editCustomer(Customer cust) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(cust);
            em.getTransaction().commit();
            return cust;
        } catch (Exception ex){
            em.getTransaction().rollback();
            throw new IllegalStateException("Could not succeed in editing customer: " + ex.getMessage());
        }finally{
            em.close();
        }
    }

//    public void persist(Object object) {
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.persist(object);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
//            em.getTransaction().rollback();
//        } finally {
//            em.close();
//        }
//    }
}
