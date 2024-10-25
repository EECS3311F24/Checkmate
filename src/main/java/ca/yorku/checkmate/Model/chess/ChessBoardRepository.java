package ca.yorku.checkmate.Model.chess;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Interface for mongodb repository that automatically
 * requests data from the mongodb database for ChessBoard
 */
public interface ChessBoardRepository extends MongoRepository<ChessBoard, String> {
}
