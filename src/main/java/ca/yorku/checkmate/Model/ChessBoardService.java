package ca.yorku.checkmate.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public boolean hasUserById(String id) {
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

    public void deleteUser(ChessBoard chessBoard) {
        repository.delete(chessBoard);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
