package ca.yorku.checkmate.Model.chat;

import ca.yorku.checkmate.Model.user.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return repository.findAll();
    }

    public Optional<ChatMessage> getChatMessageById(String id) {
        return repository.findById(id);
    }

    public List<ChatMessage> getChatMessageByUserId(String userId) {
        return getChatMessages().stream().filter(chatMessage -> chatMessage.userId.equals(userId)).toList();
    }

    public List<ChatMessage> getChatMessageByBoardId(String boardId) {
        return getChatMessages().stream().filter(chatMessage -> chatMessage.userId.equals(boardId)).toList();
    }

    public UserDataService getUserDataService() {
        return this.userDataService;
    }

    public boolean hasChatById(String id) {
        return repository.existsById(id);
    }

    public boolean addChatMessage(ChatMessage chatMessage) {
        if (chatMessage.message == null || chatMessage.message.isEmpty()) return false;
        repository.save(chatMessage);
        return true;
    }

    //TODO patch just message?????
    public boolean updateChatMessage(ChatMessage chatMessage, ChatMessage placeholder) {
        if (chatMessage == null || placeholder == null || !hasChatById(chatMessage.id)) return false;
        chatMessage.setMessage(placeholder.message);
        repository.save(chatMessage);
        return true;
    }

    public boolean deleteChatMessage(String id) {
        return getChatMessageById(id).map(chatMessage -> {
            repository.delete(chatMessage);
            return true;
        }).orElse(false);
    }

    public void deleteAll() {
        repository.deleteAll();
        userDataService.deleteAll();
    }
}