/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Customers;
import entities.ItemType;
import entities.OrderLine;
import entities.Order_;
import exceptions.EntityNotFoundException;
import exceptions.MissingInputException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 *
 * @author runi1
 */
public class ICustomerFacadeImpl implements ICustomerFacade {

    private static ICustomerFacadeImpl instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private ICustomerFacadeImpl() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static ICustomerFacadeImpl getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ICustomerFacadeImpl();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Customers createCustomer(String name, String email) throws MissingInputException {
        if (name == null || email == null
                || name.isEmpty() || email.isEmpty()) {
            throw new MissingInputException("Wrong input m8");
        }
        EntityManager em = getEntityManager();
        try {
            Customers target = new Customers(name, email);
            em.getTransaction().begin();
            em.persist(target);
            em.getTransaction().commit();
            return target;
        } finally {
            em.close();
        }
    }

    @Override
    public Customers findCustomer(Long id) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        try {
            Customers customer = em.find(Customers.class, id);
            if (customer != null) {
                return customer;
            }
            throw new EntityNotFoundException("No customer with provided id found");
        } finally {
            em.close();
        }
    }

    @Override
    public List<Customers> getAllCustomers() throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        try {
            List<Customers> result = em.createNamedQuery("Customers.getAllPersons").getResultList();
            if (result != null && !result.isEmpty()) {
                return result;
            }
            throw new EntityNotFoundException("Problem finding all customers");
        } finally {
            em.close();
        }
    }

    @Override
    public ItemType createItemType(String name, String description, int price) throws MissingInputException {
        if (name == null || description == null || price < 0
                || name.isEmpty() || description.isEmpty()) {
            throw new MissingInputException("Wrong input m8");
        }
        EntityManager em = getEntityManager();
        try {
            ItemType target = new ItemType(name, description, price);
            em.getTransaction().begin();
            em.persist(target);
            em.getTransaction().commit();
            return target;
        } finally {
            em.close();
        }
    }

    @Override
    public ItemType findItemType(long id) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        try {
            ItemType item = em.find(ItemType.class, id);
            if (item != null) {
                return item;
            }
            throw new EntityNotFoundException("No item with provided id found");
        } finally {
            em.close();
        }
    }

    @Override
    public Customers addOrderToCustomer(Customers customer, Order_ order) throws MissingInputException, EntityNotFoundException {
        if (customer == null || order == null
                || customer.getName() == null
                || customer.getEmail() == null
                || customer.getName().isEmpty()
                || customer.getEmail().isEmpty()) {
            throw new MissingInputException("WRONG INPUT MATE");
        }

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            customer.addOrder(order);
            em.merge(customer);
            em.getTransaction().commit();
            if (customer != null) {
                return customer;
            }
            throw new MissingInputException("NO success matey");

        } finally {
            em.close();
        }
    }

    @Override
    public Order_ addOrderLineToOrder(OrderLine orderLine, Order_ order) throws MissingInputException, EntityNotFoundException {
        if (orderLine == null || order == null
                || orderLine.getQuantity() < 0) {
            throw new MissingInputException("WRONG INPUT MATE");
        }

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            order.addOrderLine(orderLine);
            em.merge(order);
            em.getTransaction().commit();
            return order;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Order_> allOrderFromCustomer(Customers customer) throws EntityNotFoundException, MissingInputException {
        if (customer == null
                || customer.getName() == null || customer.getEmail() == null
                || customer.getName().isEmpty() || customer.getEmail().isEmpty()) {
            throw new MissingInputException("Customer is not correctly set.");
        }
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            List<Order_> result = em.createNamedQuery("Order_.getByCustomer", Order_.class)
                    .setParameter("customer", customer).getResultList();
            em.getTransaction().commit();
            return result;
        } catch (Exception e) {
            throw new EntityNotFoundException("Customer has no orders. (Or an error occured)");
        } finally {
            em.close();
        }
    }

    @Override
    public int totalPriceOfOrder(Order_ order) throws MissingInputException {
    }

}
