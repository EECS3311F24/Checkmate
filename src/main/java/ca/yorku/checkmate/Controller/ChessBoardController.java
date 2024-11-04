package ca.yorku.checkmate.Controller;

import ca.yorku.checkmate.Model.chess.*;
import ca.yorku.checkmate.Model.chess.chesspieces.ChessPiece;
import ca.yorku.checkmate.Model.user.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoint is api/v1/boards
 * <br>
 * Controller for chess boards, providing a rest endpoint
 * that allows getting, creating, updating, or deleting chess boards.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/boards")
public class ChessBoardController {
    // TODO need a getMoves possible for a chess pieces ?
    private final ChessBoardService service;

    @Autowired
    public ChessBoardController(ChessBoardService chessBoardService) {
        this.service = chessBoardService;
    }

    /**
     * URL: api/v1/boards
     * <br>
     * Gets all chess boards in the database.
     * @return A list of chess boards.
     */
    @GetMapping
    public List<ChessBoardDB> getBoards() {
        return service.getBoards();
    }

    /**
     * URL: api/v1/boards/{id}
     * <br>
     * Gets chess board by id in the database.
     * @param id The id of the chess board.
     * @return A response entity with chess board, informing client
     * with Http status 200 if found or 404 if not found.
     */
    @GetMapping("{id}")
    public ResponseEntity<ChessBoardDB> getChessBoardById(@PathVariable("id") String id) {
        return service.getBoardById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * URL: api/v1/boards/{id}
     * <br>
     * Gets a chess piece at a row and col from chessboard with id.
     * @param id The id of the chess board.
     * @param move The row and col to get the chess piece.
     * @return A response entity with chess board, informing client
     * with Http status 200 if found or 404 if not found.
     */
    @GetMapping("{id}/moves")
    public ResponseEntity<ChessPiece> getChessPiece(@PathVariable("id") String id, @RequestBody Move move) {
        if (!move.isValid()) return ResponseEntity.notFound().build();
        return service.getBoardById(id)
                .map(chessBoard -> ResponseEntity.ok(chessBoard.chess.getChessPiece(move.row(), move.col())))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * URL: api/v1/boards/{id}/whosTurn
     * <br>
     * Gets whosTurn from chessboard with id.
     * @param id The id of the chess board.
     * @return A response entity with chess board, informing client
     * with Http status 200 if found or 404 if not found.
     */
    @GetMapping("{id}/whosTurn")
    public ResponseEntity<Player> getWhosTurn(@PathVariable("id") String id) {
        return service.getBoardById(id)
                .map(chessBoard -> ResponseEntity.ok(chessBoard.chess.getWhosTurn()))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * URL: api/v1/boards?id1={id1}&id2={id2}
     * <br>
     * Create a new chess board in the database.
     * Needs at least player 1's id in order to create.
     * @param player1Id player 1 id for user.
     * @param player2Id player 2 id for user.
     * @return A response entity with chess board, informing client
     * with Http status 201 if created or 409 if not created.
     */
    @PostMapping
    public ResponseEntity<ChessBoardDB> createChessBoard(@RequestParam(name = "id1") String player1Id, @RequestParam(name = "id2", required = false) String player2Id) {
        ChessBoardDB chessBoard = new ChessBoardDB(new Chess(), player1Id, player2Id);
        if (!service.createChessBoard(chessBoard)) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(chessBoard, HttpStatus.CREATED);
    }

    /**
     * URL: api/v1/boards/{id}
     * <br>
     * Reset a chess board by id in the database.
     * @param id The id of the chess board.
     * @return A response entity with chess board, informing client
     * with Http status 200 if updated, 404 if not found.
     */
    @PutMapping("{id}")
    public ResponseEntity<ChessBoardDB> resetChessBoard(@PathVariable("id") String id) {
        return service.getBoardById(id).map(chessBoard ->  {
            service.resetChessBoard(chessBoard);
            return ResponseEntity.ok(chessBoard);
        }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * URL: api/v1/boards
     * <br>
     * Join a random game if not part of any game.
     * Joins any empty games, adding a new guest user to that game, only creating a new empty game if
     * no empty games available
     * @param userId The user ID of the player that sent the request.
     * @return A response entity with chess board, informing client
     * with Http status 200 if joined or 409 if not joined or 404 if no user found.
     */
    @PutMapping
    public ResponseEntity<ChessBoardDB> joinBoard(HttpServletResponse response, @CookieValue(name = "userId", required = false) String userId) {
        if (userId == null) {
            User user = service.getUserController().createUser(response, new User("Guest", "Guest")).getBody();
            if (user == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
            userId = user.id;
        }
        String id = userId;
        return service.getBoards().stream()
                .filter(chessBoard -> chessBoard.player2Id == null)
                .findFirst()
                .map(chessBoard -> {
                    service.setPlayer2Id(chessBoard, id);
                    return ResponseEntity.ok(chessBoard);
                }).orElse(createChessBoard(id, null));
    }

    /**
     * URL: api/v1/boards/{id}/moves
     * <br>
     * Move a chess piece to a new position.
     * @param id The id of the chess board.
     * @param userId The user ID of the player that sent the request.
     * @param moves The moves to be made with start and end.
     * @return A response entity with chess board, informing client
     * with Http status 200 if moved or 400 if not a valid move or 409 if not moved or 404 if not found .
     */
    @PatchMapping("{id}/moves")
    public ResponseEntity<ChessBoardDB> move(@PathVariable("id") String id, @CookieValue(name = "userId", required = false) String userId, @RequestBody Moves moves) {
        if (!moves.isValid()) return ResponseEntity.badRequest().build();
        return service.getBoardById(id).map(chessBoard -> {
            if (!service.moveChessPiece(chessBoard, userId, moves))
                return new ResponseEntity<ChessBoardDB>(HttpStatus.CONFLICT);
            return ResponseEntity.ok(chessBoard);
        }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * URL: api/v1/boards/{id}
     * <br>
     * Delete a chess board by id in the database.
     * @param id The id of the chess board.
     * @return A response entity with a message, informing client
     * with Http status 200 if deleted or 404 if not found.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<ChessBoardDB> deleteChessBoard(@PathVariable("id") String id) {
        return service.getBoardById(id).map(chessBoard -> {
            service.deleteChessBoard(chessBoard);
            return ResponseEntity.ok(chessBoard);
        }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * URL: api/v1/boards
     * <br>
     * Delete all chess boards in the database.
     * @return A response entity with a message, informing client
     * with Http status 200.
     */
    @Deprecated
    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        service.deleteAll();
        return ResponseEntity.ok("Deleted all chess boards!");
    }
}
