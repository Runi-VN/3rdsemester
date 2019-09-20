package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQuery(name = "ItemType.deleteAllRows", query = "DELETE from ItemType")
public class ItemType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private int price;

    @OneToOne
    private OrderLine orderLines;

    public ItemType() {
    }

    public ItemType(String name, String description, int price) {
        this.name = name;
        this.description = description;
        if (orderLines != null || orderLines.getQuantity() != 0) {
            this.price = this.orderLines.getQuantity() * price;
        } else {
            this.price = price;
        }

    }

    public OrderLine getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(OrderLine orderLines) {
        this.orderLines = orderLines;
    }

    /**
     * Get the value of price
     *
     * @return the value of price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Set the value of price
     *
     * @param price new value of price
     */
    public void setPrice(int price) {
        this.price = this.orderLines.getQuantity() * price;
    }

    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value of description
     *
     * @param Description new value of description
     */
    public void setDescription(String Description) {
        this.description = Description;
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
     * @param Name new value of name
     */
    public void setName(String Name) {
        this.name = Name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ItemType{" + "id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", orderLines=" + orderLines + '}';
    }

}
