package ca.yorku.checkmate.Model.chess;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChessBoardRepository extends MongoRepository<ChessBoard, String> {
}
