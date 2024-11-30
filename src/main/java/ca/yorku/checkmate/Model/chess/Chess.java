package ca.yorku.checkmate.Model.chess;

import ca.yorku.checkmate.Model.chess.chesspieces.ChessPiece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chess {
    private Player whosTurn;
    private Player playerWhite;
    private Player playerBlack;
    private int numMoves = 0;
    private List<Placeholder[][]>gameHistory = new ArrayList<>();
    private ChessBoard cb;
    private boolean draw;
    public static final char custom = 'C';
    public static final char standard = 'S';
    public static final char pawnsGame = 'P';
    public static final char noPawns = 'N';
    public static final List<Character> modeList = new ArrayList<>(Arrays.asList(pawnsGame, noPawns, standard));

    public Chess() {
        this.cb = new ChessBoard();
        this.playerWhite = new Player(ChessBoard.white);
        this.playerBlack = new Player(ChessBoard.black);
        this.whosTurn = this.playerWhite;
        this.gameHistory.add(this.cb.cloneBoard());
    }

    public Chess(char mode) {
        this.cb = new ChessBoard(mode);
        this.playerWhite = new Player(ChessBoard.white);
        this.playerBlack = new Player(ChessBoard.black);
        this.whosTurn = this.playerWhite;
        this.addGameHistory(this.cb.cloneBoard());
    }

    public Chess(Player playerW, Player playerB) {
        this.cb = new ChessBoard();
        this.playerWhite = playerW;
        this.playerBlack = playerB;
        this.whosTurn = this.playerWhite;
        this.addGameHistory(this.cb.cloneBoard());
    }

    public Chess(Player playerW, Player playerB, char mode, List<ChessPiece> removeBlacks, List<ChessPiece> removeWhites) {
        this.playerWhite = playerW;
        this.playerBlack = playerB;
        this.whosTurn = this.playerWhite;
        if(mode==Chess.custom) {
            this.cb = new ChessBoard(removeBlacks, removeWhites);
            this.addGameHistory(this.cb.cloneBoard());
        }
        else {
            this.cb = new ChessBoard(mode);
            this.addGameHistory(this.cb.cloneBoard());
        }
    }

    public boolean move(int row, int col, ChessPiece cp) {
        boolean moved = false;
        Move move = new Move(row, col);
        if(this.cb.move(cp, move, this.whosTurn.playerColor(), false)) {
            moved = true;
            this.numMoves++;
            this.addGameHistory(this.cb.cloneBoard());
            this.whosTurn = this.getOtherPlayer(this.whosTurn);
        }
        return moved;
    }
    public Placeholder[][] getGameState(int n) {
        return this.gameHistory.get(n);
    }

    private Player getOtherPlayer(Player p) {
        if(p.equals(this.playerWhite)) return this.playerBlack;
        else return this.playerWhite;
    }

    public boolean isGameOver(){
        return cb.getCheckMated()!=' ' || cb.insufficientPieces() || (!cb.hasMove(this.whosTurn)) || this.draw;
    }

    public void resign(Player player) {
        this.cb.setCheckMated(ChessBoard.getOtherPlayerColor(player.playerColor()));
    }

    public void draw(){
        this.draw = true;
    }
    public Player getWinner(){
        return this.getOtherPlayer(new Player(cb.getCheckMated()));
    }

    public Player getWhosTurn() {
        return this.whosTurn;
    }

    private void addGameHistory(Placeholder[][] board) {
        this.gameHistory.add(board);
    }
    public ChessBoard getChessBoard() {
        return this.cb;
    }

    public Move getPawnPromoStat() {
        return this.cb.getPawnPromoStatus();
    }

    public void promotePawn(char upgrade) {
        this.cb.promotePawn(upgrade);
    }

    public ChessPiece getChessPiece(int row, int col) {
        if(this.cb.getBoard()[row][col].getChar() == ' '){
            return null;
        }
        else{
            return this.cb.getBoard()[row][col].getChessPiece();
        }
    }

//    public static void main(String args[]) {
//        Chess c = new Chess();
//        Move move0 = new Move(6, 0);
//        Move move1 = new Move(4, 0);
//        c.move(move1.row(), move1.col(), c.cb.getBoard()[move0.row()][move0.col()].getChessPiece());
//        System.out.println("Current game state: /n");
//        System.out.println(c.getChessBoard().toString());
//        System.out.println("Game history state1: /n");
//        c.printHelper(c.getGameState(1));
//        System.out.println("Game history state0: /n");
//        c.printHelper(c.getGameState(0));
//
//        Move move2 = new Move(1, 1);
//        Move move3 = new Move(3, 1);
//        c.move(move3.row(), move3.col(), c.cb.getBoard()[move2.row()][move2.col()].getChessPiece());
//        System.out.println("Current game state: /n");
//        System.out.println(c.getChessBoard().toString());
//        System.out.println("Game history state2: /n");
//        c.printHelper(c.getGameState(2));
//        System.out.println("Game history state1: /n");
//        c.printHelper(c.getGameState(1));
//        System.out.println("Game history state0: /n");
//        c.printHelper(c.getGameState(0));
//
//        System.out.println("Current game state: /n");
//        System.out.println(c.getChessBoard().toString());    }
//
//    private void printHelper(Placeholder[][]board){
//        String s = "";
//        s += "  ";
//        for (int col = 0; col < 8; col++) {
//            s += col + " ";
//        }
//        s += '\n';
//
//        s += " +";
//        for (int col = 0; col < 8; col++) {
//            s += "-+";
//        }
//        s += '\n';
//
//        for (int row = 0; row < 8; row++) {
//            s += row + "|";
//            for (int col = 0; col < 8; col++) {
//                s += board[row][col].getChar() + "|";
//            }
//            s += row + "\n";
//
//            s += " +";
//            for (int col = 0; col < 8; col++) {
//                s += "-+";
//            }
//            s += '\n';
//        }
//        s += "  ";
//        for (int col = 0; col < 8; col++) {
//            s += col + " ";
//        }
//        s += '\n';
//        System.out.println(s);
//    }
}
