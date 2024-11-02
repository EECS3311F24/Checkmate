package ca.yorku.checkmate.Model.chess;

public record Moves(Move start, Move end) {
    public boolean isValid() {
        return start().isValid() && end().isValid();
    }
}
