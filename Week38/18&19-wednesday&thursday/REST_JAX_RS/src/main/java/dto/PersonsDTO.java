package dto;

import entities.Person;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author runi1
 */
public class PersonsDTO {
    
    List<PersonDTO> all = new ArrayList();

    public PersonsDTO(List<Person> personEntities) {
        personEntities.forEach((p) -> {
            all.add(new PersonDTO(p));
        });
    }
}
