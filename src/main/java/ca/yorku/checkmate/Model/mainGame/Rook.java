package mainGame;

import java.util.ArrayList;
import java.util.List;

public class Rook extends ChessPiece {
    public Rook(char color) {
        this.color = color;
        this.symbol = 'R';
    }

    @Override
    public boolean move(Move move) {
        return this.movesHistory.get(this.movesHistory.size()-1).getRow() == move.getRow() ||
                this.movesHistory.get(this.movesHistory.size() - 1).getColumn() == move.getColumn();
    }

    @Override
    public List<Move> getPathWay(Move move) {
        List<Move> path = new ArrayList<>();
        Move lastMove = this.movesHistory.get(this.movesHistory.size() - 1);
        if(lastMove.getRow() != move.getRow()) {
            if(lastMove.getRow() < move.getRow()) {
                int counter = lastMove.getRow() + 1;
                while(counter <= move.getRow()) {
                    path.add(new Move(counter, move.getColumn()));
                    counter++;
                }
            }
            else {
                int counter = lastMove.getRow() - 1;
                while(counter >= move.getRow()) {
                    path.add(new Move(counter, move.getColumn()));
                    counter--;
                }
            }
        }
        else if(lastMove.getColumn() != move.getColumn()) {
            if(lastMove.getColumn() < move.getColumn()) {
                int counter = lastMove.getColumn() + 1;
                while(counter <= move.getColumn()) {
                    path.add(new Move(move.getRow(), counter));
                    counter++;
                }
            }
            else {
                int counter = lastMove.getColumn() - 1;
                while(counter >= move.getColumn()) {
                    path.add(new Move(move.getRow(), counter));
                    counter--;
                }
            }
        }
        return path;
    }

    @Override
    public List<Move> canThisMove() {
        List<Move> list = new ArrayList<>();
        Move lastMove = this.movesHistory.get(this.movesHistory.size() - 1);
        list.add(new Move(lastMove.getRow() - 1, lastMove.getColumn())); //up
        list.add(new Move(lastMove.getRow() + 1, lastMove.getColumn())); //down
        list.add(new Move(lastMove.getRow(), lastMove.getColumn()-1)); //left
        list.add(new Move(lastMove.getRow(), lastMove.getColumn()+1)); //right
        return list;
    }
}
