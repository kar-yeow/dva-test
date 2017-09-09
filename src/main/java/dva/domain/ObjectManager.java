package dva.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by kar on 7/9/17.
 */
@Transactional
@Service
public class ObjectManager {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ClaimRepository claimRepository;

    ObjectManager() {}

    public Person createPerson(String name, String phone, String email) {
        return personRepository.save(new Person(name, phone, email));
    }

    public Claim addClaimToPerson(Person person, String number, String description, Double amount) {
        return claimRepository.save(new Claim(person, number, description, amount));
    }

    public Person findPersonByName(String name) {
        Optional<Person> o = personRepository.findByName(name);
        return o.isPresent() ? o.get() : null;
    }

    public Page<Person> findAll(Pageable pageable) {

        Assert.notNull(pageable, "Pageable must not be null!");

        return personRepository.findAll(pageable);
    }

}
