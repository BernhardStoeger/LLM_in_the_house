package scead.llminthehouse.domain.repository;

import scead.llminthehouse.base.domain.repository.AggregateRootRepository;
import scead.llminthehouse.domain.businessobject.Conversation;
import scead.llminthehouse.domain.businessobject.User;
import java.util.List;

public interface ConversationRepository extends AggregateRootRepository<Conversation, Long> {
    List<Conversation> findByParticipantsContaining(User user);
}
