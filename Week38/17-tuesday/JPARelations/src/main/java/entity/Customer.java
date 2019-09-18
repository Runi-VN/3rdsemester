package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.eclipse.persistence.jpa.config.Cascade;

/**
 *
 * @author runi1
 */
@Entity
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    //@Column(name = "CUSTOMER_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //will stay as Long for exercise 2
    private String firstName;
    private String lastName;

    //@OneToOne
    //@OneToOne
    //private Address address;
//    @OneToMany(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "CUSTOMER_ID")
    //@OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Address> addresses = new ArrayList();

    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
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

    /* Below contains exercise 1 /*
//    @ElementCollection
//    @CollectionTable(
//            name = "hobbies",
//            joinColumns = @JoinColumn(name = "CUSTOMER_ID")
//    )
//    @Column(name = "HOBBY")
//    private List<String> hobbies = new ArrayList();
//
//    @ElementCollection(fetch = FetchType.LAZY)
//    @MapKeyColumn(name = "PHONE")
//    @Column(name = "Description")
//    private Map<String, String> phones = new HashMap();
//
//    public void addPhone(String phoneNo, String description) {
//        this.phones.put(description, phoneNo);
//    }
//
//    public String getPhoneDescription(String phoneNo) {
//        return this.phones.get(phoneNo);
//    }
//
//    /**
//     * Get the value of hobbies
//     *
//     * @return the value of hobbies
//     */
//    public List getHobbies() {
//        return hobbies;
//    }
//
//    /**
//     * Set the value of hobbies
//     *
//     * @param hobbies new value of hobbies
//     */
//    public void setHobbies(List hobbies) {
//        this.hobbies = hobbies;
//    }
//
//    /**
//     * Adds an hobby to -this- hobbie-list.
//     *
//     * @param hobby
//     */
//    public void addHobby(String hobby) {
//        this.hobbies.add(hobby);
//    }
}
