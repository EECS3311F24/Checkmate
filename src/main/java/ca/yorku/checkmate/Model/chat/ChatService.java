package ca.yorku.checkmate.Model.chat;

import ca.yorku.checkmate.Model.user.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * A Chat service that will get, create, update, and delete chat message
 * data that is stored in the ChatRepository. It is to be used by a controller
 * that will get and receive information to be performed on the Mongodb database.
 */
@Service
public class ChatService {
    private final ChatRepository repository;
    private final UserDataService userDataService;

    @Autowired
    public ChatService(ChatRepository repository, UserDataService userDataService) {
        this.repository = repository;
        this.userDataService = userDataService;
    }

    public List<ChatMessage> getChatMessages() {
        return repository.findAll().stream().sorted(Comparator.comparing(msg -> msg.createdOn)).toList();
    }

    public Optional<ChatMessage> getChatMessageById(String id) {
        return repository.findById(id);
    }

    public List<ChatMessage> getChatMessageByUserId(String userId) {
        return getChatMessages().stream().filter(chatMessage -> chatMessage.userId.equals(userId)).toList();
    }

    public List<ChatMessage> getChatMessageByBoardId(String boardId) {
        return getChatMessages().stream().filter(chatMessage -> chatMessage.boardId.equals(boardId)).toList();
    }

    public UserDataService getUserDataService() {
        return this.userDataService;
    }

    public boolean hasChatById(String id) {
        return repository.existsById(id);
    }

    public boolean addChatMessage(ChatMessage chatMessage) {
        if (chatMessage.message == null || chatMessage.message.isEmpty()) return false;
        if (chatMessage.boardId == null || chatMessage.userId == null) return false;
        repository.save(chatMessage);
        return true;
    }

    public boolean changeChatMessage(ChatMessage chatMessage, String message) {
        if (chatMessage == null || !hasChatById(chatMessage.id)) return false;
        chatMessage.setMessage(message);
        repository.save(chatMessage);
        return true;
    }

    public void deleteChatMessage(String id) {
        getChatMessageById(id).ifPresent(repository::delete);
    }

    public void deleteAll() {
        repository.deleteAll();
        userDataService.deleteAll();
    }
}