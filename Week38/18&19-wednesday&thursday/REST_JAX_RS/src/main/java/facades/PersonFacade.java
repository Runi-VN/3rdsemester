package facades;

import java.util.List;
import entities.Person;

/**
 *
 * @author runi1
 */
public interface PersonFacade {

    public Person addPerson(String fName, String lName, String phone);

    public Person deletePerson(int id);

    public Person getPerson(int id);

    public List<Person> getAllPersons();

    public Person editPerson(Person p);

}
