package dva.domain;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by kar on 9/9/17.
 */
public class PersonRepositoryTests extends AbstractIntegrationTests {
    @Autowired PersonRepository personRepository;

    @Test
    public void testUpdatePerson() {
        Person person = personRepository.save(new Person("Delete Me", "0", "x@x"));
        person.setPhone("11111111");
        personRepository.save(person);
        Person p = personRepository.findOne(person.getId());
        assertThat(p.getPhone(), is("11111111"));
    }

    @Test
    public void testDeletePerson() {
        Person person = personRepository.save(new Person("Delete Me", "0", "x@x"));
        assertThat(personRepository.exists(person.getId()), is(true));
        personRepository.delete(person.getId());
        assertThat(personRepository.exists(person.getId()), is(false));
    }
}
