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
        if(this.getCastling().contains(move)) return true;
        return ((rowDiff == 1&&colDiff==0) ^ (colDiff == 1&&rowDiff==0) || (rowDiff == 1 && colDiff == 1));
    }

    @Override //ADD FOR CASTLING
    public List<Move> getPathWay(Move move) {
        List<Move> path = new ArrayList<>();
        if(this.getCastling().contains(move)) {
            if(move.col()==2) path.add(new Move(move.row(), 3));
            else if (move.col()==6) path.add(new Move(move.row(), 5));
        }
        path.add(move);
        return path;
    }

    private List<Move> getCastling() {
        List<Move> moves = new ArrayList<>();
        Move lastMove = this.movesHistory.get(this.movesHistory.size() - 1);
        moves.add(new Move(lastMove.row(), lastMove.col()+2));
        moves.add(new Move(lastMove.row(), lastMove.col()-2));
        return moves;
    }

    @Override
    public List<Move> listOfShortestMoves() {
        Queen fakeQueen = new Queen(ChessBoard.black);
        fakeQueen.addMove(this.movesHistory.get(this.movesHistory.size() - 1));
        List<Move> result = new ArrayList<>(fakeQueen.listOfShortestMoves());
        result.addAll(this.getCastling());
        return result;
    }

    @Override
    public List<Move> listOfAllMoves() {
        return this.listOfShortestMoves();
    }
}
