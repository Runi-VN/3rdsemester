package dto;

import entities.Person;
import java.util.Objects;

/**
 * This is meant for the addPerson-exercise that is not allowed to return ID
 *
 * @author runi1
 */
public class PersonDTO_create {

    private String fName;
    private String lName;
    private String phone;

    public PersonDTO_create() {
    }

    public PersonDTO_create(String fName, String lName, String phone) {
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
    }

    public PersonDTO_create(Person p) {
        this.fName = p.getFirstName();
        this.lName = p.getLastName();
        this.phone = p.getPhone();
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
     * Get the value of lName
     *
     * @return the value of lName
     */
    public String getlName() {
        return lName;
    }

    /**
     * Set the value of lName
     *
     * @param lName new value of lName
     */
    public void setlName(String lName) {
        this.lName = lName;
    }

    /**
     * Get the value of fName
     *
     * @return the value of fName
     */
    public String getfName() {
        return fName;
    }

    /**
     * Set the value of fName
     *
     * @param fName new value of fName
     */
    public void setfName(String fName) {
        this.fName = fName;
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
        final PersonDTO_create other = (PersonDTO_create) obj;
        if (!Objects.equals(this.fName, other.fName)) {
            return false;
        }
        if (!Objects.equals(this.lName, other.lName)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        return true;
    }
}
