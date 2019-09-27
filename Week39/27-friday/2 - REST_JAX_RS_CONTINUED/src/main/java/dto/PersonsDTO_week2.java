package dto;

import entities.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author runi1
 */
public class PersonsDTO_week2 {
    
    List<PersonDTO_week2> all = new ArrayList();

    public PersonsDTO_week2(List<Person> personEntities) {
        personEntities.forEach((p) -> {
            all.add(new PersonDTO_week2(p));
        });
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
        final PersonsDTO_week2 other = (PersonsDTO_week2) obj;
        if (!Objects.equals(this.all, other.all)) {
            return false;
        }
        return true;
    }
    
    
}
