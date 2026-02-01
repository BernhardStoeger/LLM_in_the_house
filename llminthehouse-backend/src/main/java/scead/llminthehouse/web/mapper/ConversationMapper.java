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
        return new ConversationListWebDTO(
                conversation.getId(), conversation.getUuid(), conversation.getTitle(), conversation.getCreatedOn());
    }

    public static ConversationWebDTO mapConversation(Conversation conversation)
    {
        return new ConversationWebDTO(
                conversation.getId(), conversation.getUuid(), conversation.getTitle(), conversation.getCreatedOn(),
                UserMapper.mapUser(conversation.getUser()), ConversationMapper.mapMessages(conversation.getMessages()));
    }

    public static List<MessageWebDTO> mapMessages(Collection<Message> messages)
    {
        return messages.stream()
                .map(ConversationMapper::mapMessage)
                .toList();
    }

    public static MessageWebDTO mapMessage(Message message)
    {
        return new MessageWebDTO(
                message.getId(), message.getContent(), message.getTimestamp(), message.getNumber(), message.isLlm());
    }
}
