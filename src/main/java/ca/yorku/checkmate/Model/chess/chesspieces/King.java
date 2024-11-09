package ca.yorku.checkmate.Model.chess.chesspieces;

import ca.yorku.checkmate.Model.chess.ChessBoard;
import ca.yorku.checkmate.Model.chess.Move;

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
        int rowDiff = Math.abs(this.movesHistory.get(this.movesHistory.size() - 1).row()-move.row());
        int colDiff = Math.abs(this.movesHistory.get(this.movesHistory.size() - 1).col()-move.col());

        return ((rowDiff == 1&&colDiff==0) ^ (colDiff == 1&&rowDiff==0) || (rowDiff == 1 && colDiff == 1));
    }

    @Override
    public List<Move> getPathWay(Move move) {
        List<Move> path = new ArrayList<>();
        path.add(move);
        return path;
    }

    @Override
    public List<Move> getUnverifiedMovesList() {
        Rook fakeRook = new Rook(ChessBoard.black);
        fakeRook.addMove(this.movesHistory.get(this.movesHistory.size() - 1));
        return fakeRook.getUnverifiedMovesList();
    }
}
