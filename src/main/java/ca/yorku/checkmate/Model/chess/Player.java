package ca.yorku.checkmate.Model.chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// TODO can convert to record
public class Player {
    private final char playerColor;

    // TODO remove only for testing/printing to console
    private static final String INVALID_INPUT_MESSAGE = "Invalid number, please enter 1-8";
    private static final String IO_ERROR_MESSAGE = "I/O Error";
    private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    public Player(char playerColor) {
        this.playerColor = playerColor;
    }

    public Move getMove() {
        int row = getMove("row: ");
        int col = getMove("col: ");
        return new Move(row, col);
    }

    // TODO remove only for testing/printing to console
    private int getMove(String message) {

        int move, lower = 0, upper = 7;
        while (true) {
            try {
                System.out.print(message);
                String line = Player.stdin.readLine();
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

    public char getPlayerColor() {
        return playerColor;
    }
}
