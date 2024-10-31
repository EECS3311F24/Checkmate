package ca.yorku.checkmate.Model.mainGame;

public class Knight extends ChessPiece {

    public Knight(char color) {
        this.color = color;
        this.symbol = 'N';
    }


    //pass back array of coordinates to check if empty
    @Override
    public boolean move(Move move) {
        int rowDiff = Math.abs(this.movesHistory.getLast().getRow()-move.getRow());
        int colDiff = Math.abs(this.movesHistory.getLast().getColumn()-move.getColumn());
        return ((rowDiff==2 && colDiff==1) || (rowDiff==1 && colDiff==2));
    }
}
