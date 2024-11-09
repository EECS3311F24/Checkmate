package ca.yorku.checkmate.Model.chess.chesspieces;

import ca.yorku.checkmate.Model.chess.ChessBoard;
import ca.yorku.checkmate.Model.chess.Move;

import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece {

    public Queen(char color) {
        this.color = color;
        this.symbol = 'Q';
    }

    @Override
    public boolean move(Move move) {
        Rook temp0 = new Rook(ChessBoard.black);
        temp0.addMove(this.movesHistory.get(this.movesHistory.size() - 1));
        Bishop temp1 = new Bishop(ChessBoard.black);
        temp1.addMove(this.movesHistory.get(this.movesHistory.size() - 1));
        return temp0.move(move) || temp1.move(move);
    }

    @Override
    public List<Move> getPathWay(Move move) {
        List<Move> path = new ArrayList<Move>();
        Rook fakeRook = new Rook(ChessBoard.black);
        fakeRook.addMove(this.movesHistory.get(this.movesHistory.size() - 1));
        Bishop fakeBishop = new Bishop(ChessBoard.black);
        fakeBishop.addMove(this.movesHistory.get(this.movesHistory.size() - 1));
        if(fakeRook.move(move)) {
            path = fakeRook.getPathWay(move);
        }
        else if(fakeBishop.move(move)) {
            path = fakeBishop.getPathWay(move);
        }
        return path;
    }

    @Override
    public List<Move> listOfShortestMoves() {
        Rook fakeRook = new Rook(ChessBoard.black);
        fakeRook.addMove(this.movesHistory.get(this.movesHistory.size() - 1));
        Bishop fakeBishop = new Bishop(ChessBoard.black);
        fakeBishop.addMove(this.movesHistory.get(this.movesHistory.size() - 1));
        List<Move> list = new ArrayList<>(fakeRook.listOfShortestMoves());
        list.addAll(fakeBishop.listOfShortestMoves());
        return list;
    }
}
