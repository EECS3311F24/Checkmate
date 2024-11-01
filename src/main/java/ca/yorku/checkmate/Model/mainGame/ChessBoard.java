package ca.yorku.checkmate.Model.mainGame;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
    static final int dimensions = 8;
    private Placeholder[][] board;
    static final char black = 'B';
    static final char white = 'W';
    private List<ChessPiece> blackPieces;
    private List<ChessPiece> whitePieces;
    private boolean whiteInCheck;
    private boolean blackInCheck;
    private Move whiteKingLocation;
    private Move blackKingLocation;
    private List<ChessPiece> capturedPieces;

    //setup standard chess board
    public ChessBoard(){
        board = new Placeholder[dimensions][dimensions];
        this.blackPieces = new ArrayList<>();
        this.whitePieces = new ArrayList<>();
        this.capturedPieces = new ArrayList<>();
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
            this.blackPieces.add(blackPawn);
        }
        //place white pawns
        for(int i = 0; i < dimensions; i++) {
            Pawn whitePawn = new Pawn(white);
            board[dimensions-2][i] = new Placeholder(whitePawn);
            whitePawn.addMove(new Move(dimensions-2, i));
            this.whitePieces.add(whitePawn);
        }
    //place black pieces
        ChessPiece blackRook = new Rook(black);
        board[0][0] = new Placeholder(blackRook);
        blackRook.addMove(new Move(0,0));
        this.blackPieces.add(blackRook);

        ChessPiece blackKnight = new Knight(black);
        board[0][1] = new Placeholder(blackKnight);
        blackKnight.addMove(new Move(0,1));
        this.blackPieces.add(blackKnight);

        ChessPiece blackBishop = new Bishop(black);
        board[0][2] = new Placeholder(blackBishop);
        blackBishop.addMove(new Move(0,2));
        this.blackPieces.add(blackBishop);

        ChessPiece blackQueen = new Queen(black);
        board[0][3] = new Placeholder(blackQueen);
        blackQueen.addMove(new Move(0,3));
        this.blackPieces.add(blackQueen);

        ChessPiece blackKing = new King(black);
        board[0][4] = new Placeholder(blackKing);
        this.blackKingLocation = new Move(0,4);
        blackKing.addMove(this.blackKingLocation);
        this.blackPieces.add(blackKing);

        ChessPiece blackBishop2 = new Bishop(black);
        board[0][5] = new Placeholder(blackBishop2);
        blackBishop2.addMove(new Move(0,5));
        this.blackPieces.add(blackBishop2);

        ChessPiece blackKnight2 = new Knight(black);
        board[0][6] = new Placeholder(blackKnight2);
        blackKnight2.addMove(new Move(0,6));
        this.blackPieces.add(blackKnight2);

        ChessPiece blackRook2 = new Rook(black);
        board[0][7] = new Placeholder(blackRook2);
        blackRook2.addMove(new Move(0,7));
        this.blackPieces.add(blackRook2);


    //place white pieces
        ChessPiece whiteRook = new Rook(white);
        board[dimensions-1][0] = new Placeholder(whiteRook);
        whiteRook.addMove(new Move(dimensions-1,0));
        this.whitePieces.add(whiteRook);

        ChessPiece whiteKnight = new Knight(white);
        board[dimensions-1][1] = new Placeholder(whiteKnight);
        whiteKnight.addMove(new Move(dimensions-1,1));
        this.whitePieces.add(whiteKnight);

        ChessPiece whiteBishop = new Bishop(white);
        board[dimensions-1][2] = new Placeholder(whiteBishop);
        whiteBishop.addMove(new Move(dimensions-1,2));
        this.whitePieces.add(whiteBishop);

        ChessPiece whiteQueen = new Queen(white);
        board[dimensions-1][3] = new Placeholder(whiteQueen);
        whiteQueen.addMove(new Move(dimensions-1,3));
        this.whitePieces.add(whiteQueen);

        ChessPiece whiteKing = new King(white);
        board[dimensions-1][4] = new Placeholder(whiteKing);
        this.whiteKingLocation = new Move(dimensions-1,4);
        whiteKing.addMove(this.whiteKingLocation);
        this.whitePieces.add(whiteKing);

        ChessPiece whiteBishop2 = new Bishop(white);
        board[dimensions-1][5] = new Placeholder(whiteBishop2);
        whiteBishop2.addMove(new Move(dimensions-1,5));
        this.whitePieces.add(whiteBishop2);

        ChessPiece whiteKnight2 = new Knight(white);
        board[dimensions-1][6] = new Placeholder(whiteKnight2);
        whiteKnight2.addMove(new Move(dimensions-1,6));
        this.whitePieces.add(whiteKnight2);

        ChessPiece whiteRook2 = new Rook(white);
        board[dimensions-1][7] = new Placeholder(whiteRook2);
        whiteRook2.addMove(new Move(dimensions-1,7));
        this.whitePieces.add(whiteRook2);
    }

    public Placeholder[][] getBoard(){
        return this.board;
    }

    public List<ChessPiece> getBlackPieces() {
        return blackPieces;
    }

    public List<ChessPiece> getWhitePieces() {
        return whitePieces;
    }

    public void updateKingLocation(Move move, King king) {
        if(king.getColor() == ChessBoard.white) this.whiteKingLocation = move;
        else this.blackKingLocation = move;
    }

    public boolean move(ChessPiece cp, Move move, Player player) {
        //move + capture
        if(this.isValid(cp, move) && cp.move(move)) {
            List<Move> path = cp.getPathWay(move);
            List<Move> pathMinusLast = path.subList(0, path.size()-1);
            if(this.checkForAllClearPath(pathMinusLast)) {
                Placeholder last = board[path.getLast().getRow()][path.getLast().getColumn()];
                if(last.getChar() == this.getOtherPlayerColor(player)) {
                    this.capturedPieces.add(last.getChessPiece());
                    if(this.getOtherPlayerColor(player) == ChessBoard.white) this.whitePieces.remove(last.getChessPiece());
                    else this.blackPieces.remove(last.getChessPiece());
                    last.emptyPlaceholder();
                }
                int oldRow = cp.movesHistory.getLast().getRow();
                int oldCol = cp.movesHistory.getLast().getColumn();
                this.board[move.getRow()][move.getColumn()] = this.board[oldRow][oldCol];
                this.board[oldRow][oldCol].emptyPlaceholder();
                cp.addMove(move);
                if(cp instanceof King) this.updateKingLocation(move, (King) cp);
                return true;
            }
        }
        return false;
    }

    private char getOtherPlayerColor(Player player) {
        if(player.getPlayerColor() == ChessBoard.white) {
            return ChessBoard.black;
        }
        else return ChessBoard.white;
    }

    private boolean isValid(ChessPiece cp, Move move) {
        //checks coordinates and not same
        return (move.getRow() > 0 && move.getRow() < ChessBoard.dimensions &&
                move.getColumn() > 0 && move.getColumn() < ChessBoard.dimensions)
                &&
                (move.getRow() != cp.getMovesHistory().getLast().getRow() ||
                move.getColumn() != cp.getMovesHistory().getLast().getColumn());
    }

    public boolean hasMove(Player player) {
        //checks for move for player, checks at start of turn
        if (player.getPlayerColor() == ChessBoard.white) {
            //have player return array of moves that needs to be checked for empty
            //create isEmpty method
            List<Move> possibleMoves = new ArrayList<>();
            for(ChessPiece cp: this.whitePieces) {
                if(this.checkForFirstEmpty(cp.canThisMove())) return true;
            }
        }
        else {
            List<Move> possibleMoves = new ArrayList<>();
            for(ChessPiece cp: this.blackPieces) {
                if(this.checkForFirstEmpty(cp.canThisMove())) return true;
            }
        }
        //needs to run beginning of every turn to check for draws
        return false;
    }

    private boolean checkForFirstEmpty(List<Move> moveList) {
        for(Move m: moveList) {
            if(board[m.getRow()][m.getColumn()].getChar() != ' ') return true;
        }
        return false;
    }

    private boolean checkForAllClearPath(List<Move> path) {
        for (Move currentMove : path) {
            if (this.board[currentMove.getRow()][currentMove.getColumn()].getChar() != ' ') {
                return false;
            }
        }
        return true;
    }

    public boolean inCheck(Player player) {
        //checks for checks beginning of each turn
        //create move in King position and pass to each chess piece
        if(player.getPlayerColor() == ChessBoard.white) {
            Move kingLoc = this.whiteKingLocation;
            for(ChessPiece cp: this.blackPieces) {
                if(cp.move(kingLoc) && this.checkForAllClearPath(cp.getPathWay(kingLoc))) return true;
            }
        }
        else {
            Move kingLoc = this.blackKingLocation;
            for(ChessPiece cp: this.whitePieces) {
                if(cp.move(kingLoc) && this.checkForAllClearPath(cp.getPathWay(kingLoc))) return true;
            }
        }
        return false;
    }

    public boolean insufficientPieces(){
        return this.whitePieces.size()==1 && this.blackPieces.size()==1;
    }

    public boolean castle(){
        //TODO: check if can castle: check at start of player input (if >1 square from king origin)
        return false;
    }

    //TODO: add methods: getAllValidMoves for checksForChecks and/or castling
    //TODO: en passant

    public static void main(String[] args){
        new ChessBoard();
    }
}
