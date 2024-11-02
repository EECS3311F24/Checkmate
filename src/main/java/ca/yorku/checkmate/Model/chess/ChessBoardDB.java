package ca.yorku.checkmate.Model.chess;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class ChessBoardDB {
    @Id
    public String id;
    @CreatedDate
    public Date createdOn;
    public Chess chess;
    public String player1Id;
    public String player2Id;

    public ChessBoardDB(Chess chess, String player1Id, String player2Id) {
        this.chess = chess;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, createdOn=%s, chess=%s, player1Id=%s, player2Id=%s]",
                id, createdOn, chess, player1Id, player2Id
        );
    }
}
