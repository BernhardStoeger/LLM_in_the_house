package scead.llminthehouse.base.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import scead.llminthehouse.base.domain.businessobject.AggregateRootEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@NoRepositoryBean
public interface AggregateRootRepository<AGGREGATE extends AggregateRootEntity, ID extends Serializable>
    extends JpaRepository<AGGREGATE, ID>
{
    AGGREGATE getOneByUuid(UUID uuid);

    /**
     * Finds an aggregate by it's UUID
     *
     * @param uuid the UUID of the aggregate root
     * @return the aggregate root if found
     */
    Optional<AGGREGATE> findOneByUuid(UUID uuid);

    /**
     * Finds multiple Aggregates through a List of UUIDs
     *
     * @param uuids the UUIDs of the aggregate roots to find
     * @return the List of aggregate roots if found
     */
    List<AGGREGATE> findAllByUuidIn(List<UUID> uuids);

    /**
     * Finds multiple Aggregates through a Set of UUIDs
     *
     * @param uuids the UUIDs of the aggregate roots to find
     * @return the Set of aggregate roots if found
     */
    Set<AGGREGATE> findAllByUuidIn(Set<UUID> uuids);

    /**
     * @param uuid the UUID
     * @return true if an bo with the given uuid exists
     */
    boolean existsByUuid(UUID uuid);

    /**
     * Deletes all entities with the given uuids
     *
     * @param uuids the list of uuids
     */
    void deleteByUuidIn(List<UUID> uuids);
}