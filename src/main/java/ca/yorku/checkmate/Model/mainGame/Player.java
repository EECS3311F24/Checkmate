package ca.yorku.checkmate.Model.mainGame;

public class Player {
    private ChessBoard cb;
    private char playerColor;s

    public Player(ChessBoard cb, char playerColor) {
        this.cb = cb;
        this.playerColor = playerColor;
    }


    public ChessBoard getCb() {
        return cb;
    }

    public char getPlayerColor() {
        return playerColor;
    }
}
