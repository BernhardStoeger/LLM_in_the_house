package scead.llminthehouse.domain.service;

import org.springframework.stereotype.Service;
import scead.llminthehouse.domain.businessobject.Conversation;
import scead.llminthehouse.domain.businessobject.Message;
import scead.llminthehouse.domain.businessobject.User;
import scead.llminthehouse.domain.repository.ConversationRepository;
import java.util.List;
import java.util.UUID;

@Service
public class ConversationService
{
    private final ConversationRepository conversationRepository;

    public ConversationService(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    public Conversation createConversation(String title, User user)
    {
        Conversation conversation = new Conversation(title, user);
        conversation.setTitle(title);
        return conversationRepository.save(conversation);
    }

    public List<Conversation> getConversationsForUser(User user)
    {
        return conversationRepository.findAllByUser(user);
    }

    public Message addMessage(UUID conversationUUID, String content, boolean llm)
    {
        Conversation conversation = conversationRepository.getOneByUuid(conversationUUID);
        Message message = conversation.addMessage(content, llm);
        conversationRepository.save(conversation);
        return message;
    }

    public Conversation getConversation(UUID uuid) {
        return conversationRepository.getOneByUuid(uuid);
    }
}
