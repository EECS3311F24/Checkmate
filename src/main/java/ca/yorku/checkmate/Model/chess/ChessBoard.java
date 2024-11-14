package ca.yorku.checkmate.Model.chess;

import ca.yorku.checkmate.Model.chess.chesspieces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessBoard {
    public static final int dimensions = 8;
    public Placeholder[][] board = new Placeholder[dimensions][dimensions]; //TODO: change to: private final after testing
    public static final char black = 'B';
    public static final char white = 'W';
    public List<ChessPiece> blackPieces = new ArrayList<>(); //TODO: private
    public List<ChessPiece> whitePieces = new ArrayList<>(); //TODO: private
    public List<ChessPiece> capturedPieces = new ArrayList<>();
    private Move whiteKingLocation;
    private Move blackKingLocation;
    private char checkMated = ' ';
    private Move pawnPromoStatus;
    private Move lastPlayed;

    //setup standard chess board
    public ChessBoard() {
        this.initializeBoard(Chess.standard);
    }

    public ChessBoard(char mode) {
        this.initializeBoard(mode);
    }

    public ChessBoard(List<ChessPiece> removeBlack, List<ChessPiece> removeWhite) {
        this.initializeBoard(Chess.standard);
        for(ChessPiece cp: removeBlack) {
            if(this.blackPieces.contains(cp)) {
                this.blackPieces.remove(cp);
                this.board[cp.getMovesHistory().get(0).row()][cp.getMovesHistory().get(0).col()] = new Placeholder();
            }
        }
        for(ChessPiece cp: removeWhite) {
            if(this.whitePieces.contains(cp)) {
                this.whitePieces.remove(cp);
                this.board[cp.getMovesHistory().get(0).row()][cp.getMovesHistory().get(0).col()] = new Placeholder();
            }
        }
    }

    private void initializeBoard(char mode) {
        if(mode == Chess.standard || mode == Chess.noPawns) {
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
        else if(mode == Chess.pawnsGame) {
            for (int i = 2; i < dimensions - 2; i++) {
                for (int j = 0; j < dimensions; j++) {
                    board[i][j] = new Placeholder();
                }
            }
            //place black pawns
            for (int i = 0; i < dimensions; i++) {
                Pawn blackPawn0 = new Pawn(black);
                Pawn blackPawn1 = new Pawn(black);
                if(i!=4) {
                    board[0][i] = new Placeholder(blackPawn0);
                    blackPawn0.addMove(new Move(0, i));
                    this.blackPieces.add(blackPawn0);
                }
                board[1][i] = new Placeholder(blackPawn1);
                blackPawn1.addMove(new Move(1, i));
                this.blackPieces.add(blackPawn1);
            }
            //place white pawns
            for (int i = 0; i < dimensions; i++) {
                Pawn whitePawn0 = new Pawn(white);
                Pawn whitePawn1 = new Pawn(white);
                if(i!=4) {
                    board[7][i] = new Placeholder(whitePawn0);
                    whitePawn0.addMove(new Move(7, i));
                    this.whitePieces.add(whitePawn0);
                }
                board[6][i] = new Placeholder(whitePawn1);
                whitePawn1.addMove(new Move(6, i));
                this.whitePieces.add(whitePawn1);
            }
            //place black king
            ChessPiece blackKing = new King(black);
            board[0][4] = new Placeholder(blackKing);
            this.blackKingLocation = new Move(0, 4);
            blackKing.addMove(this.blackKingLocation);
            this.blackPieces.add(blackKing);
            //place white king
            ChessPiece whiteKing = new King(white);
            board[dimensions - 1][4] = new Placeholder(whiteKing);
            this.whiteKingLocation = new Move(dimensions - 1, 4);
            whiteKing.addMove(this.whiteKingLocation);
            this.whitePieces.add(whiteKing);
        }
        if(mode==Chess.noPawns) {
            for(int i = 0; i < ChessBoard.dimensions; i++) {
                this.blackPieces.remove(this.board[1][i].getChessPiece());
                this.board[1][i] = new Placeholder();
                this.whitePieces.remove(this.board[6][i].getChessPiece());
                this.board[6][i] = new Placeholder();
            }
        }

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
            boolean pawnCapture = false;
            Move lastMove = cp.getMovesHistory().get(cp.getMovesHistory().size() - 1);
            if (cp instanceof Pawn) {
                if (this.checkPawnIllegalDiagonal((Pawn) cp, move, playerColor) == -1) return false;
                else if (Math.abs(lastMove.col() - move.col()) == 1 && Math.abs(lastMove.row() - move.row()) == 1) pawnCapture = true;
            }
            List<ChessPiece> opponentPieces = null;
            Placeholder last = board[move.row()][move.col()];
            List<Move> path = cp.getPathWay(move);
            List<Move> pathMinusLast = path.subList(0, path.size() - 1);
            if (last.getChar() != ' ' && last.getChessPiece().getColor() == playerColor) return false;
            if (!this.checkForAllClearPath(pathMinusLast)) return false;
            if ((last.getChessPiece()!=null && last.chessPiece.color == ChessBoard.getOtherPlayerColor(playerColor)) || pawnCapture) {
                if (cp instanceof Pawn && !pawnCapture) return false;
                if(pawnCapture && last.getChar()==' ') {
                    last = this.board[this.lastPlayed.row()][this.lastPlayed.col()];
                    this.board[this.lastPlayed.row()][this.lastPlayed.col()] = new Placeholder();
                }
                opponentPieces = last.getChessPiece().getColor() == ChessBoard.white ? this.whitePieces : this.blackPieces;
                opponentPieces.remove(last.getChessPiece());
                this.capturedPieces.add(last.getChessPiece());
            }
            if(cp instanceof King) if(Math.abs(move.col()-lastMove.col())==2) return this.castle((King)cp, move, fakeMove);
            this.board[move.row()][move.col()] = new Placeholder(cp);
            if (cp instanceof King) this.updateKingLocation(move, (King) cp);
            this.board[lastMove.row()][lastMove.col()] = new Placeholder();
            cp.addMove(move);
            return passesChecks(playerColor, opponentPieces, cp, lastMove, move, fakeMove);
        }
        return false;
    }

    private boolean passesChecks(char playerColor, List<ChessPiece> opponentPieces, ChessPiece cp, Move old, Move
            newSpot, boolean fakeMove) {
        boolean result = true;
        boolean inCheck = inCheck(playerColor, false);
        if (inCheck || fakeMove) {
            if (opponentPieces != null) {
                ChessPiece revive = this.capturedPieces.get(this.capturedPieces.size() - 1);
                this.capturedPieces.remove(revive);
                opponentPieces.add(revive);
                Move moveBackTo = revive.getMovesHistory().get(revive.getMovesHistory().size() - 1);
                board[moveBackTo.row()][moveBackTo.col()] = new Placeholder(revive);
            } else board[newSpot.row()][newSpot.col()] = new Placeholder();
            cp.getMovesHistory().remove(cp.getMovesHistory().size() - 1);
            this.board[old.row()][old.col()] = new Placeholder(cp);
            if (cp instanceof King) this.updateKingLocation(cp.getMovesHistory().get(cp.getMovesHistory().size() - 1), (King) cp);
            if (inCheck) result = false;
        }
        if (!fakeMove) {
            checkCheckMate(ChessBoard.getOtherPlayerColor(playerColor));
            if (cp instanceof Pawn) pawnPromoCheck((Pawn)cp);
        }
        if(result && !fakeMove) this.lastPlayed = cp.getMovesHistory().get(cp.getMovesHistory().size() - 1);
        return result;
    }

    private void checkCheckMate(char playerColor) {
        List<ChessPiece> pieces = playerColor == ChessBoard.white ? this.whitePieces : this.blackPieces;
        for (ChessPiece cp : pieces) {
            for (Move move : cp.listOfAllMoves()) {
                if (this.move(cp, move, playerColor, true)) {
                    return;
                }
            }
        }
        this.checkMated = playerColor == ChessBoard.white ? ChessBoard.white : ChessBoard.black;
    }

    public static char getOtherPlayerColor(char playerColor) {
        if (playerColor == ChessBoard.white) {
            return ChessBoard.black;
        } else return ChessBoard.white;
    }

    private boolean isValid(ChessPiece cp, Move move) {
        return (move.row() >= 0 && move.row() < ChessBoard.dimensions &&
                move.col() >= 0 && move.col() < ChessBoard.dimensions)
                &&
                (move.row() != cp.getMovesHistory().get(cp.getMovesHistory().size() - 1).row() ||
                        move.col() != cp.getMovesHistory().get(cp.getMovesHistory().size() - 1).col());
    }

    public boolean hasMove(Player player) {
        List<ChessPiece> list = player.playerColor() == ChessBoard.white ? this.whitePieces : this.blackPieces;
        for (ChessPiece cp : list) {
            for (Move m : cp.listOfShortestMoves()) {
                if (this.move(cp, m, cp.getColor(), true)) return true;
            }
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

    public boolean inCheck(char player, boolean zeroPathCheck) {
        List<ChessPiece> listToLoop = player == ChessBoard.white ? this.blackPieces : this.whitePieces;
        Move kingLoc = player == ChessBoard.white ? this.whiteKingLocation : this.blackKingLocation;
        for (ChessPiece cp : listToLoop) {
            List<Move> pathToKing = cp.getPathWay(kingLoc);
            if (cp.move(kingLoc) && (pathToKing.size() == 1 || (!pathToKing.isEmpty() && this.checkForAllClearPath(pathToKing.subList(0, pathToKing.size() - 1))))) return true;
            if(pathToKing.isEmpty() && !zeroPathCheck){//this assumes its not inCheck but this position could put it in danger to other things. need to run in Check to this position
                //break;//how to stop it infinitely running: fake move
                return inCheck(player, true);//add infinite loop stopper
            }
        }
        return false;
    }

    public char getCheckMated() {
        return this.checkMated;
    }

    public void setCheckMated(char checkMated) {
        this.checkMated = checkMated;
    }

    public boolean insufficientPieces() {
        return this.whitePieces.size() == 1 && this.blackPieces.size() == 1;
    }

    public boolean castle(King k, Move move, boolean fakeMove) {
        Move lastMove = k.getMovesHistory().get(k.getMovesHistory().size() - 1);
        if(!((k.getColor()==ChessBoard.white && lastMove.row()==7 && lastMove.col()==4 && k.getMovesHistory().size()==1) ||
                (k.getColor()==ChessBoard.black && lastMove.row()==0 && lastMove.col()==4 && k.getMovesHistory().size()==1))) return false; //checks for exact coordinates
        Placeholder h = null;
        h = move.col()==lastMove.col()-2 ? this.board[move.row()][0]:this.board[move.row()][7];
        if(h== null || !(h.getChessPiece() instanceof Rook rook)) return false;
        if(move.col()==2 && this.board[move.row()][1].getChar()!=' ') return false;//check unadded pathway for empty path
        if(rook.getMovesHistory().size()!= 1) return false;
        List<Move> path = k.getPathWay(move);
        if(this.checkForAllClearPath(path) && this.freeOfCheckCastling(k, move, path)) {
            doCastlingShuffle(k, move, rook);
            if(fakeMove) this.undoCastling(k, rook);
            else this.lastPlayed = k.getMovesHistory().get(k.getMovesHistory().size() - 1);
            return true;
        }
        return false;
    }

    private void undoCastling(King k, Rook r) {
        Move kingTo = k.getMovesHistory().get(k.getMovesHistory().size() - 2);
        Move kingFrom = k.getMovesHistory().get(k.getMovesHistory().size() - 1);
        Move rookTo = r.getMovesHistory().get(r.getMovesHistory().size() - 2);
        Move rookFrom = r.getMovesHistory().get(r.getMovesHistory().size() - 1);

        this.board[kingTo.row()][kingTo.col()] = new Placeholder(k);
        this.board[kingFrom.row()][kingFrom.col()] = new Placeholder();
        this.board[rookTo.row()][rookTo.col()] = new Placeholder(r);
        this.board[rookTo.row()][rookTo.col()] = new Placeholder();
        k.getMovesHistory().remove(k.getMovesHistory().size() - 1);
        r.getMovesHistory().remove(k.getMovesHistory().size() - 1);
        if(k.getColor()==ChessBoard.white) this.whiteKingLocation = kingTo;
        else this.blackKingLocation = kingTo;
    }

    private boolean freeOfCheckCastling(King k, Move move, List<Move> path) {
        boolean result = true;
        Move actualKingLoc = (k.getColor() == ChessBoard.white ? this.whiteKingLocation : this.blackKingLocation);
        if(inCheck(k.getColor(), false)) return false;
        for(Move m: path) {
            if(k.getColor()==ChessBoard.white) this.whiteKingLocation = m;
            else this.blackKingLocation = m;
            if(inCheck(k.getColor(), false)) result = false;
            if(k.getColor()==ChessBoard.white) this.whiteKingLocation = actualKingLoc;
            else this.blackKingLocation = actualKingLoc;
            if(!result) return result;
        }
        return true;
    }

    public void doCastlingShuffle(King k, Move move, Rook rook) {
        Move oldKing = k.getMovesHistory().get(k.getMovesHistory().size() - 1);
        Move oldRook = rook.getMovesHistory().get(rook.getMovesHistory().size() - 1);
        Move newRook = move.col()==2 ? new Move(move.row(), 3): new Move(move.row(), 5);
        this.board[oldKing.row()][oldKing.col()] = new Placeholder();
        this.board[oldRook.row()][oldRook.col()] = new Placeholder();
        this.board[move.row()][move.col()] = new Placeholder(k);
        this.board[newRook.row()][newRook.col()] = new Placeholder(rook);
        rook.addMove(newRook);
        k.addMove(move);
        this.updateKingLocation(move, k);
    }

    public boolean enPassantCheck(Pawn p, Move move) {
        ChessPiece piece = this.board[lastPlayed.row()][lastPlayed.col()].getChessPiece();
        if(piece instanceof Pawn && piece.getMovesHistory().size()>1) {
            Move otherPlayerLast = piece.getMovesHistory().get(piece.getMovesHistory().size() - 1);
            Move otherPlayer2Last = piece.getMovesHistory().get(piece.getMovesHistory().size() - 2);
            if(Math.abs(otherPlayer2Last.row()-otherPlayerLast.row())==2) {
                if(p.getColor()==ChessBoard.white) return piece == this.board[move.row() + 1][move.col()].getChessPiece();
                else return piece == this.board[move.row() - 1][move.col()].getChessPiece();
            }
        }
        return false;
    }

    private void pawnPromoCheck(Pawn p){
        Move lastMove = p.getMovesHistory().get(p.getMovesHistory().size() - 1);
        if (p.getColor() == ChessBoard.white && lastMove.row() == 0) this.pawnPromoStatus = new Move(0, lastMove.col());
        else if (lastMove.row() == 7) this.pawnPromoStatus = new Move(7, lastMove.col());
    }


    public void promotePawn(char upgrade) {
        if (this.pawnPromoStatus != null) {
            Placeholder p = this.board[this.pawnPromoStatus.row()][this.pawnPromoStatus.col()];
            if (p.getChessPiece() != null && p.getChessPiece() instanceof Pawn)
                this.promotePawnHelper((Pawn) p.getChessPiece(), upgrade);
        }
    }

    private void promotePawnHelper(Pawn p, char upgrade) { //user input to be validated in controller class, call from controller class
        Move lastMove = p.getMovesHistory().get(p.getMovesHistory().size() - 1);
        List<ChessPiece> modifyList = p.getColor() == ChessBoard.white ? this.whitePieces : this.blackPieces;
        ChessPiece cp = switch (upgrade) {
            case 'Q' -> new Queen(p.getColor());
            case 'B' -> new Bishop(p.getColor());
            case 'N' -> new Knight(p.getColor());
            case 'R' -> new Rook(p.getColor());
            default -> null;
        };
        if (cp != null) {
            modifyList.add(cp);
            this.board[lastMove.row()][lastMove.col()] = new Placeholder(cp);
            cp.addMove(lastMove);
            modifyList.remove(p);
            this.pawnPromoStatus = null;
        }
    }

    public Move getPawnPromoStatus() {
        return this.pawnPromoStatus;
    }

    private int checkPawnIllegalDiagonal(Pawn cp, Move move, char playerColor) { //false if empty&&not enP && not capturable
        Move lastMove = cp.getMovesHistory().get(cp.getMovesHistory().size() - 1);
        if (Math.abs(lastMove.row() - move.row()) == 1 && Math.abs(lastMove.col() - move.col()) == 1) { //checks if diagonal
            if ((this.board[move.row()][move.col()].getChar() == ' ' && !this.enPassantCheck(cp, move)) || //if empty & not enPassant ret F or own piece F
                    (this.board[move.row()][move.col()].getChar() != ' ' && this.board[move.row()][move.col()].getChessPiece().getColor() == playerColor)) {
                return -1;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
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
        System.out.println(cb.toString());
        ChessPiece p = cb.board[6][4].getChessPiece();
        Move to0 = new Move(4, 4);
        cb.move(p, to0, 'W', false);
    }
}
