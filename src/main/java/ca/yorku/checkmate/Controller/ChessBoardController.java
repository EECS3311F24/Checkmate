package ca.yorku.checkmate.Controller;

import ca.yorku.checkmate.Model.ChessBoard;
import ca.yorku.checkmate.Model.ChessBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Endpoint is api/v1/board</p>
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
     * <p>URL: api/v1/board</p>
     * Gets all chess boards in the database.
     * @return A list of chess boards.
     */
    @GetMapping
    public List<ChessBoard> getBoards() {
        return service.getBoards();
    }

    /**
     * <p>URL: api/v1/board/{id}<p>
     * Gets chess board by id in the database.
     * @param id The id of the chess board.
     * @return A response entity with chess board, informing client
     * with Http status 200 if found or 404 if not found.
     */
    @GetMapping("{id}")
    public ResponseEntity<ChessBoard> getUserById(@PathVariable("id") String id) {
        return service.getBoardById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * <p>URL: api/v1/board</p>
     * Create a new chess board in the database.
     * @param chessBoard The chess board to be created.
     * @return A response entity with chess board, informing client
     * with Http status 201 if created or 409 if not created.
     */
    @PostMapping
    public ResponseEntity<ChessBoard> createUser(@RequestBody ChessBoard chessBoard) {
        if (!service.createChessBoard(chessBoard)) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(chessBoard, HttpStatus.CREATED);
    }

    /**
     * <p>URL: api/v1/board/{id}</p>
     * Update a chess board by id in the database.
     * @param id The id of the chess board.
     * @param chessBoard The chessBoard to be created.
     * @return A response entity with chess board, informing client
     * with Http status 200 if updated, 201 if created, 409 if not created.
     */
    @PutMapping("{id}")
    public ResponseEntity<ChessBoard> updateUser(@PathVariable("id") String id, @RequestBody ChessBoard chessBoard) {
        if (!service.hasUserById(id)) return createUser(chessBoard);
        service.updateChessBoard(id, chessBoard);
        return ResponseEntity.ok(chessBoard);
    }

    /**
     * <p>URL: api/v1/board/{id}</p>
     * Delete a user by id in the database.
     * @param id The id of the user.
     * @return A response entity with a message, informing client
     * with Http status 200 if deleted or 404 if not found.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        return service.getBoardById(id).map(chessBoard -> {
            service.deleteUser(chessBoard);
            return ResponseEntity.ok("Deleted chess board " + chessBoard + "!");
        }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * <p>URL: api/v1/board</p>
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
