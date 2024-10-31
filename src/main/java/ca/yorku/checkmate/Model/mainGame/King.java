package ca.yorku.checkmate.Model.mainGame;

public class King extends ChessPiece {

    public King(char color) {
        this.color = color;
        this.symbol = 'K';
    }

    @Override
    public boolean move(Move move) {
        //TODO: implement castle and checks later
        int rowDiff = Math.abs(this.movesHistory.getLast().getRow()-move.getRow());
        int colDiff = Math.abs(this.movesHistory.getLast().getColumn()-move.getColumn());
        return rowDiff == 1 ^ colDiff == 1;
    }
}
