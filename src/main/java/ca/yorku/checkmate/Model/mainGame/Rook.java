package ca.yorku.checkmate.Model.mainGame;

public class Rook extends ChessPiece {
    public Rook(char color) {
        this.color = color;
        this.symbol = 'R';
    }

    @Override
    public boolean move(Move move) {
        return this.movesHistory.getLast().getRow() == move.getRow() ||
                this.movesHistory.getLast().getColumn() == move.getColumn();
    }
}
