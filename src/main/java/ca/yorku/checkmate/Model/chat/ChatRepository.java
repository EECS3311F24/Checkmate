package ca.yorku.checkmate.Model.chat;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Interface for mongodb repository that automatically
 * requests data from the mongodb database for ChatMessage
 */
public interface ChatRepository extends MongoRepository<ChatMessage, String> { }