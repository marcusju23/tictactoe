package com.example.tictactoe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ModelTest {

    private Model model;

    @Before
    public void setUp() {
        model = new Model();
    }

    @Test
    public void testCheckForHorizontalWinner() {
        model.setMarker(0);
        model.setMarker(1);
        model.setMarker(2);
        model.checkForWinner();
        assertTrue(model.isGameOver());
        assertEquals("Winner: X", model.getWinnerToString());
    }

    @Test
    public void testCheckForVerticalWinner() {
        model.setMarker(0);
        model.setMarker(3);
        model.setMarker(6);
        model.checkForWinner();
        assertTrue(model.isGameOver());
        assertEquals("Winner: X", model.getWinnerToString());
    }

    @Test
    public void testCheckForDiagonalWinner() {
        model.setMarker(0);
        model.setMarker(4);
        model.setMarker(8);
        model.checkForWinner();
        assertTrue(model.isGameOver());
        assertEquals("Winner: X", model.getWinnerToString());
    }

    @Test
    public void testCheckForOtherDiagonalWinner() {
        model.setMarker(2);
        model.setMarker(4);
        model.setMarker(6);
        model.checkForWinner();
        assertTrue(model.isGameOver());
        assertEquals("Winner: X", model.getWinnerToString());
    }

    @Test
    public void testGameTurnValidMove() {
        model.gameTurn(0);
        assertEquals(1, model.turns);
        assertEquals("X", model.getCurrentPlayerMarker());
        assertFalse(model.isGameOver());
    }

    @Test
    public void testGameTurnInvalidMove() {
        // Same cell twice (invalid move)
        model.gameTurn(0);
        assertEquals(1, model.turns);
        assertEquals("X", model.getCurrentPlayerMarker());

        // Attempt to make the same move again
        model.gameTurn(0);
        // Turns and currentPlayer should not change
        assertEquals(2, model.turns);
        assertEquals("O", model.getCurrentPlayerMarker());
        // The game should not be over
        assertFalse(model.isGameOver());
    }
}
