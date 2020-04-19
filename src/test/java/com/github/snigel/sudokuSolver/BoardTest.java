package com.github.snigel.sudokuSolver;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardTest {

    @Test
    public void doneBoardIsDone() throws BoardNotValidException {
        assertTrue("This board should be reported as done.", new Board(TestPuzzles.ggboard_solved).isDone());
    }
    @Test
    public void doneBoardIsNotDone() throws BoardNotValidException {
        assertFalse("This board should not be reported as done.", new Board(TestPuzzles.ggboard).isDone());
    }

}