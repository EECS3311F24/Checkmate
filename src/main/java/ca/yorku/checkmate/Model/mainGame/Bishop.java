package ca.yorku.checkmate.Model.mainGame;

public class Bishop extends ChessPiece {
    public Bishop(char color) {
        this.color = color;
        this.symbol = 'B';
    }

    @Override
    public boolean move(Move move) {
        //diag: +1+1, -1-1, +1-1, -1+1
        Move lastMove = movesHistory.getLast();
        int rowDifference = Math.abs(lastMove.getRow() - move.getRow());
        int columnDifference = Math.abs(lastMove.getColumn() - move.getColumn());
        return rowDifference == columnDifference;
    }
}
