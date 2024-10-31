package ca.yorku.checkmate.Model.mainGame;

import java.util.ArrayList;
import java.util.List;

public class King extends ChessPiece {

    public King(char color) {
        this.color = color;
        this.symbol = 'K';
    }

    @Override
    public boolean move(Move move) {
        //TODO: implement castle and checks later
        int rowDiff = Math.abs(this.movesHistory.getLast().getRow()-move.getRow());
        int colDiff = Math.abs(this.movesHistory.getLast().getColumn()-move.getColumn());
        return rowDiff == 1 ^ colDiff == 1;
    }

    @Override
    public List<Move> getPathWay(Move move) {
        List<Move> path = new ArrayList<>();
        path.add(move);
        return path;
    }

    @Override
    public List<Move> canThisMove() {
        Rook fakeRook = new Rook(ChessBoard.black);
        fakeRook.addMove(this.movesHistory.getLast());
        return fakeRook.canThisMove();
    }
}
