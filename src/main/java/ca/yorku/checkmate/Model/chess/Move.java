package ca.yorku.checkmate.Model.chess;

// TODO record?
public record Move(int x, int y) {
    public int getRow() {
        return x;
    }

    public int getColumn() {
        return y;
    }
}
