package ca.yorku.checkmate.Model.chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// TODO can convert to record
public class Player {
    private final char playerColor;

    public Player(char playerColor) {
        this.playerColor = playerColor;
    }

    public char getPlayerColor() {
        return playerColor;
    }
}
