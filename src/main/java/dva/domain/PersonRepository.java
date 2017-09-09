package dva.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * Created by kar on 7/9/17.
 */
interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
    Optional<Person> findByName(String name);
}
