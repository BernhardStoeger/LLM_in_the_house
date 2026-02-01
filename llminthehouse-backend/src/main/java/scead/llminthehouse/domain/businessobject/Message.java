package scead.llminthehouse.domain.businessobject;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scead.llminthehouse.base.domain.businessobject.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
public class Message extends BaseEntity
{
    public Message(String content, int number, boolean llm) {
        this.content = content;
        this.number = number;
        this.llm = llm;
    }

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    @Column(nullable = false)
    private int number;

    private boolean llm = false;

}
