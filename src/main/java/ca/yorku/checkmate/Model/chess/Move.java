package ca.yorku.checkmate.Model.chess;

public record Move(int row, int col) {
    public boolean isValid() {
        return row >= 0 && row < ChessBoard.dimensions &&
                col >= 0 && col < ChessBoard.dimensions;
    }
}
