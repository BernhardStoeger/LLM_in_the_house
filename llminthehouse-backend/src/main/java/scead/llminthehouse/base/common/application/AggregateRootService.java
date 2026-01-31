package scead.llminthehouse.base.common.application;

import jakarta.persistence.EntityNotFoundException;
import scead.llminthehouse.base.domain.businessobject.AggregateRootEntity;

import java.util.List;
import java.util.UUID;

/**
 * Service for all AggregateRoots.
 * Calls AggregateRoot.touch() on save to ensure the root bo is changed so that the version is incremented
 * <p>
 * Copied from FM-Technic
 */
public interface AggregateRootService<AGGREGATE extends AggregateRootEntity>
{
    /**
     * @param uuid The uuid of an existing bo.
     * @return The existing domain object with the given uuid.
     * @throws EntityNotFoundException if an bo with the given uuid cannot be found.
     */
    AGGREGATE load(UUID uuid);

    /**
     * @param entity Saves the bo and calls bo.touch() to ensure the version of the root bo will increment
     * @return The saved bo.
     */
    AGGREGATE save(AGGREGATE entity);

    /**
     * @param uuid the uuid to check
     * @return true if an bo with the given uuid exists
     */
    boolean existsByUuid(UUID uuid);

    /**
     * Delete all entities with given uuids and sends a EntityDeletedEvent.
     * @param uuids the uuids
     */
    void deleteByUuidIn(List<UUID> uuids);
}
