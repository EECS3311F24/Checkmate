package ca.yorku.checkmate.Model.chess.chesspieces;

import ca.yorku.checkmate.Model.chess.Move;

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
        int rowDiff = Math.abs(this.movesHistory.get(this.movesHistory.size() - 1).row()-move.row());
        int colDiff = Math.abs(this.movesHistory.get(this.movesHistory.size() - 1).col()-move.col());
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
        Move lastMove = this.movesHistory.get(this.movesHistory.size() - 1);
        for(int i = 1; i <=2; i++) {
            for(int j = 2; j >= 1; j--) {
                if(!((i==2 && j == 2) || (i==1 && j == 1))) {
                    //12,11,22,21
                    list.add(new Move(lastMove.row()-i, lastMove.col()-j)); //-1-2, -2-1
                    list.add(new Move(lastMove.row()-i, lastMove.col()+j)); //-1+2, -2+1
                    list.add(new Move(lastMove.row()+i, lastMove.col()-j)); //+1-2, +2-1
                    list.add(new Move(lastMove.row()+i, lastMove.col()+j)); //+1+2, +2+1
                }
            }
        }
        return list;
    }
}
