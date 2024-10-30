package ca.yorku.checkmate.Model.mainGame;

public class Chess {
    private char whosTurn = ChessBoard.white;
    private int numMoves = 0;
    private ChessBoard cb;
    //self note: add points system?

    public Chess() {
        this.cb = new ChessBoard();
    }

    public boolean move(int row, int col) {
        //TODO
        return false;
    }

    public boolean isGameOver(){
        //TODO
        return false;
    }


}
