package ca.yorku.checkmate.Model.chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Player {
    private char playerColor;

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
