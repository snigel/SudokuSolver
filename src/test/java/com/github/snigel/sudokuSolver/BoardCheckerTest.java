package com.github.snigel.sudokuSolver;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardCheckerTest {

    @Test
    public void validSolutionIsValid() throws BoardNotValidException {
        BoardChecker bc = new BoardChecker(new Board(TestPuzzles.ggboard_solved));
        assertTrue("This board should be valid!", bc.check());
    }
    @Test
    public void invalidSolutionIsInvalid() throws BoardNotValidException {
        BoardChecker bc = new BoardChecker(new Board(TestPuzzles.ggboard_invalid));
        assertFalse("This should be invalid!", bc.check());
    }

}