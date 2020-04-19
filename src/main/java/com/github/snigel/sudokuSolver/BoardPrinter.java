package com.github.snigel.sudokuSolver;

import java.util.HashSet;
import java.util.Set;

public class BoardPrinter {
    private final Board boardSet;

    public BoardPrinter(Board board) {
        this.boardSet = board;
    }

    public String print() {
        String output = "";

        for (int row = 0; row < 9; row++) {
            if (row % 3 == 0) {
                output += ("-------------\n");
            }
            for (int col = 0; col < 9; col++) {
                if (col % 3 == 0) {
                    output += ("|");
                }
                if (boardSet.knownValue(row, col)) {
                    output += (boardSet.getValue(row, col).toString());

                } else {
                    output += ("_");
                }
            }
            output += ("|\n");
        }
        output += ("-------------");
        return output;
    }

    public String printSize() {
        String output = "";

        for (int row = 0; row < 9; row++) {
            if (row % 3 == 0) {
                output += ("-------------\n");
            }
            for (int col = 0; col < 9; col++) {
                if (col % 3 == 0) {
                    output += ("|");
                }
                output += (boardSet.getAll(row, col).size());
            }
            output += ("|\n");
        }
        output += ("-------------");
        return output;
    }
}
