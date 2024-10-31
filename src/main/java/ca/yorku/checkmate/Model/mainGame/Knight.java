package ca.yorku.checkmate.Model.mainGame;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Move> getPathWay(Move move) {
        List<Move> path = new ArrayList<>();
        path.add(move);
        return path;
    }

    @Override
    public List<Move> canThisMove() {
        List<Move> list = new ArrayList<>();
        Move lastMove = this.movesHistory.getLast();
        for(int i = 1; i <=2; i++) {
            for(int j = 2; j >= 1; j--) {
                if(!((i==2 && j == 2) || (i==1 && j == 1))) {
                    //12,11,22,21
                    list.add(new Move(lastMove.getRow()-i, lastMove.getColumn()-j)); //-1-2, -2-1
                    list.add(new Move(lastMove.getRow()-i, lastMove.getColumn()+j)); //-1+2, -2+1
                    list.add(new Move(lastMove.getRow()+i, lastMove.getColumn()-j)); //+1-2, +2-1
                    list.add(new Move(lastMove.getRow()+i, lastMove.getColumn()+j)); //+1+2, +2+1
                }
            }
        }
        return list;
    }
}
