package ca.yorku.checkmate.Model.mainGame;

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
        temp0.addMove(this.movesHistory.getLast());
        Bishop temp1 = new Bishop(ChessBoard.black);
        temp1.addMove(this.movesHistory.getLast());
        return temp0.move(move) || temp1.move(move);
    }

    @Override
    public List<Move> getPathWay(Move move) {
        List<Move> path = new ArrayList<Move>();
        Rook fakeRook = new Rook(ChessBoard.black);
        fakeRook.addMove(this.movesHistory.getLast());
        Bishop fakeBishop = new Bishop(ChessBoard.black);
        fakeBishop.addMove(this.movesHistory.getLast());
        if(fakeRook.move(move)) {
            path = fakeRook.getPathWay(move);
        }
        else if(fakeBishop.move(move)) {
            path = fakeBishop.getPathWay(move);
        }
        return path;
    }

    @Override
    public List<Move> canThisMove() {
        Rook fakeRook = new Rook(ChessBoard.black);
        fakeRook.addMove(this.movesHistory.getLast());
        Bishop fakeBishop = new Bishop(ChessBoard.black);
        fakeBishop.addMove(this.movesHistory.getLast());
        List<Move> list = new ArrayList<>(fakeRook.canThisMove());
        list.addAll(fakeBishop.canThisMove());
        return list;
    }
}
