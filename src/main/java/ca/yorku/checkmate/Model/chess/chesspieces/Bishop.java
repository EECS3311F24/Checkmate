package ca.yorku.checkmate.Model.chess.chesspieces;

import ca.yorku.checkmate.Model.chess.Move;

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
        int rowDifference = Math.abs(lastMove.row() - move.row());
        int columnDifference = Math.abs(lastMove.col() - move.col());
        return rowDifference == columnDifference;
    }

    @Override
    public List<Move> getPathWay(Move move) {
        List<Move> path = new ArrayList<Move>();
        Move lastMove = this.movesHistory.get(this.movesHistory.size() - 1);
        int currentRow = lastMove.row();
        int currentColumn = lastMove.col();
        if(lastMove.row() < move.row() && lastMove.col() < move.col()) { //+1+1
            while(currentRow < move.row() && lastMove.col() < move.col()) {
                currentRow++;
                currentColumn++;
                path.add(new Move(currentRow, currentColumn));
            }

        }
        else if(lastMove.row() < move.row() && lastMove.col() > move.col()) { //+1-1
            while(currentRow < move.row() && lastMove.col() > move.col()) {
                currentRow++;
                currentColumn--;
                path.add(new Move(currentRow, currentColumn));
            }
        }
        else if (lastMove.row() > move.row() && lastMove.col() < move.col()) { //-1+1
            while(currentRow > move.row() && lastMove.col() < move.col()) {
                currentRow--;
                currentColumn++;
                path.add(new Move(currentRow, currentColumn));
            }
        }
        else if (lastMove.row() > move.row() && lastMove.col() > move.col()) { //-1-1
            while(currentRow > move.row() && lastMove.col() > move.col()) {
                currentRow--;
                currentColumn--;
                path.add(new Move(currentRow, currentColumn));
            }
        }
        return path;
    }

    @Override
    public List<Move> listOfShortestMoves() {
        List<Move> list = new ArrayList<>();
        Move lastMove = this.movesHistory.get(this.movesHistory.size() - 1);
        list.add(new Move(lastMove.row() - 1, lastMove.col()-1));
        list.add(new Move(lastMove.row() - 1, lastMove.col()+1));
        list.add(new Move(lastMove.row() + 1, lastMove.col()-1));
        list.add(new Move(lastMove.row() + 1, lastMove.col()+1));
        return list;
    }

    @Override
    public List<Move> listOfAllMoves() {
        return List.of();
    }
}
