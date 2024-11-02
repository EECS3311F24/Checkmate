package ca.yorku.checkmate.Model.chess;

// TODO record?
public class Move {
    private final int x;
    private final int y;

    public Move(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getRow() {
        return x;
    }

    public int getColumn() {
        return y;
    }
}
