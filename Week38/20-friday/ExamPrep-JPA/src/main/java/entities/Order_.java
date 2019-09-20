package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name = "Order_.deleteAllRows", query = "DELETE from Order_"),
    @NamedQuery(name = "Order_.getByCustomer", query = "SELECT o FROM Order_ o WHERE o.customer = :customer")
})

/**
 * "Order" is not a name that can be used.
 */
public class Order_ implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderID;
    @ManyToOne
    private Customers customer;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<OrderLine> orderLines = new ArrayList();

    public Order_() {
    }

    public Order_(Customers customer) {
        this.customer = customer;
    }

    public void addOrderLine(OrderLine orderLine) {
        this.orderLines.add(orderLine);
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(int id) {
        this.orderID = id;
    }

    @Override
    public String toString() {
        return "Order_{" + "orderID=" + orderID + ", customer=" + customer + ", orderLines=" + orderLines + '}';
    }
    

}
