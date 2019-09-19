package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

@Entity
@NamedQueries({
    @NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person"),
    @NamedQuery(name = "Person.getAllPersons", query = "SELECT p FROM Person p")})

public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String phone;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date created;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastEdited;

    public Person() {
    }

    /**
     *
     * Create existing person
     *
     * @param firstName
     * @param lastName
     * @param phone
     * @param created
     * @param lastEdited
     */
    public Person(String firstName, String lastName, String phone, Date created, Date lastEdited) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.created = created;
        this.lastEdited = lastEdited;
    }

    /**
     * Create brand new person
     *
     * @param firstName
     * @param lastName
     * @param phone
     */
    public Person(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.created = new Date();
        this.lastEdited = new Date();
    }

    /**
     * Get the value of phone
     *
     * @return the value of phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set the value of phone
     *
     * @param phone new value of phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Get the value of created
     *
     * @return the value of created
     */
    public Date getCreated() {
        return created;
    }

////    /**
////     * Set the value of created
////     *
////     * @param created new value of created
////     */
////    public void setCreated(Date created) {
////        this.created = created;
////    }
    /**
     * Get the value of lastEdited
     *
     * @return the value of lastEdited
     */
    public Date getLastEdited() {
        return lastEdited;
    }

    /**
     * Set the value of lastEdited
     *
     * @param lastEdited new value of lastEdited
     */
    public void setLastEdited(Date lastEdited) {
        this.lastEdited = lastEdited;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.created, other.created)) {
            return false;
        }
        if (!Objects.equals(this.lastEdited, other.lastEdited)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone + ", created=" + created + ", lastEdited=" + lastEdited + '}';
    }

}
