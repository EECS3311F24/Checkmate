package ca.yorku.checkmate.Controller;

import ca.yorku.checkmate.Model.chat.ChatMessage;
import ca.yorku.checkmate.Model.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoint is api/v1/chats
 * <br>
 * Controller for chat messages, providing a rest endpoint
 * that allows getting, creating, updating, or deleting chat messages.
 */
@RestController
@RequestMapping("api/v1/chats")
public class ChatController {
    private final ChatService service;

    @Autowired
    public ChatController(ChatService service) {
        this.service = service;
    }

    /**
     * URL: api/v1/chats
     * <br>
     * Gets all chat messages in the database.
     * @return A list of chat messages.
     */
    @GetMapping
    public List<ChatMessage> getChatMessages() {
        return service.getChatMessages();
    }

    /**
     * URL: api/v1/chats/{id}
     * <br>
     * Gets chat message by id in the database.
     * @param id The id of the chat message.
     * @return The chat message associated with the id if it exists.
     */
    @GetMapping("{id}")
    public ResponseEntity<ChatMessage> getChatById(@PathVariable("id") String id) {
        return service.getChatMessageById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * URL: api/v1/chats?boardId={boardId}
     * <br>
     * Gets chat messages by boardId in the database.
     * @param boardId The boardId of the chat messages.
     * @return The chat messages associated with the boardId if it exists.
     */
    @GetMapping(params = "boardId")
    public ResponseEntity<List<ChatMessage>> getChatByBoardId(String boardId) {
        List<ChatMessage> chatMessages = service.getChatMessageByBoardId(boardId);
        if (chatMessages.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(chatMessages);
    }

    /**
     * URL: api/v1/chats
     * <br>
     * Create a new chat message in the database.
     * @param chatMessage The chat message to be created.
     * @return A response entity with the message, informing client
     * with Http status 201 if created or 409 if not created.
     */
    @PostMapping
    public ResponseEntity<ChatMessage> createChatMessage(@RequestBody ChatMessage chatMessage) {
        if (!service.addChatMessage(chatMessage)) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(chatMessage, HttpStatus.CREATED);
    }

    /**
     * URL: api/v1/chats/{id}
     * <br>
     * Update a chat message by id in the database.
     * @param id The id of the chat message.
     * @param message The message to change to for the chat with given id.
     * @return A response entity with chat message, informing client
     * with Http status 200 if updated, 401 if not same chat message ids, 404 if not found.
     */
    @PatchMapping("{id}")
    public ResponseEntity<ChatMessage> updateChatMessage(@PathVariable("id") String id, @RequestBody String message) {
        return service.getChatMessageById(id).map(chatMessage -> {
            if (!service.changeChatMessage(chatMessage, message))
                return new ResponseEntity<ChatMessage>(HttpStatus.UNAUTHORIZED);
            return ResponseEntity.ok(chatMessage);
        }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * URL: api/v1/chats/{id}
     * <br>
     * Delete a chat message by id in the database.
     * @param id The id of the chat message.
     * @return A response entity with chat message, informing client
     * with Http status 200 if deleted or 404 if not found.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<ChatMessage> deleteChatMessageById(@PathVariable("id") String id) {
        return service.getChatMessageById(id).map(chatMessage -> {
            service.deleteChatMessage(chatMessage.id);
            return ResponseEntity.ok(chatMessage);
        }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * URL: api/v1/chats?boardId={boardId}
     * <br>
     * Delete chat messages by boardId in the database.
     * @param boardId The boardId of the chat messages.
     * @return A response entity with chat message, informing client
     * with Http status 200 if deleted or 404 if not found.
     */
    @DeleteMapping(params = "boardId")
    public ResponseEntity<List<ChatMessage>> deleteChatMessageByBoardId(String boardId) {
        List<ChatMessage> chatMessages = service.getChatMessageByBoardId(boardId);
        if (chatMessages.isEmpty()) return ResponseEntity.notFound().build();
        for (ChatMessage chatMessage : chatMessages)
            service.deleteChatMessage(chatMessage.id);
        return ResponseEntity.ok(chatMessages);
    }

    /**
     * URL: api/v1/chats
     * <br>
     * Delete all chat messages in the database.
     * @return A response entity with a message, informing client
     * with Http status 200.
     */
    @Deprecated
    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        service.deleteAll();
        return ResponseEntity.ok("Deleted all chat messages");
    }
}