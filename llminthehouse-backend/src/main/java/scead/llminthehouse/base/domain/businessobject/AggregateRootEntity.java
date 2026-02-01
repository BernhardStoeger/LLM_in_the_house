package scead.llminthehouse.base.domain.businessobject;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.Hibernate;
import scead.llminthehouse.base.common.utils.DateUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class AggregateRootEntity extends BaseEntity
{
    @NotNull
    @Column(unique = true, nullable = false)
    private UUID uuid = UUID.randomUUID();

    @NotNull
    private LocalDateTime createdOn = DateUtils.now();

    protected AggregateRootEntity()
    {
    }

    protected AggregateRootEntity(UUID uuid)
    {
        this(null, uuid);
    }

    protected AggregateRootEntity(Long id, UUID uuid)
    {
        super(id);
        this.uuid = uuid == null ? UUID.randomUUID() : uuid;
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
        {
            return false;
        }
        AggregateRootEntity that = (AggregateRootEntity) o;
        return uuid.equals(that.uuid);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(uuid);
    }
}
