package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author runin
 */
public class MakeTestData {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        try {
            em.getTransaction().begin();
            em.persist(new BankCustomer("John", "Johnson", "100", 1000));
            em.persist(new BankCustomer("Knud", "Knudsen", "101", 500));
            em.persist(new BankCustomer("Line", "Linesen", "102", 27.25));
            em.persist(new BankCustomer("Anders", "Andersen", "1012770", -5));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
