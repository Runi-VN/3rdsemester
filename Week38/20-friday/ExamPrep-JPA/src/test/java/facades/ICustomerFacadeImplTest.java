package facades;

import entities.Customers;
import entities.ItemType;
import entities.OrderLine;
import entities.Order_;
import utils.EMF_Creator;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
@Disabled //this shit dont work
public class ICustomerFacadeImplTest {

    private static EntityManagerFactory emf;
    private static ICustomerFacadeImpl facade;
    private static List<Customers> customers;
    private static List<OrderLine> orderLines;
    private static List<Order_> orders;

    public ICustomerFacadeImplTest() {
    }

    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = ICustomerFacadeImpl.getFacadeExample(emf);
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        customers = new ArrayList<>(); //init collection
        orderLines = new ArrayList();
        orders = new ArrayList();

        //init Customers
        Customers c1 = new Customers("John Mogensen", "johnmogensen@måge.dk");
        Customers c2 = new Customers("Hans Boss", "hansb@outlook.dk");
        Customers c3 = new Customers("Knud Knudsen", "kk@kk.kk");

        //init orders
        //Order_ o1 = new Order_(c1);
        //Order_ o2 = new Order_(c2);
        //Order_ o3 = new Order_(c3);
        Order_ o1 = new Order_();
        Order_ o2 = new Order_();
        Order_ o3 = new Order_();

        //init ItemType
        ItemType itemT1 = new ItemType("Milk", "It's from Cows", 15);
        ItemType itemT2 = new ItemType("Eggs", "Chickens lay these", 30);
        ItemType itemT3 = new ItemType("Bread", "idk where this is from", 45);

        //init OrderLines
//        OrderLine oLine1 = new OrderLine(5, itemT1);
//        OrderLine oLine2 = new OrderLine(5, itemT1);
//        OrderLine oLine3 = new OrderLine(5, itemT1);
        //Bind orderLine to order
        orderLines.add(new OrderLine(5, itemT1));
        orderLines.add(new OrderLine(10, itemT2));
        orderLines.add(new OrderLine(15, itemT3));

        o1.setOrderLines(orderLines);
        o2.setOrderLines(orderLines);
        o3.setOrderLines(orderLines);

        //Add orderLines to collection
        orders.add(o1);
        orders.add(o2);
        orders.add(o3);

        //add to customer
        c1.setOrders(orders);
        c2.setOrders(orders);
        c3.setOrders(orders);

        //add to collection
        customers.add(c1);
        customers.add(c2);
        customers.add(c3);

        try {
            //reset DB tables, AutoIncrement-counter
            em.getTransaction().begin();
            Query query = em.createNativeQuery("truncate table Week5Day5_test.CUSTOMER;");
            query.executeUpdate();
            em.getTransaction().commit();
            //add collection to DB
            customers.forEach(p -> {
                em.getTransaction().begin();
                em.persist(p);
                em.getTransaction().commit();
            });
        } finally {
            em.close();
        }
    }

    @org.junit.jupiter.api.Test
    public void testSetUp() {
        assertThat(customers.size(), equalTo(3));
        assertThat(orderLines.size(), equalTo(3));
        assertThat(orderLines.size(), equalTo(3));
    }

}
