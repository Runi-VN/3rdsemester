
import entities.Customers;
import entities.OrderLine;
import entities.Order_;
import exceptions.EntityNotFoundException;
import exceptions.MissingInputException;
import facades.ICustomerFacadeImpl;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 * I couldn't get testing to work, see my documentation. This is what I did
 * instead.
 *
 * @author runi1
 */
public class main {

    public static void main(String[] args) throws MissingInputException, EntityNotFoundException {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        ICustomerFacadeImpl facade = ICustomerFacadeImpl.getFacadeExample(emf);

        Customers testCustomer = new Customers("John Mogensen", "johnmogensen@måge.dk");
        
        facade.createItemType("Milk", "It's from Cows", 15); //createItemType works
        facade.createItemType("Eggs", "Chickens lay these", 30);
        facade.createItemType("Bread", "idk where this is from", 45);

        System.out.println("ITEMTYPE 1 :" + facade.findItemType(1L).getName()); //findItemType works
        
        facade.createCustomer(testCustomer.getName(), testCustomer.getEmail()); //createCustomer works
        System.out.println("CUSTOMER: " + facade.findCustomer(1L).getName()); //findCustomer works
        System.out.println("ALL CUSTOMERS: " + facade.getAllCustomers()); //getAllCustomers works
        facade.addOrderToCustomer(testCustomer, new Order_()); //addOrderToCustomer works
        Order_ o1 = facade.allOrderFromCustomer(testCustomer).get(0);
        facade.addOrderLineToOrder(new OrderLine(15, facade.findItemType(1)), o1);

    }

}
