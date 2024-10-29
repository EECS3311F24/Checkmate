package ca.yorku.checkmate.Model.mainGame;

public class ChessBoard {
    static final int dimensions = 8;
    private Placeholder[][] board;
    static final char black = 'B';
    static final char white = 'W';
    private ChessPiece[] blackPieces;
    private ChessPiece[] whitePieces;

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
            board[0][i] = new Placeholder(new Pawn(ChessBoard.black));
        }
        //place white pawns
        for(int i = 0; i < dimensions; i++) {
            board[dimensions-2][i] = new Placeholder(new Pawn(ChessBoard.white));
        }
    //place black pieces
        board[0][0] = new Placeholder(new Rook(black));
        board[0][1] = new Placeholder(new Knight(black));
        board[0][2] = new Placeholder(new Bishop(black));

        board[0][3] = new Placeholder( new Queen(black));
        board[0][4] = new Placeholder(new King(black));

        board[0][5] = new Placeholder(new Bishop(black));
        board[0][6] = new Placeholder(new Knight(black));
        board[0][7] = new Placeholder(new Rook(black));
    //place white pieces
        board[dimensions-1][0] = new Placeholder(new Rook(white));
        board[dimensions-1][1] = new Placeholder(new Knight(white));
        board[dimensions-1][2] = new Placeholder(new Bishop(white));

        board[dimensions-1][3] = new Placeholder(new Queen(white));
        board[dimensions-1][4] = new Placeholder(new King(white));

        board[dimensions-1][5] = new Placeholder(new Bishop(white));
        board[dimensions-1][6] = new Placeholder(new Knight(white));
        board[dimensions-1][7] = new Placeholder(new Rook(white));
    }
}
