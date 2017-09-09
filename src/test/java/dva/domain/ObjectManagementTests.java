package dva.domain;

import org.junit.Test;
import org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by kar on 9/9/17.
 */
public class ObjectManagementTests extends AbstractIntegrationTests {
    @Autowired ObjectManager objectManager;

    @Test
    public void testCreatePersonOk() {
        Person person = objectManager.createPerson("John Smith", "0000 0000", "email@test.com");
        assertThat(objectManager.findPersonByName("John Smith") != null, is(true));
    }

    @Test
    public void testAddClaimOk() {
        Person person = objectManager.createPerson("John Smith 2", "0000 0000", "email@test.com");
        objectManager.addClaimToPerson(person, "Claim #1", "Claim test", 50.05);
        Person p = objectManager.findPersonByName("John Smith 2");
        assertThat(p.getClaims().size(), is(1));
        assertThat(p.getClaims().iterator().next().getPerson().getId(), is(p.getId()));
    }

    @Test
    public void testPersonNotFound() {
        Person person = objectManager.findPersonByName("Hello world");
        assertThat(person == null, is(true));
    }
}
