package ca.yorku.checkmate.Model.chess.chesspieces;

import ca.yorku.checkmate.Model.chess.ChessBoard;
import ca.yorku.checkmate.Model.chess.Move;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece {

    public Pawn(char color) {
        this.color = color;
        this.symbol = 'P';
    }

    //checks validity of move based on squares moved (board stuff dealt with in ChessBoard)
    @Override
    public boolean move(Move move) {
        if(this.movesHistory.get(this.movesHistory.size() - 1).getColumn() != move.getColumn()) return false; //en passant not currently supported
        if(this.movesHistory.size() > 1) { //if not first move, only +1 row moves
            if(this.color == ChessBoard.black) {
                return move.getRow() == 1 + this.movesHistory.get(this.movesHistory.size() - 1).getRow();
            }
            else {
                return move.getRow() == this.movesHistory.get(this.movesHistory.size() - 1).getRow() - 1;
            }
        }
        else {
            int rowsMoved = -1;
            if(this.color == ChessBoard.black) {
                rowsMoved = move.getRow()-this.movesHistory.get(this.movesHistory.size() - 1).getRow();
            }
            else {
                rowsMoved = this.movesHistory.get(this.movesHistory.size() - 1).getRow() - move.getRow();
            }
            return rowsMoved > 0 && rowsMoved < 3;
        }
    }

    @Override
    public List<Move> getPathWay(Move move) {
        //TODO: fix black white pawn
        List<Move> path = new ArrayList<Move>();
        int counter = this.getMovesHistory().get(this.getMovesHistory().size()-1).getRow() + 1;
        while(this.color==ChessBoard.black && move.getRow() >= counter) {
            path.add(new Move(counter, this.getMovesHistory().get(this.getMovesHistory().size()-1).getColumn()));
            counter++;
        }
        counter = this.getMovesHistory().get(this.getMovesHistory().size()-1).getRow() - 1;
        while(this.color==ChessBoard.white && move.getRow() <= counter) {
            path.add(new Move(counter, this.getMovesHistory().get(this.getMovesHistory().size()-1).getColumn()));
            counter--;
        }
        return path;
    }

    @Override
    public List<Move> canThisMove() {
        List<Move> list = new ArrayList<>();
        list.add(new Move(this.getMovesHistory().get(this.getMovesHistory().size()-1).getRow(), this.getMovesHistory().get(this.getMovesHistory().size()-1).getColumn()));
        return list;
    }
}
