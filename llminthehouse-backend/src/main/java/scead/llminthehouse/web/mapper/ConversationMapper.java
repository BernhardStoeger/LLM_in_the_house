package scead.llminthehouse.web.mapper;

import scead.llminthehouse.base.channel.web.dto.ConversationListWebDTO;
import scead.llminthehouse.base.channel.web.dto.ConversationWebDTO;
import scead.llminthehouse.base.channel.web.dto.MessageWebDTO;
import scead.llminthehouse.domain.businessobject.Conversation;
import scead.llminthehouse.domain.businessobject.Message;

import java.util.Collection;
import java.util.List;

public class ConversationMapper
{
    public static List<ConversationListWebDTO> mapConversationLists(List<Conversation> conversations)
    {
        return conversations.stream()
                .map(ConversationMapper::mapConversationList)
                .toList();
    }

    public static ConversationListWebDTO mapConversationList(Conversation conversation)
    {
        ConversationListWebDTO conversationListDTO = new ConversationListWebDTO();
        conversationListDTO.setId(conversation.getId());
        conversationListDTO.setUuid(conversation.getUuid());
        conversationListDTO.setTitle(conversation.getTitle());
        conversationListDTO.setCreatedAt(conversation.getCreatedOn());
        return conversationListDTO;
    }

    public static ConversationWebDTO mapConversation(Conversation conversation)
    {
        ConversationWebDTO conversationDTO = new ConversationWebDTO();
        conversationDTO.setId(conversation.getId());
        conversationDTO.setUuid(conversation.getUuid());
        conversationDTO.setTitle(conversation.getTitle());
        conversationDTO.setCreatedAt(conversation.getCreatedOn());
        conversationDTO.setUser(UserMapper.mapUser(conversation.getUser()));
        conversationDTO.setMessages(mapMessages(conversation.getMessages()));
        return conversationDTO;
    }

    public static List<MessageWebDTO> mapMessages(Collection<Message> messages)
    {
        return messages.stream()
                .map(ConversationMapper::mapMessage)
                .toList();
    }

    public static MessageWebDTO mapMessage(Message message)
    {
        MessageWebDTO messageDTO = new MessageWebDTO();
        messageDTO.setId(message.getId());
        messageDTO.setContent(message.getContent());
        messageDTO.setTimestamp(message.getTimestamp());
        messageDTO.setNumber(message.getNumber());
        messageDTO.setLlm(message.isLlm());
        return messageDTO;
    }
}
