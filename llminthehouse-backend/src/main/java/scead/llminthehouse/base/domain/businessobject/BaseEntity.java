package scead.llminthehouse.base.domain.businessobject;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    protected BaseEntity()
    {
    }

    protected BaseEntity(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
}
