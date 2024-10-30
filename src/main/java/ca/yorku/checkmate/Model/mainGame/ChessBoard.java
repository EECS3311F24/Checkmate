package ca.yorku.checkmate.Model.mainGame;

public class ChessBoard {
    static final int dimensions = 8;
    private Placeholder[][] board;
    static final char black = 'B';
    static final char white = 'W';
    private ChessPiece[] blackPieces;
    private ChessPiece[] whitePieces;

    //setup standard chess board
    public ChessBoard(){
        board = new Placeholder[dimensions][dimensions];
        //place blank spaces
        for(int i = 2; i < dimensions-2; i++){
            for(int j = 0; j < dimensions; j++){
                board[i][j] = new Placeholder();
            }
        }
        //place black pawns
        for(int i = 0; i < dimensions; i++){
            Pawn blackPawn = new Pawn(black);
            board[0][i] = new Placeholder(blackPawn);
            blackPawn.addMove(new Move(0, i));
        }
        //place white pawns
        for(int i = 0; i < dimensions; i++) {
            Pawn whitePawn = new Pawn(white);
            board[dimensions-2][i] = new Placeholder(whitePawn);
            whitePawn.addMove(new Move(dimensions-2, i));
        }
    //place black pieces
        ChessPiece blackRook = new Rook(black);
        board[0][0] = new Placeholder(blackRook);
        blackRook.addMove(new Move(0,0));

        ChessPiece blackKnight = new Knight(black);
        board[0][1] = new Placeholder(blackKnight);
        blackKnight.addMove(new Move(0,1));

        ChessPiece blackBishop = new Bishop(black);
        board[0][2] = new Placeholder(blackBishop);
        blackBishop.addMove(new Move(0,2));

        ChessPiece blackQueen = new Queen(black);
        board[0][3] = new Placeholder(blackQueen);
        blackQueen.addMove(new Move(0,3));

        ChessPiece blackKing = new King(black);
        board[0][4] = new Placeholder(blackKing);
        blackKing.addMove(new Move(0,4));

        ChessPiece blackBishop2 = new Bishop(black);
        board[0][5] = new Placeholder(blackBishop2);
        blackBishop2.addMove(new Move(0,5));

        ChessPiece blackKnight2 = new Knight(black);
        board[0][6] = new Placeholder(blackKnight2);
        blackKnight2.addMove(new Move(0,6));

        ChessPiece blackRook2 = new Rook(black);
        board[0][7] = new Placeholder(blackRook2);
        blackRook2.addMove(new Move(0,7));


    //place white pieces
        ChessPiece whiteRook = new Rook(white);
        board[dimensions-1][0] = new Placeholder(whiteRook);
        whiteRook.addMove(new Move(dimensions-1,0));

        ChessPiece whiteKnight = new Knight(white);
        board[dimensions-1][1] = new Placeholder(whiteKnight);
        whiteKnight.addMove(new Move(dimensions-1,1));

        ChessPiece whiteBishop = new Bishop(white);
        board[dimensions-1][2] = new Placeholder(whiteBishop);
        whiteBishop.addMove(new Move(dimensions-1,2));

        ChessPiece whiteQueen = new Queen(white);
        board[dimensions-1][3] = new Placeholder(whiteQueen);
        whiteQueen.addMove(new Move(dimensions-1,3));

        ChessPiece whiteKing = new King(white);
        board[dimensions-1][4] = new Placeholder(whiteKing);
        whiteKing.addMove(new Move(dimensions-1,4));

        ChessPiece whiteBishop2 = new Bishop(white);
        board[dimensions-1][5] = new Placeholder(whiteBishop2);
        whiteBishop2.addMove(new Move(dimensions-1,5));

        ChessPiece whiteKnight2 = new Knight(white);
        board[dimensions-1][6] = new Placeholder(whiteKnight2);
        whiteKnight2.addMove(new Move(dimensions-1,6));

        ChessPiece whiteRook2 = new Rook(white);
        board[dimensions-1][7] = new Placeholder(whiteRook2);
        whiteRook2.addMove(new Move(dimensions-1,7));
    }

    public Placeholder[][] getBoard(){
        return this.board;
    }

    public ChessPiece[] getBlackPieces() {
        return blackPieces;
    }

    public ChessPiece[] getWhitePieces() {
        return whitePieces;
    }

    public void move(ChessPiece cp, Move move) {
        //TODO: check validity, replace placeholder, move cp to new coordinate
    }

    public static void main(String[] args){
        new ChessBoard();
    }
}
