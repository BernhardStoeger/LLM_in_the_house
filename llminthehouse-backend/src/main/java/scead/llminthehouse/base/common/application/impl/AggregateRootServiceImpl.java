package scead.llminthehouse.base.common.application.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import scead.llminthehouse.base.common.application.AggregateRootService;
import scead.llminthehouse.base.domain.businessobject.AggregateRootEntity;
import scead.llminthehouse.base.domain.repository.AggregateRootRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.apache.commons.lang3.Validate.noNullElements;

public abstract class AggregateRootServiceImpl<AGGREGATE extends AggregateRootEntity>
    implements AggregateRootService<AGGREGATE>
{
    @Autowired
    protected AggregateRootRepository<AGGREGATE, Long> repository;

    @Override
    @Transactional(readOnly = true)
    public AGGREGATE load(UUID uuid)
    {
        Objects.requireNonNull(uuid);
        return ensureNotNull(repository.findOneByUuid(uuid), uuid.toString());
    }

    @Override
    @Transactional
    public AGGREGATE save(AGGREGATE entity)
    {
        Objects.requireNonNull(entity);
        entity = repository.save(entity);

        return entity;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUuid(UUID uuid)
    {
        Objects.requireNonNull(uuid);

        return repository.existsByUuid(uuid);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public void deleteByUuidIn(List<UUID> uuids)
    {
        Objects.requireNonNull(uuids);
        noNullElements(uuids);

        repository.deleteByUuidIn(uuids);
    }

    /**
     * Checks if bo is not null, otherwise throws {@link EntityNotFoundException}
     *
     * @param entity     bo to check
     * @param identifier identifier to include in exception
     * @return bo
     * @throws EntityNotFoundException if bo is null
     */
    protected AGGREGATE ensureNotNull(AGGREGATE entity, String identifier)
    {
        if (entity == null)
        {
            throw new EntityNotFoundException(identifier);
        }
        return entity;
    }

    /**
     * Checks if bo is present, otherwise throws {@link EntityNotFoundException}
     *
     * @param entity     Optional bo
     * @param identifier identifier to include in exception
     * @return unwrapped bo
     * @throws EntityNotFoundException if bo is not present
     */
    protected AGGREGATE ensureNotNull(Optional<AGGREGATE> entity, String identifier)
    {
        return ensureNotNull(entity.orElse(null), identifier);
    }
}
