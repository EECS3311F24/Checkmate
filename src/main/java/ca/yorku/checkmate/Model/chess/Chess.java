package ca.yorku.checkmate.Model.chess;

import ca.yorku.checkmate.Model.chess.chesspieces.ChessPiece;

public class Chess {
    private Player whosTurn;
    private Player playerWhite;
    private Player playerBlack;
    private int numMoves = 0;
    private ChessBoard cb;
    //self note: add points system?

    public Chess(Player playerW, Player playerB) {
        this.cb = new ChessBoard();
        this.playerWhite = playerW;
        this.playerBlack = playerB;
        this.whosTurn = this.playerWhite;
    }

    public boolean move(int row, int col, ChessPiece cp) {
        boolean moved = false;
        if(this.cb.move(cp, new Move(row, col), this.whosTurn)) {
            moved = true;
            this.numMoves++;
            this.whosTurn = this.getOtherPlayer(this.whosTurn);
        }
        return moved;
    }
    private Player getOtherPlayer(Player p) {
        if(p == this.playerWhite) {return this.playerBlack;}
        else return this.playerWhite;
    }

    public boolean isGameOver(){
        return cb.checkMated!=' ';
    }

    public Player getWinner(){
        return this.getOtherPlayer(new Player(cb.checkMated));
    }

    public Player getWhosTurn() {
        return this.whosTurn;
    }

    public ChessBoard getChessBoard() {
        return this.cb;
    }

    public ChessPiece getChessPiece(int row, int col) {
        if(this.cb.getBoard()[row][col].getChar() == ' '){
            return null;
        }
        else{
            return this.cb.getBoard()[row][col].getChessPiece();
        }
    }
}
