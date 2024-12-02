package ca.yorku.checkmate.Model.chess;

import ca.yorku.checkmate.Model.chess.chesspieces.ChessPiece;

public class Placeholder implements Cloneable {
    private char piece;
    private ChessPiece chessPiece;

    public Placeholder() {
        this.piece = ' ';
    }

    public Placeholder(ChessPiece cp) {
        this.chessPiece = cp;
        this.piece = cp.getChar();
    }

    public char getChar(){
        return this.piece;
    }

    public ChessPiece getChessPiece(){
        return this.chessPiece;
    }
    public void emptyPlaceholder(){
        this.chessPiece = null;
        this.piece = ' ';
    }

    @Override
    public Placeholder clone() {
        if(this.chessPiece == null){return new Placeholder();}
        else return new Placeholder(this.chessPiece.clone());
    }

}
