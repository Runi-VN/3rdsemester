package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

/**
 *
 * @author runi1
 */
@Entity
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "CUSTOMER_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @ElementCollection
    @CollectionTable(
            name = "hobbies",
            joinColumns = @JoinColumn(name = "CUSTOMER_ID")
    )
    @Column(name="HOBBY")
    private List<String> hobbies = new ArrayList();

    /**
     * Get the value of hobbies
     *
     * @return the value of hobbies
     */
    public List getHobbies() {
        return hobbies;
    }

    /**
     * Set the value of hobbies
     *
     * @param hobbies new value of hobbies
     */
    public void setHobbies(List hobbies) {
        this.hobbies = hobbies;
    }

    /**
     * Adds an hobby to -this- hobbie-list.
     *
     * @param hobby
     */
    public void addHobby(String hobby) {
        this.hobbies.add(hobby);
    }

    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Get the value of lastName
     *
     * @return the value of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the value of lastName
     *
     * @param lastName new value of lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the value of firstName
     *
     * @return the value of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the value of firstName
     *
     * @param firstName new value of firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
