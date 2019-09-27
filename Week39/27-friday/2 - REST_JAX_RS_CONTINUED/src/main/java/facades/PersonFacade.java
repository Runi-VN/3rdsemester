package facades;

import java.util.List;
import entities.Person;
import exceptions.MissingInputException;
import exceptions.PersonNotFoundException;

/**
 *
 * @author runi1
 */
public interface PersonFacade {

    public Person addPerson(String fName, String lName, String phone) throws MissingInputException;

    public Person deletePerson(int id) throws PersonNotFoundException;

    public Person getPerson(int id) throws PersonNotFoundException;

    public List<Person> getAllPersons() throws PersonNotFoundException;

    public Person editPerson(Person p) throws MissingInputException, PersonNotFoundException;

}
