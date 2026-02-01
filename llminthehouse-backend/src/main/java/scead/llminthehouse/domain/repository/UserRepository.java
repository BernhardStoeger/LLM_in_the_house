package scead.llminthehouse.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import scead.llminthehouse.base.domain.repository.AggregateRootRepository;
import scead.llminthehouse.domain.businessobject.User;
import java.util.Optional;

public interface UserRepository extends AggregateRootRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
