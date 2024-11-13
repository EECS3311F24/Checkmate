package ca.yorku.checkmate.Model.chess;

import ca.yorku.checkmate.Model.chess.chesspieces.ChessPiece;

public class Placeholder {
    public char piece; //TODO: private
    public ChessPiece chessPiece;//TODO: private

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
}
