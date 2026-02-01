package scead.llminthehouse.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import scead.llminthehouse.domain.businessobject.Conversation;
import scead.llminthehouse.domain.businessobject.Message;
import scead.llminthehouse.domain.businessobject.User;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByUser(User user);
    List<Message> findTop50ByOrderByTimestampDesc();
    List<Message> findByConversationOrderByTimestampAsc(Conversation conversation);
}
