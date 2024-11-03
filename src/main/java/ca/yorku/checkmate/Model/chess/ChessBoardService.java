package ca.yorku.checkmate.Model.chess;

import ca.yorku.checkmate.Model.chess.chesspieces.ChessPiece;
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

    public List<ChessBoardDB> getBoards() {
        return repository.findAll();
    }

    public Optional<ChessBoardDB> getBoardById(String id) {
        return repository.findById(id);
    }

    public boolean hasBoardById(String id) {
        return repository.existsById(id);
    }

    public void resetChessBoard(ChessBoardDB chessBoard) {
        chessBoard.chess = new Chess();
        repository.save(chessBoard);
    }

    public boolean createChessBoard(ChessBoardDB chessBoard) {
        repository.save(chessBoard);
        return true;
    }

    public void setPlayer2Id(ChessBoardDB chessBoard, String id) {
        chessBoard.player2Id = id;
        repository.save(chessBoard);
    }

    public boolean moveChessPiece(ChessBoardDB chessBoard, String userId, Moves moves) {
        Player whosTurn = chessBoard.chess.getWhosTurn();
        // TODO assumes player1 is always white
        if (whosTurn.playerColor() == ChessBoard.white) {
            if (!chessBoard.player1Id.equals(userId)) return false;
        } else if (whosTurn.playerColor() == ChessBoard.black) {
            if (!chessBoard.player2Id.equals(userId)) return false;
        } else return false;

        ChessPiece piece = chessBoard.chess.getChessPiece(moves.start().row(), moves.start().col());
        if (piece == null) return false;
        if (whosTurn.playerColor() != piece.getColor()) return false;
        boolean moved = chessBoard.chess.move(moves.end().row(), moves.end().col(), piece);
        repository.save(chessBoard);
        return moved;
    }

    public void deleteChessBoard(ChessBoardDB chessBoard) {
        repository.delete(chessBoard);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
