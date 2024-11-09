package ca.yorku.checkmate.Model.chess;

import ca.yorku.checkmate.Model.chess.chesspieces.*;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
    static final int dimensions = 8;
    private final Placeholder[][] board;
    public static final char black = 'B';
    public static final char white = 'W';
    private List<ChessPiece> blackPieces;
    private List<ChessPiece> whitePieces;
    public List<ChessPiece> capturedPieces;
    private Move whiteKingLocation;
    private Move blackKingLocation;
    public char checkMated = ' ';

    //setup standard chess board
    public ChessBoard() {
        board = new Placeholder[dimensions][dimensions];
        this.blackPieces = new ArrayList<>();
        this.whitePieces = new ArrayList<>();
        this.capturedPieces = new ArrayList<>();
        //place blank spaces
        for (int i = 2; i < dimensions - 2; i++) {
            for (int j = 0; j < dimensions; j++) {
                board[i][j] = new Placeholder();
            }
        }
        //place black pawns
        for (int i = 0; i < dimensions; i++) {
            Pawn blackPawn = new Pawn(black);
            board[1][i] = new Placeholder(blackPawn);
            blackPawn.addMove(new Move(1, i));
            this.blackPieces.add(blackPawn);
        }
        //place white pawns
        for (int i = 0; i < dimensions; i++) {
            Pawn whitePawn = new Pawn(white);
            board[dimensions - 2][i] = new Placeholder(whitePawn);
            whitePawn.addMove(new Move(dimensions - 2, i));
            this.whitePieces.add(whitePawn);
        }
        //place black pieces
        ChessPiece blackRook = new Rook(black);
        board[0][0] = new Placeholder(blackRook);
        blackRook.addMove(new Move(0, 0));
        this.blackPieces.add(blackRook);

        ChessPiece blackKnight = new Knight(black);
        board[0][1] = new Placeholder(blackKnight);
        blackKnight.addMove(new Move(0, 1));
        this.blackPieces.add(blackKnight);

        ChessPiece blackBishop = new Bishop(black);
        board[0][2] = new Placeholder(blackBishop);
        blackBishop.addMove(new Move(0, 2));
        this.blackPieces.add(blackBishop);

        ChessPiece blackQueen = new Queen(black);
        board[0][3] = new Placeholder(blackQueen);
        blackQueen.addMove(new Move(0, 3));
        this.blackPieces.add(blackQueen);

        ChessPiece blackKing = new King(black);
        board[0][4] = new Placeholder(blackKing);
        this.blackKingLocation = new Move(0, 4);
        blackKing.addMove(this.blackKingLocation);
        this.blackPieces.add(blackKing);

        ChessPiece blackBishop2 = new Bishop(black);
        board[0][5] = new Placeholder(blackBishop2);
        blackBishop2.addMove(new Move(0, 5));
        this.blackPieces.add(blackBishop2);

        ChessPiece blackKnight2 = new Knight(black);
        board[0][6] = new Placeholder(blackKnight2);
        blackKnight2.addMove(new Move(0, 6));
        this.blackPieces.add(blackKnight2);

        ChessPiece blackRook2 = new Rook(black);
        board[0][7] = new Placeholder(blackRook2);
        blackRook2.addMove(new Move(0, 7));
        this.blackPieces.add(blackRook2);


        //place white pieces
        ChessPiece whiteRook = new Rook(white);
        board[dimensions - 1][0] = new Placeholder(whiteRook);
        whiteRook.addMove(new Move(dimensions - 1, 0));
        this.whitePieces.add(whiteRook);

        ChessPiece whiteKnight = new Knight(white);
        board[dimensions - 1][1] = new Placeholder(whiteKnight);
        whiteKnight.addMove(new Move(dimensions - 1, 1));
        this.whitePieces.add(whiteKnight);

        ChessPiece whiteBishop = new Bishop(white);
        board[dimensions - 1][2] = new Placeholder(whiteBishop);
        whiteBishop.addMove(new Move(dimensions - 1, 2));
        this.whitePieces.add(whiteBishop);

        ChessPiece whiteQueen = new Queen(white);
        board[dimensions - 1][3] = new Placeholder(whiteQueen);
        whiteQueen.addMove(new Move(dimensions - 1, 3));
        this.whitePieces.add(whiteQueen);

        ChessPiece whiteKing = new King(white);
        board[dimensions - 1][4] = new Placeholder(whiteKing);
        this.whiteKingLocation = new Move(dimensions - 1, 4);
        whiteKing.addMove(this.whiteKingLocation);
        this.whitePieces.add(whiteKing);

        ChessPiece whiteBishop2 = new Bishop(white);
        board[dimensions - 1][5] = new Placeholder(whiteBishop2);
        whiteBishop2.addMove(new Move(dimensions - 1, 5));
        this.whitePieces.add(whiteBishop2);

        ChessPiece whiteKnight2 = new Knight(white);
        board[dimensions - 1][6] = new Placeholder(whiteKnight2);
        whiteKnight2.addMove(new Move(dimensions - 1, 6));
        this.whitePieces.add(whiteKnight2);

        ChessPiece whiteRook2 = new Rook(white);
        board[dimensions - 1][7] = new Placeholder(whiteRook2);
        whiteRook2.addMove(new Move(dimensions - 1, 7));
        this.whitePieces.add(whiteRook2);
    }

    public Placeholder[][] getBoard() {
        return this.board;
    }

    public List<ChessPiece> getBlackPieces() {
        return blackPieces;
    }

    public List<ChessPiece> getWhitePieces() {
        return whitePieces;
    }

    public void updateKingLocation(Move move, King king) {
        if (king.getColor() == ChessBoard.white) this.whiteKingLocation = move;
        else this.blackKingLocation = move;
    }

    public boolean move(ChessPiece cp, Move move, char playerColor, boolean fakeMove) {
        if (this.isValid(cp, move) && cp.move(move)) {
            List<ChessPiece> capturedList = null;
            Placeholder last = board[move.row()][move.col()];
            List<Move> path = cp.getPathWay(move); //tiles to move, except knight
            List<Move> pathMinusLast = path.subList(0, path.size() - 1);
            if(last.getChar() != ' ' && last.getChessPiece().getColor() == playerColor) return false;
            if (!this.checkForAllClearPath(pathMinusLast)) return false;
            if (last.getChar() != ' ') { //capture here
                capturedList = last.getChessPiece().getColor()==ChessBoard.white ? this.whitePieces : this.blackPieces;
                capturedList.add(last.getChessPiece());
            }
            int oldRow = cp.getMovesHistory().get(cp.getMovesHistory().size() - 1).row();
            int oldCol = cp.getMovesHistory().get(cp.getMovesHistory().size() - 1).col();
            this.board[move.row()][move.col()] = new Placeholder(cp);
            this.board[oldRow][oldCol] = new Placeholder();
            cp.addMove(move);
            return passesChecks(playerColor, capturedList, cp, new Move(oldRow, oldCol), move, fakeMove);
        }
        return false;
    }

    private boolean passesChecks(char playerColor, List<ChessPiece> capturedList, ChessPiece cp, Move old, Move newSpot, boolean fakeMove){
        boolean result = true;
        if(inCheck(playerColor) || fakeMove) {
            if(capturedList != null) {
                ChessPiece revive = capturedList.get(capturedList.size()-1);
                capturedList.remove(revive);
                board[newSpot.row()][newSpot.col()] = new Placeholder(revive);
            }
            else board[newSpot.row()][newSpot.col()] = new Placeholder();
            cp.getMovesHistory().remove(cp.getMovesHistory().size()-1);
            this.board[old.row()][old.col()] = new Placeholder(cp);
            result = false;
        }
        if(!fakeMove) {
            checkCheckMate(this.getOtherPlayerColor(playerColor));
            if(cp instanceof King) this.updateKingLocation(newSpot, (King)cp);
        }
        return result;
    }

    private void checkCheckMate(char playerColor) {
        List<ChessPiece> pieces = playerColor == ChessBoard.white ? this.whitePieces : this.blackPieces;
        for(ChessPiece cp : pieces) {
            for(Move move : cp.getUnverifiedMovesList()) {
                if(this.move(cp, move,  playerColor, true)) {
                    return;
                }
            }
        }
        this.checkMated = playerColor==ChessBoard.white ? ChessBoard.white : ChessBoard.black;
    }

    private char getOtherPlayerColor(char playerColor) {
        if (playerColor == ChessBoard.white) {
            return ChessBoard.black;
        } else return ChessBoard.white;
    }

    private boolean isValid(ChessPiece cp, Move move) {
        //checks coordinates and not same
        return (move.row() >= 0 && move.row() < ChessBoard.dimensions &&
                move.col() >= 0 && move.col() < ChessBoard.dimensions)
                &&
                (move.row() != cp.getMovesHistory().get(cp.getMovesHistory().size() - 1).row() ||
                        move.col() != cp.getMovesHistory().get(cp.getMovesHistory().size() - 1).col());
    }

    public boolean hasMove(Player player) {
        //checks for move for player, checks at start of turn
        if (player.playerColor() == ChessBoard.white) {
            //have player return array of moves that needs to be checked for empty
            //create isEmpty method
            List<Move> possibleMoves = new ArrayList<>();
            for (ChessPiece cp : this.whitePieces) {
                if (this.checkForFirstEmpty(cp.getUnverifiedMovesList())) return true;
            }
        } else {
            List<Move> possibleMoves = new ArrayList<>();
            for (ChessPiece cp : this.blackPieces) {
                if (this.checkForFirstEmpty(cp.getUnverifiedMovesList())) return true;
            }
        }
        //needs to run beginning of every turn to check for draws
        return false;
    }

    private boolean checkForFirstEmpty(List<Move> moveList) {
        for (Move m : moveList) {
            if (board[m.row()][m.col()].getChar() != ' ') return true;
        }
        return false;
    }

    private boolean checkForAllClearPath(List<Move> path) {
        for (Move currentMove : path) {
            if (this.board[currentMove.row()][currentMove.col()].getChar() != ' ') {
                return false;
            }
        }
        return true;
    }

    public boolean inCheck(char player) {
        List<ChessPiece> listToLoop = player == ChessBoard.white ? this.whitePieces : this.blackPieces;
        Move kingLoc = player == ChessBoard.white ? this.whiteKingLocation : this.blackKingLocation;
        for(ChessPiece cp : listToLoop) {
            List<Move> pathToKing = cp.getPathWay(kingLoc);
            if (cp.move(kingLoc) && (pathToKing.size() == 1 || this.checkForAllClearPath(pathToKing.subList(0, pathToKing.size() - 1))))
                return true;
        }
        return false;
    }

    public boolean insufficientPieces() {
        return this.whitePieces.size() == 1 && this.blackPieces.size() == 1;
    }

    public boolean castle() {
        //TODO: check if can castle: check at start of player input (if >1 square from king origin)
        return false;
    }

    //TODO: add methods: getAllValidMoves for checksForChecks and/or castling
    //TODO: en passant

    @Override
    public String toString() {
//        String s = "______________________________" +'\n';
//        for(int i = 0; i < ChessBoard.dimensions; i++){
//            s+= "|";
//            for(int j = 0; j < ChessBoard.dimensions; j++){
//                Placeholder p = this.board[i][j];
//                s += p.getChar() + "|";
//            }
//            s += "\n";
//        }
//        return s + "______________________________"+'\n';
        String s = "";
        s += "  ";
        for (int col = 0; col < 8; col++) {
            s += col + " ";
        }
        s += '\n';

        s += " +";
        for (int col = 0; col < 8; col++) {
            s += "-+";
        }
        s += '\n';

        for (int row = 0; row < 8; row++) {
            s += row + "|";
            for (int col = 0; col < 8; col++) {
                s += this.board[row][col].getChar() + "|";
            }
            s += row + "\n";

            s += " +";
            for (int col = 0; col < 8; col++) {
                s += "-+";
            }
            s += '\n';
        }
        s += "  ";
        for (int col = 0; col < 8; col++) {
            s += col + " ";
        }
        s += '\n';
        return s;


    }

    public static void main(String[] args) {
        ChessBoard cb = new ChessBoard();
//        System.out.println(cb.toString());
//        char pW = ChessBoard.white;
//        char pB = ChessBoard.black;
//        Move from0 = new Move(6, 0);
//        Move to0 = new Move(4, 0);
//        ChessPiece cp = cb.getBoard()[from0.row()][from0.col()].getChessPiece();
//        cb.move(cp, to0, pW);
//
//        Move from1 = new Move(1, 1);
//        Move to1 = new Move(3, 1);
//        cp = cb.getBoard()[from1.row()][from1.col()].getChessPiece();
//        cb.move(cp, to1, pB);
//
//        Move from02 = new Move(4, 0);
//        Move to02 = new Move(3, 0);
//        cp = cb.getBoard()[from02.row()][from02.col()].getChessPiece();
//        cb.move(cp, to02, pW);
//
//        Move from2 = new Move(0, 1);
//        Move to2 = new Move(2, 0);
//        cp = cb.getBoard()[from2.row()][from2.col()].getChessPiece();
//        cb.move(cp, to2, pB);
//
//        Move from3 = new Move(3, 0);
//        Move to3 = new Move(2, 0);
//        cp = cb.getBoard()[from3.row()][from3.col()].getChessPiece();
//        cb.move(cp, to3, pW);
//
//        Move from4 = new Move(1, 0);
//        Move to4 = new Move(2, 0);
//        cp = cb.getBoard()[from4.row()][from4.col()].getChessPiece();
//        cb.move(cp, to4, pB);
//
//        Move from5 = new Move(7, 0);
//        Move to5 = new Move(2, 0);
//        cp = cb.getBoard()[from5.row()][from5.col()].getChessPiece();
//        cb.move(cp, to5, pW);
//
//        Move from06 = new Move(0, 2);
//        Move to06 = new Move(1, 1);
//        cp = cb.getBoard()[from06.row()][from06.col()].getChessPiece();
//        cb.move(cp, to06, pB);
//
//        Move from6 = new Move(2, 0);
//        Move to6 = new Move(0, 0);
//        cp = cb.getBoard()[from6.row()][from6.col()].getChessPiece();
//        cb.move(cp, to6, pW);
//
//        Move from7 = new Move(1, 1);
//        Move to7 = new Move(5, 5);
//        cp = cb.getBoard()[from7.row()][from7.col()].getChessPiece();
//        cb.move(cp, to7, pB);
//
//        //error start here
//        Move from8 = new Move(0, 0);
//        Move to8 = new Move(0, 3);
//        cp = cb.getBoard()[from8.row()][from8.col()].getChessPiece();
//        cb.move(cp, to8, pB);
//        //above move didn't go through try again White
//
//        Move from9 = new Move(0, 0);
//        Move to9 = new Move(0, 2);
//        cp = cb.getBoard()[from9.row()][from9.col()].getChessPiece();
//        cb.move(cp, to9, pW);
//
//        Move from10 = new Move(5, 5);
//        Move to10 = new Move(4, 4);
//        cp = cb.getBoard()[from10.row()][from10.col()].getChessPiece();
//        cb.move(cp, to10, pW);
//
//        //throw exception below
//        Move from11 = new Move(0, 2);
//        Move to11 = new Move(0, 0);
//        cp = cb.getBoard()[from11.row()][from11.col()].getChessPiece();
//        cb.move(cp, to11, pB);
//
//        Move from12 = new Move(6, 4);
//        Move to12 = new Move(5, 4);
//        cp = cb.getBoard()[from12.row()][from12.col()].getChessPiece();
//        cb.move(cp, to12, pW);
//
//        Move from13 = new Move(4, 4);
//        Move to13 = new Move(6, 2);
//        cp = cb.getBoard()[from13.row()][from13.col()].getChessPiece();
//        cb.move(cp, to13, pB);
//
//        Move from14 = new Move(7, 3);
//        Move to14 = new Move(3, 7);
//        cp = cb.getBoard()[from14.row()][from14.col()].getChessPiece();
//        cb.move(cp, to14, pW);
//
//        Move from15 = new Move(1, 5);
//        Move to15 = new Move(2, 5);
//        cp = cb.getBoard()[from15.row()][from15.col()].getChessPiece();
//        cb.move(cp, to15, pB);
//        System.out.println(cb.toString());
//
//
//        Move from16 = new Move(7, 1);
//        Move to16 = new Move(5, 2);
//        cp = cb.getBoard()[from16.row()][from16.col()].getChessPiece();
//        cb.move(cp, to16, pW);
//        System.out.println(cb.toString());
//
//
//        Move from17 = new Move(1, 4);
//        Move to17 = new Move(3, 4);
//        cp = cb.getBoard()[from17.row()][from17.col()].getChessPiece();
//        cb.move(cp, to17, pB);


        System.out.println(cb.toString());
    }
}
