package ca.yorku.checkmate.Controller;

import ca.yorku.checkmate.Model.chess.ChessBoard;
import ca.yorku.checkmate.Model.chess.ChessBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoint is api/v1/board
 * <br>
 * Controller for chess boards, providing a rest endpoint
 * that allows getting, creating, updating, or deleting chess boards.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/board")
public class ChessBoardController {
    private final ChessBoardService service;

    @Autowired
    public ChessBoardController(ChessBoardService chessBoardService) {
        this.service = chessBoardService;
    }

    /**
     * URL: api/v1/board
     * <br>
     * Gets all chess boards in the database.
     * @return A list of chess boards.
     */
    @GetMapping
    public List<ChessBoard> getBoards() {
        return service.getBoards();
    }

    /**
     * URL: api/v1/board/{id}
     * <br>
     * Gets chess board by id in the database.
     * @param id The id of the chess board.
     * @return A response entity with chess board, informing client
     * with Http status 200 if found or 404 if not found.
     */
    @GetMapping("{id}")
    public ResponseEntity<ChessBoard> getChessBoardById(@PathVariable("id") String id) {
        return service.getBoardById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * URL: api/v1/board
     * <br>
     * Create a new chess board in the database.
     * @param chessBoard The chess board to be created.
     * @return A response entity with chess board, informing client
     * with Http status 201 if created or 409 if not created.
     */
    @PostMapping
    public ResponseEntity<ChessBoard> createChessBoard(@RequestBody ChessBoard chessBoard) {
        if (!service.createChessBoard(chessBoard)) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(chessBoard, HttpStatus.CREATED);
    }

    /**
     * URL: api/v1/board/{id}
     * <br>
     * Update a chess board by id in the database.
     * @param id The id of the chess board.
     * @param chessBoard The chessBoard to be created.
     * @return A response entity with chess board, informing client
     * with Http status 200 if updated, 201 if created, 409 if not created.
     */
    @PutMapping("{id}")
    public ResponseEntity<ChessBoard> updateChessBoard(@PathVariable("id") String id, @RequestBody ChessBoard chessBoard) {
        if (!service.hasBoardById(id)) return createChessBoard(chessBoard);
        service.updateChessBoard(id, chessBoard);
        return ResponseEntity.ok(chessBoard);
    }

    /**
     * URL: api/v1/board/{id}
     * <br>
     * Delete a chess board by id in the database.
     * @param id The id of the chess board.
     * @return A response entity with a message, informing client
     * with Http status 200 if deleted or 404 if not found.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteChessBoard(@PathVariable("id") String id) {
        return service.getBoardById(id).map(chessBoard -> {
            service.deleteChessBoard(chessBoard);
            return ResponseEntity.ok("Deleted chess board " + chessBoard + "!");
        }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * URL: api/v1/board
     * <br>
     * Delete all chess boards in the database.
     * @return A response entity with a message, informing client
     * with Http status 200.
     */
    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        service.deleteAll();
        return ResponseEntity.ok("Deleted all chess boards!");
    }
}
