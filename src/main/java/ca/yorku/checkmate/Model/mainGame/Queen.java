package ca.yorku.checkmate.Model.mainGame;

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
}
