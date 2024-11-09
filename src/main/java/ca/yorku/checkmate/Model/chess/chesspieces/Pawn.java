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
        Move lastMove = this.getMovesHistory().get(this.movesHistory.size() - 1);
        if(this.color==ChessBoard.white && move.row() == lastMove.row()-1) {//-1-1, -1+1
            if (move.col() == lastMove.col() - 1 || move.col() == lastMove.col() + 1) return true;
        }
        else if(this.color==ChessBoard.black && move.row()==lastMove.row()+1) {//+1-1,+1+1
            if (move.col() == lastMove.col() - 1 || move.col() == lastMove.col() + 1) return true;
        }
        if(lastMove.col() != move.col()) return false;
        if(this.movesHistory.size() > 1) {
            if(this.color == ChessBoard.black) return move.row() == lastMove.row() + 1;
            else return move.row() == lastMove.row() - 1;
        }
        else {
            int rowsMoved = -1;
            if(this.color == ChessBoard.black) rowsMoved = move.row()-lastMove.row();
            else rowsMoved = lastMove.row() - move.row();
            return rowsMoved > 0 && rowsMoved < 3;
        }
    }

    @Override
    public List<Move> getPathWay(Move move) {
        List<Move> path = new ArrayList<Move>();
        Move lastMove = this.getMovesHistory().get(this.movesHistory.size() - 1);
        if(Math.abs(lastMove.col() - move.col()) == 1 && Math.abs(lastMove.row())-move.row()==1) path.add(move);
        else {
            int counter = lastMove.row() + 1;
            while (this.color == ChessBoard.black && move.row() >= counter) {
                path.add(new Move(counter, this.getMovesHistory().get(this.getMovesHistory().size() - 1).col()));
                counter++;
            }
            counter = this.getMovesHistory().get(this.getMovesHistory().size() - 1).row() - 1;
            while (this.color == ChessBoard.white && move.row() <= counter) {
                path.add(new Move(counter, this.getMovesHistory().get(this.getMovesHistory().size() - 1).col()));
                counter--;
            }
        }
        return path;
    }

    @Override
    public List<Move> listOfShortestMoves() { //returns shortest moves in all directions
        List<Move> list = new ArrayList<>();
        Move lastMove = this.getMovesHistory().get(this.getMovesHistory().size()-1);
        if(this.color == ChessBoard.white) {
            list.add(new Move(lastMove.row()-1, lastMove.col()));
            list.add(new Move(lastMove.row()-1,lastMove.col()-1));
            list.add(new Move(lastMove.row()-1,lastMove.col()+1));
        }
        else {
            list.add(new Move(lastMove.row()+1, lastMove.col()));
            list.add(new Move(lastMove.row()+1,lastMove.col()-1));
            list.add(new Move(lastMove.row()+1,lastMove.col()+1));
        }
        return list;
    }

    @Override
    public List<Move> listOfAllMoves() { //fix: use listOfShortestmoves + 2square first move
        Move lastMove = this.getMovesHistory().get(this.getMovesHistory().size()-1);
        List<Move> list = new ArrayList<>(this.listOfShortestMoves());
        if(this.movesHistory.size() == 1) {
            list.add(this.color==ChessBoard.white ? new Move(lastMove.row()-2, lastMove.col()):
                    new Move(lastMove.row()+2, lastMove.col()));
        }
        return list;
    }
}
