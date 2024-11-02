package mainGame;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends ChessPiece {
    public Bishop(char color) {
        this.color = color;
        this.symbol = 'B';
    }

    @Override
    public boolean move(Move move) {
        //diag: +1+1, -1-1, +1-1, -1+1
        Move lastMove = this.movesHistory.get(this.movesHistory.size() - 1);
        int rowDifference = Math.abs(lastMove.getRow() - move.getRow());
        int columnDifference = Math.abs(lastMove.getColumn() - move.getColumn());
        return rowDifference == columnDifference;
    }

    @Override
    public List<Move> getPathWay(Move move) {
        List<Move> path = new ArrayList<Move>();
        Move lastMove = this.movesHistory.get(this.movesHistory.size() - 1);
        int currentRow = lastMove.getRow();
        int currentColumn = lastMove.getColumn();
        if(lastMove.getRow() < move.getRow() && lastMove.getColumn() < move.getColumn()) { //+1+1
            while(currentRow < move.getRow() && lastMove.getColumn() < move.getColumn()) {
                currentRow++;
                currentColumn++;
                path.add(new Move(currentRow, currentColumn));
            }

        }
        else if(lastMove.getRow() < move.getRow() && lastMove.getColumn() > move.getColumn()) { //+1-1
            while(currentRow < move.getRow() && lastMove.getColumn() > move.getColumn()) {
                currentRow++;
                currentColumn--;
                path.add(new Move(currentRow, currentColumn));
            }
        }
        else if (lastMove.getRow() > move.getRow() && lastMove.getColumn() < move.getColumn()) { //-1+1
            while(currentRow > move.getRow() && lastMove.getColumn() < move.getColumn()) {
                currentRow--;
                currentColumn++;
                path.add(new Move(currentRow, currentColumn));
            }
        }
        else if (lastMove.getRow() > move.getRow() && lastMove.getColumn() > move.getColumn()) { //-1-1
            while(currentRow > move.getRow() && lastMove.getColumn() > move.getColumn()) {
                currentRow--;
                currentColumn--;
                path.add(new Move(currentRow, currentColumn));
            }
        }
        return path;
    }

    @Override
    public List<Move> canThisMove() {
        List<Move> list = new ArrayList<>();
        Move lastMove = this.movesHistory.get(this.movesHistory.size() - 1);
        list.add(new Move(lastMove.getRow() - 1, lastMove.getColumn()-1));
        list.add(new Move(lastMove.getRow() - 1, lastMove.getColumn()+1));
        list.add(new Move(lastMove.getRow() + 1, lastMove.getColumn()-1));
        list.add(new Move(lastMove.getRow() + 1, lastMove.getColumn()+1));
        return list;
    }
}
