package ca.yorku.checkmate.Model.chess;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Interface for mongodb repository that automatically
 * requests data from the mongodb database for ChessBoardDB
 */
public interface ChessBoardRepository extends MongoRepository<ChessBoardDB, String> {
}
