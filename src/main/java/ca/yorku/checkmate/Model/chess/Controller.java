package ca.yorku.checkmate.Model.chess;

import ca.yorku.checkmate.Model.chess.chesspieces.ChessPiece;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// TODO remove only for testing/printing to console
public class Controller {
    private static final String INVALID_INPUT_MESSAGE = "Invalid number, please enter 1-8";
    private static final String IO_ERROR_MESSAGE = "I/O Error";
    private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
    private Chess chess;
    private Player pW;
    private Player pB;

    public Controller() {
        this.pW = new Player(ChessBoard.white);
        this.pB = new Player(ChessBoard.black);
        this.chess = new Chess(pW, pB);
    }

    public Controller(char mode, List<ChessPiece> removeBlack, List<ChessPiece> removeWhite) {
        this.pW = new Player(ChessBoard.white);
        this.pB = new Player(ChessBoard.black);
        this.chess = new Chess(pW, pB, mode, removeBlack, removeWhite);
    }

    public void play() {
        while (!chess.isGameOver()) {
            this.report();

            Move from = null;
            Move to = null;
            Player whosTurn = chess.getWhosTurn();

            from = getMove();
            to = getMove();
            this.reportMove(whosTurn.playerColor(), from, to);
            ChessPiece piece = this.chess.getChessPiece(from.row(), from.col());
            if(piece != null) {
                if(piece.getColor() == whosTurn.playerColor()) {
                    chess.move(to.row(), to.col(), piece);
                    if(chess.getPawnPromoStat() != null) {
                        char promoChar = this.getPawnPromoChar("input upgrade piece \n");
                        chess.promotePawn(promoChar);
                    }
                }
            }
        }
        this.reportFinal();
    }

    private void report() {

        String s = chess.getChessBoard().toString()
                + " " + this.chess.getWhosTurn().playerColor() + " moves next";
        System.out.println(s);
    }

    private void reportMove(char whosTurn, Move from, Move to) {
        System.out.println(whosTurn + " makes move from " + from.row() +
                ":"+from.col() + " to " + to.row() + ":"+to.col()+"\n");
    }

    private void reportFinal() {

        String s = chess.getChessBoard().toString() +
                "  " + chess.getWinner() + " won\n";
        System.out.println(s);
    }

    public Move getMove() {
        int row = getMove("row: ");
        int col = getMove("col: ");
        return new Move(row, col);
    }

    private int getMove(String message) {
        int move, lower = 0, upper = 7;
        while (true) {
            try {
                System.out.print(message);
                String line = stdin.readLine();
                line = line.trim();
                line = line.strip();
                System.out.println(line);
                move = Integer.parseInt(line);
                if (lower <= move && move <= upper) {
                    return move;
                } else {
                    System.out.println(INVALID_INPUT_MESSAGE);
                }
            } catch (IOException e) {
                System.out.println(INVALID_INPUT_MESSAGE);
                break;
            } catch (NumberFormatException e) {
                System.out.println(INVALID_INPUT_MESSAGE);
            }
        }
        return -1;
    }

    private char getPawnPromoChar(String message) {
        char promoChar;
        while (true) {
            try {
                System.out.print(message);
                String line = stdin.readLine();
                line = line.trim();
                line = line.strip();
                System.out.println(line);
                promoChar = (char)line.charAt(0);
                if (promoChar == 'Q' || promoChar == 'B' || promoChar == 'N' || promoChar == 'R') {
                    return promoChar;
                } else {
                    System.out.println(INVALID_INPUT_MESSAGE);
                }
            } catch (IOException e) {
                System.out.println(INVALID_INPUT_MESSAGE);
                break;
            } catch (NumberFormatException e) {
                System.out.println(INVALID_INPUT_MESSAGE);
            }
        }
        return ' ';
    }

    public static void main(String[] args) {
//        Controller c = new Controller('P', null, null);
        Controller c = new Controller();
        c.play();
    }
}
