package ca.yorku.checkmate.Model.mainGame;

import java.util.ArrayList;
import java.util.List;

public abstract class ChessPiece {
    char color;
    char symbol;
    List<Move> movesHistory;

    public ChessPiece() {
        this.movesHistory = new ArrayList<Move>();
    }
    public char getChar() {
        return this.symbol;
    };
    public char getColor() {
        return this.color;
    }
    public List<Move> getMovesHistory() {
        return this.movesHistory;
    }
    public void addMove(Move move) {
        this.movesHistory.add(move);
    }
    public abstract boolean move(Move move);

    public abstract List<Move> getPathWay(Move move);
}
