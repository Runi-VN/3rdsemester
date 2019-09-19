package facades;

import entities.Person;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class PersonFacadeImpl implements PersonFacade {

    private static PersonFacadeImpl instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacadeImpl() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacadeImpl getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacadeImpl();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public long getPersonCount() {
        EntityManager em = emf.createEntityManager();
        try {
            return (long) em.createQuery("SELECT COUNT(r) FROM Person r").getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public Person getPerson(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Person.class, id);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Problem finding person: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Person> getAllPersons() {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Person.getAllPersons").getResultList();
        } catch (Exception ex) {
            throw new IllegalArgumentException("Problem finding all persons: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Person addPerson(String fName, String lName, String phone) {
        if (fName != null && lName != null && phone != null
                && !fName.isEmpty() && !lName.isEmpty() && !phone.isEmpty()) {
            EntityManager em = getEntityManager();
            try {
                Person target = new Person(fName, lName, phone);
                em.getTransaction().begin();
                em.persist(target);
                em.getTransaction().commit();
                return target;
            } catch (Exception ex) {
                em.getTransaction().rollback();
                throw new IllegalStateException("Problem adding person: " + ex.getMessage());
            } finally {
                em.close();
            }
        } else {
            throw new IllegalArgumentException("Input is wrong");
        }
    }

    @Override
    public Person deletePerson(int id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Person result = em.find(Person.class, id);
            em.remove(result);
            em.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new IllegalStateException("Problem deleting person: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Person editPerson(Person p) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            p.setLastEdited(new Date());
            em.merge(p);
            em.getTransaction().commit();
            return p;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new IllegalStateException("Problem editing person: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

}
