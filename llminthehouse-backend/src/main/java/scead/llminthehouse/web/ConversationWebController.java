package scead.llminthehouse.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import scead.llminthehouse.base.channel.web.ConversationApi;
import scead.llminthehouse.base.channel.web.dto.ConversationListWebDTO;
import scead.llminthehouse.base.channel.web.dto.ConversationWebDTO;
import scead.llminthehouse.base.channel.web.dto.MessageWebDTO;
import scead.llminthehouse.domain.businessobject.Conversation;
import scead.llminthehouse.domain.businessobject.Message;
import scead.llminthehouse.domain.businessobject.User;
import scead.llminthehouse.domain.service.ConversationService;
import scead.llminthehouse.domain.service.UserService;
import scead.llminthehouse.web.mapper.ConversationMapper;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/conversations")
public class ConversationWebController implements ConversationApi {

    private final ConversationService conversationService;

    private final UserService userService;

    public ConversationWebController(ConversationService conversationService, UserService userService) {
        this.conversationService = conversationService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ConversationWebDTO> createConversation(@RequestBody ConversationWebDTO conversationWebDTO)
    {
        Conversation conversation = conversationService.createConversation(
                conversationWebDTO.getTitle(), userService.getUser(conversationWebDTO.getUser().getUuid()));
        return ResponseEntity.ok(ConversationMapper.mapConversation(conversation));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ConversationWebDTO> getConversation(@PathVariable UUID uuid) {
        return ResponseEntity.ok(ConversationMapper.mapConversation(conversationService.getConversation(uuid)));
    }


    @GetMapping("/user/{useruuid}")
    public ResponseEntity<List<ConversationListWebDTO>> getConversationsForUser(@PathVariable UUID useruuid) {
        User user = userService.getUser(useruuid);
        return ResponseEntity.ok(ConversationMapper
                .mapConversationLists(conversationService.getConversationsForUser(user)));
    }

    @PostMapping("/{uuid}/message")
    public ResponseEntity<MessageWebDTO> addMessage(@PathVariable UUID uuid, @RequestBody MessageWebDTO messageWebDTO)
    {
        Message message = conversationService.addMessage(uuid, messageWebDTO.getContent(), false);
        return ResponseEntity.ok(ConversationMapper.mapMessage(message));
    }
}
