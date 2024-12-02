package ca.yorku.checkmate.Model.user;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Interface for mongodb repository that automatically
 * requests data from the mongodb database for UserData
 */
public interface UserDataRepository extends MongoRepository<UserData, String> { }