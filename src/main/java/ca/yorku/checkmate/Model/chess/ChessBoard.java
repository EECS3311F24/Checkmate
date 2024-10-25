package ca.yorku.checkmate.Model.chess;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * ChessBoard model that represents a simple chess board.
 */
public class ChessBoard {
    @Id
    public String id;
    @CreatedDate
    public Date createdOn;

    @Override
    public String toString() {
        return String.format("ChessBoard[id=%s, createdOn='%s']", id, createdOn);
    }
}
