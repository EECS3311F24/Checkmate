package ca.yorku.checkmate.Model.mainGame;

public class Pawn extends ChessPiece {

    public Pawn(char color) {
        this.color = color;
        this.symbol = 'P';
    }

    //checks validity of move based on squares moved (board stuff dealt with in ChessBoard)
    @Override
    public boolean move(Move move) {
        if(this.movesHistory.getLast().getColumn() != move.getColumn()) return false; //en passant not currently supported
        if(this.movesHistory.size() > 1) { //if not first move, only +1 row moves
            return move.getRow() == 1 + this.movesHistory.getLast().getRow();
        }
        else {
            int rowsMoved = move.getRow()-movesHistory.getLast().getRow();
            return rowsMoved > 0 && rowsMoved < 3;
        }
    }
}
