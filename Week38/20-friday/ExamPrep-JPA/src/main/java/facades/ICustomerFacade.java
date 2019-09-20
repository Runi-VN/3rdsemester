package facades;

import entities.Customers;
import entities.ItemType;
import entities.OrderLine;
import entities.Order_;
import exceptions.EntityNotFoundException;
import exceptions.MissingInputException;
import java.util.List;

/**
 *
 * @author runi1
 */
public interface ICustomerFacade {
    
    public Customers createCustomer(String name, String email) throws MissingInputException;
    
    public Customers findCustomer(Long id) throws EntityNotFoundException;
    
    public List<Customers> getAllCustomers() throws EntityNotFoundException;
    
    public ItemType createItemType(String name, String description, int price) throws MissingInputException;
    
    public ItemType findItemType(long id) throws EntityNotFoundException;
    
    public Customers addOrderToCustomer(Customers customer, Order_ order) throws MissingInputException, EntityNotFoundException;
    
    public Order_ addOrderLineToOrder(OrderLine orderLine, Order_ order) throws MissingInputException, EntityNotFoundException;
    
    public List<Order_> allOrderFromCustomer(Customers customer) throws EntityNotFoundException, MissingInputException;
    
    public int totalPriceOfOrder(Order_ order) throws MissingInputException;
    
}
