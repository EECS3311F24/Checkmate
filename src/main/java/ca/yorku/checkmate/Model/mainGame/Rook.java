package ca.yorku.checkmate.Model.mainGame;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Move> getPathWay(Move move) {
        List<Move> path = new ArrayList<Move>();
        Move lastMove = this.movesHistory.getLast();
        if(lastMove.getRow() != move.getRow()) {
            if(lastMove.getRow() < move.getRow()) {
                int counter = lastMove.getRow() + 1;
                while(counter <= move.getRow()) {
                    path.add(new Move(move.getRow(), counter));
                    counter++;
                }
            }
            else {
                int counter = lastMove.getRow() - 1;
                while(counter >= move.getRow()) {
                    path.add(new Move(move.getRow(), counter));
                    counter--;
                }
            }
        }
        else if(lastMove.getColumn() != move.getColumn()) {
            if(lastMove.getColumn() < move.getColumn()) {
                int counter = lastMove.getColumn() + 1;
                while(counter <= move.getColumn()) {
                    path.add(new Move(move.getColumn(), counter));
                    counter++;
                }
            }
            else {
                int counter = lastMove.getColumn() - 1;
                while(counter <= move.getColumn()) {
                    path.add(new Move(move.getColumn(), counter));
                    counter--;
                }
            }
        }
        return path;
    }
}
