package com.github.snigel.sudokuSolver;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardSolverTest {

    @Test
    public void solvableBoardIsSolved() throws BoardNotValidException {
        assertTrue("This board should be solvable.", new BoardSolver(new Board(TestPuzzles.ggboard)).solveBoard());
    }
    @Test
    public void unsolvableBoardIsNotSolved() throws BoardNotValidException {
        assertFalse("This board should not be solvable.", new BoardSolver(new Board(TestPuzzles.empty_board)).solveBoard());
    }
}