package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name = "Customers.deleteAllRows", query = "DELETE from Customers p where p.id > 0"),
    @NamedQuery(name = "Customers.getAllPersons", query = "SELECT p FROM Customers p")})
public class Customers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private List<Order_> orders = new ArrayList<>();

    public Customers() {
    }

    public Customers(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public List<Order_> getOrders() {
        return orders;
    }

    public void setOrders(List<Order_> orders) {
        this.orders = orders;
    }
    
    public void addOrder(Order_ order) {
        this.orders.add(order);
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Customers{" + "id=" + id + ", name=" + name + ", email=" + email + ", orders=" + orders + '}';
    }

    
}
