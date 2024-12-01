package ca.yorku.checkmate.Model.chess;

import ca.yorku.checkmate.Controller.UserController;
import ca.yorku.checkmate.Model.chess.chesspieces.ChessPiece;
import ca.yorku.checkmate.Model.user.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private final UserController userController;

    @Autowired
    public ChessBoardService(ChessBoardRepository repository, UserController userController) {
        this.repository = repository;
        this.userController = userController;
    }

    public List<ChessBoardDB> getBoards() {
        return repository.findAll().stream().map(this::fixChessBoardData).toList();
    }

    public Optional<ChessBoardDB> getBoardById(String id) {
        return repository.findById(id).map(this::fixChessBoardData);
    }

    private ChessBoardDB fixChessBoardData(ChessBoardDB chessBoard) {
        ChessBoard cb = chessBoard.chess.getChessBoard();
        cb.getWhitePieces().clear();
        cb.getBlackPieces().clear();
        for (int row = 0; row < cb.getBoard().length; row++) {
            for (int col = 0; col < cb.getBoard().length; col++) {
                Placeholder p = cb.getBoard()[row][col];
                if (p == null || p.getChessPiece() == null) continue;
                if (p.getChessPiece().getColor() == ChessBoard.white) {
                    cb.getWhitePieces().add(p.getChessPiece());
                } else cb.getBlackPieces().add(p.getChessPiece());
            }
        }
        return chessBoard;
    }

    public UserController getUserController() {
        return this.userController;
    }

    public boolean hasBoardById(String id) {
        return repository.existsById(id);
    }

    public void resetChessBoard(ChessBoardDB chessBoard) {
        chessBoard.chess = new Chess(chessBoard.mode);
        repository.save(chessBoard);
    }

    public boolean createChessBoard(ChessBoardDB chessBoard) {
        if (chessBoard == null || chessBoard.player1Id == null) return false;
        if (!updateGamesPlayed(chessBoard)) return false;
        repository.save(chessBoard);
        return true;
    }

    public boolean updateGamesPlayed(ChessBoardDB chessBoard) {
        if (chessBoard.player1Id != null && chessBoard.player1Id.equals(chessBoard.player2Id)) return false;
        String id = chessBoard.player2Id == null ? chessBoard.player1Id : chessBoard.player2Id;
        ResponseEntity<UserData> response = userController.getUserData(id);
        if (!response.getStatusCode().is2xxSuccessful()) return false;
        UserData userData = response.getBody();
        if (userData == null) return false;
        userController.updateUserData(id, id, userData.setGamesPlayed(userData.gamesPlayed + 1));
        return true;
    }

    public void setPlayer2Id(ChessBoardDB chessBoard, String id) {
        chessBoard.player2Id = id;
        updateGamesPlayed(chessBoard);
        repository.save(chessBoard);
    }

    public boolean moveChessPiece(ChessBoardDB chessBoard, String userId, Moves moves) {
        if (chessBoard.chess.isGameOver()) return false;
        Player whosTurn = chessBoard.chess.getWhosTurn();
        if (whosTurn.playerColor() == ChessBoard.white) {
           if (chessBoard.player1Id != null && !chessBoard.player1Id.equals(userId)) return false;
        } else if (whosTurn.playerColor() == ChessBoard.black) {
           if (chessBoard.player2Id != null && !chessBoard.player2Id.equals(userId)) return false;
        } else return false;
        ChessPiece piece = chessBoard.chess.getChessPiece(moves.start().row(), moves.start().col());
        if (piece == null) return false;
        if (whosTurn.playerColor() != piece.getColor()) return false;
        boolean moved = chessBoard.chess.move(moves.end().row(), moves.end().col(), piece);
        updateWinnerData(chessBoard);
        repository.save(chessBoard);
        return moved;
    }

    private void updateWinnerData(ChessBoardDB chessBoard) {
        if (!chessBoard.chess.isGameOver()) return;
        String id = chessBoard.chess.getWinner().playerColor() == ChessBoard.white ? chessBoard.player1Id : chessBoard.player2Id;
        if (id != null) {
            ResponseEntity<UserData> response = userController.getUserData(id);
            UserData userData = response.getBody();
            if (userData != null) userController.updateUserData(id, id, userData.setWins(userData.wins + 1));
        }
        id = chessBoard.chess.getWinner().playerColor() == ChessBoard.white ? chessBoard.player2Id : chessBoard.player1Id;
        if (id != null) {
            ResponseEntity<UserData> response = userController.getUserData(id);
            UserData userData = response.getBody();
            if (userData != null) userController.updateUserData(id, id, userData.setLoses(userData.loses + 1));
        }
        chessBoard.player1Id = null;
        chessBoard.player2Id = null;
    }

    private void updatePlayerPieces(ChessBoard chessBoard, ChessPiece piece) {
        List<ChessPiece> pieces = piece.getColor() == ChessBoard.white ? chessBoard.getWhitePieces() : chessBoard.getBlackPieces();
        pieces.forEach(chessPiece -> {
            List<Move> history = chessPiece.getMovesHistory();
            if (history.isEmpty())return;
            if (history.get(0).equals(piece.getMovesHistory().get(0))) {
                history.addAll(piece.getMovesHistory().stream().filter(move -> !history.contains(move)).toList());
                List<ChessPiece> otherPieces = piece.getColor() == ChessBoard.black ? chessBoard.getWhitePieces() : chessBoard.getBlackPieces();
                otherPieces.removeIf(otherPiece -> otherPiece.getMovesHistory().get(otherPiece.getMovesHistory().size() - 1).equals(piece.getMovesHistory().get(piece.getMovesHistory().size() - 1)));
            }
        });
        chessBoard.checkCheckMate(ChessBoard.getOtherPlayerColor(piece.getColor()));
    }

    public void deleteChessBoard(ChessBoardDB chessBoard) {
        repository.delete(chessBoard);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}