package scead.llminthehouse.domain.businessobject;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scead.llminthehouse.base.domain.businessobject.AggregateRootEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends AggregateRootEntity
{
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
}
