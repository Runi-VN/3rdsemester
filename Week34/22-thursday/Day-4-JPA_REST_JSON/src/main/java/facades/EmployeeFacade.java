package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private EmployeeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    public Employee getEmployeeByID(Long id) {
        EntityManager em = getEntityManager();
        try {
            Employee e = em.find(Employee.class, id); //needs to be long as we are storing IDs as Long
            return e;
        } finally {
            em.close();
        }
    }

    public List<Employee> getEmployeesByName(String name) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Employee> query
                    = em.createQuery("SELECT z FROM Employee z WHERE z.name = :name", Employee.class).setParameter("name", name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Employee> getAllEmployees() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Employee> query
                    = em.createQuery("Select z FROM Employee z", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Employee> getEmployeesWithHighestSalary() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Employee> query
                    = em.createQuery("SELECT z FROM Employee z WHERE z.salary = (SELECT MAX(e.salary) FROM Employee e)", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Employee createEmployee(String name, String address, int salary) {
        Employee e = new Employee(name, address, salary);
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
            return e;
        } finally {
            em.close();
        }
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}
