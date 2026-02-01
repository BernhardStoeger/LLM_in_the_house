package scead.llminthehouse.domain.businessobject;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import scead.llminthehouse.base.domain.businessobject.AggregateRootEntity;
import scead.llminthehouse.base.domain.businessobject.BaseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "conversations")
@Getter
@Setter
public class Conversation extends AggregateRootEntity
{
    public Conversation() {
    }

    public Conversation(String title, User user) {
        this.title = title;
        this.user = user;
    }

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id")
    @OrderBy("number")
    private List<Message> messages = new ArrayList<>();

    public int getNextMessageIndex()
    {
        return this.messages.size();
    }

    public Message addMessage(String content, boolean llm)
    {
        Message message = new Message(content, getNextMessageIndex(), llm);
        this.messages.add(message);
        return message;
    }


}
