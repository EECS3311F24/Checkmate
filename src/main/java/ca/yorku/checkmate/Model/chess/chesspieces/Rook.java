package ca.yorku.checkmate.Model.chess.chesspieces;

import ca.yorku.checkmate.Model.chess.ChessBoard;
import ca.yorku.checkmate.Model.chess.Move;

import java.util.ArrayList;
import java.util.List;

public class Rook extends ChessPiece {
    public Rook(char color) {
        this.color = color;
        this.symbol = 'R';
    }

    @Override
    public boolean move(Move move) {
        return this.movesHistory.get(this.movesHistory.size()-1).row() == move.row() ||
                this.movesHistory.get(this.movesHistory.size() - 1).col() == move.col();
    }

    @Override
    public List<Move> getPathWay(Move move) {
        List<Move> path = new ArrayList<>();
        Move lastMove = this.movesHistory.get(this.movesHistory.size() - 1);
        if(lastMove.row() != move.row()) {
            if(lastMove.row() < move.row()) {
                int counter = lastMove.row() + 1;
                while(counter <= move.row()) {
                    path.add(new Move(counter, move.col()));
                    counter++;
                }
            }
            else {
                int counter = lastMove.row() - 1;
                while(counter >= move.row()) {
                    path.add(new Move(counter, move.col()));
                    counter--;
                }
            }
        }
        else if(lastMove.col() != move.col()) {
            if(lastMove.col() < move.col()) {
                int counter = lastMove.col() + 1;
                while(counter <= move.col()) {
                    path.add(new Move(move.row(), counter));
                    counter++;
                }
            }
            else {
                int counter = lastMove.col() - 1;
                while(counter >= move.col()) {
                    path.add(new Move(move.row(), counter));
                    counter--;
                }
            }
        }
        return path;
    }

    @Override
    public List<Move> listOfShortestMoves() { //only returns list of shortest paths, not all paths
        List<Move> list = new ArrayList<>();
        Move lastMove = this.movesHistory.get(this.movesHistory.size() - 1);
        list.add(new Move(lastMove.row() - 1, lastMove.col())); //up
        list.add(new Move(lastMove.row() + 1, lastMove.col())); //down
        list.add(new Move(lastMove.row(), lastMove.col()-1)); //left
        list.add(new Move(lastMove.row(), lastMove.col()+1)); //right
        return list;
    }

    @Override
    public List<Move> listOfAllMoves() { //(-1,0)(0,+1)(+1,0)(0,-1)
        List<Move> list = new ArrayList<>();
        Move lastMove = this.movesHistory.get(this.movesHistory.size() - 1);
        int row0 = lastMove.row()-1;
        int col0 = lastMove.col();
        while (row0>=0) { //(-1,0)
            list.add(new Move(row0, col0));
            row0--;
        }
        row0 = lastMove.row();
        col0 = lastMove.col()+1;
        while (col0 < ChessBoard.dimensions) { //(0,+1)
            list.add(new Move(row0, col0));
            col0++;
        }
        row0 = lastMove.row()+1;
        col0 = lastMove.col();
        while (row0<ChessBoard.dimensions) { //(+1,0)
            list.add(new Move(row0, col0));
            row0++;
        }
        row0 = lastMove.row();
        col0 = lastMove.col()-1;
        while (col0 >= 0) {   //(0,-1)
            list.add(new Move(row0, col0));
            col0--;
        }
        return list;
    }

    @Override
    public ChessPiece clone() {
        return new Rook(color);
    }
}
