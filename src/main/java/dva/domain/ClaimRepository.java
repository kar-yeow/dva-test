package dva.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * Created by kar on 8/9/17.
 */
interface ClaimRepository extends PagingAndSortingRepository<Claim, Long> {
    Optional<Claim> findByNumber(String number);
}
