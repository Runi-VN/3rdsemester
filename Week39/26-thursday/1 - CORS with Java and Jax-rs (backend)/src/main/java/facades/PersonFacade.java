package facades;

import entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Person addPerson(String name, String occupation) {

        EntityManager em = getEntityManager();
        try {
            Person target = new Person(name, occupation);
            em.getTransaction().begin();
            em.persist(target);
            em.getTransaction().commit();
            return target;
        } finally {
            em.close();
        }
    }

    public Person getPerson(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Person.class, id);
        } finally {
            em.close();
        }
    }

    public Person editPerson(Person p) {

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

    public Person deletePerson(long id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Person result = em.find(Person.class, id);
            em.remove(result);
            em.getTransaction().commit();
            return result;
        } finally {
            em.close();
        }
    }
}
