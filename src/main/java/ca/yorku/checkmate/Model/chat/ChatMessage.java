package ca.yorku.checkmate.Model.chat;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Chat message model that represents a chat message
 */
public class ChatMessage {
    @Id
    public String id;
    public String userId;
    public String boardId;
    @CreatedDate
    public Date createdOn;
    public String message;

    public ChatMessage(String userId, String boardId, String message) {
        this.userId = userId;
        this.boardId = boardId;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, userId='%s', boardId='%s', message='%s', createdOn='%s']",
                id, userId, boardId, message, createdOn
        );
    }
}