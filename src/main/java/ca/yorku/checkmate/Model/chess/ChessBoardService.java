package ca.yorku.checkmate.Model.chess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * A ChessBoard service that will get, create, update, and delete ChessBoard
 * data that is stored in the ChessBoardRepository. It is to be used by a controller
 * that will get and receive information to be performed on the Mongodb database.
 */
@Service
public class ChessBoardService {
    private final ChessBoardRepository repository;

    @Autowired
    public ChessBoardService(ChessBoardRepository repository) {
        this.repository = repository;
    }

    public List<ChessBoard> getBoards() {
        return repository.findAll();
    }

    public Optional<ChessBoard> getBoardById(String id) {
        return repository.findById(id);
    }

    public boolean hasBoardById(String id) {
        return repository.existsById(id);
    }

    public boolean createChessBoard(ChessBoard chessBoard) {
        // TODO if important info not allowed to duplicate check here first
        repository.save(chessBoard);
        return true;
    }

    public void updateChessBoard(String id, ChessBoard newChessBoard) {
        getBoardById(id).ifPresent(chessBoard -> {
            // TODO update information when chess board gets more info.
            repository.save(chessBoard);
        });
    }

    public void deleteChessBoard(ChessBoard chessBoard) {
        repository.delete(chessBoard);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
