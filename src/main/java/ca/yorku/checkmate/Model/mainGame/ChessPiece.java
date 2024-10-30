package ca.yorku.checkmate.Model.mainGame;

public abstract class ChessPiece {
    char color;
    char symbol;
    public char getChar() {
        return this.symbol;
    };
    public char getColor() {
        return this.color;
    }
}
