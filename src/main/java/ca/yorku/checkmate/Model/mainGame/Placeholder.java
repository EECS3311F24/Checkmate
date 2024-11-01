package mainGame;

public class Placeholder {
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
}
