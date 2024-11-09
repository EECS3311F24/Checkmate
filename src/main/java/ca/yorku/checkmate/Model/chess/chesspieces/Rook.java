package ca.yorku.checkmate.Model.chess.chesspieces;

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
}
