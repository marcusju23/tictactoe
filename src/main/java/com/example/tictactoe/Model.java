package com.example.tictactoe;

import java.util.Arrays;
import java.util.Random;

public class Model {

    public int[] board;
    private final int PLAYER = 1;
    private final int COMPUTER = -1;
    private final int EMPTY = 0;
    private int currentPlayer = PLAYER;

    public int turns = 0;

    // Scoreboard
    private int totalGames = 0;
    private int totalDraws = 0;
    private int playerXScore = 0;
    private int playerOScore = 0;

    private String winner;
    private boolean gameOver;

    Random random = new Random();

    public Model() {
        board = new int[9];
        initializeBoard();
    }

    private void initializeBoard() {
        Arrays.fill(board, EMPTY);
    }

    public void resetBoard(){
        initializeBoard();
        gameOver = false;
        turns = 0;
    }

    public void gameTurn(int buttonIndex) {
        if (!gameOver && !isDraw()) {
            setMarker(buttonIndex);
            switchTurn();
            checkForWinner();
            turns++;
        }
    }

    public int computerTurn() {
        int buttonIndex;

        do {
            buttonIndex = random.nextInt(0, 8);
            System.out.println("LOOP");
        } while (board[buttonIndex] != EMPTY);
        return buttonIndex;
    }


    public void setMarker(int buttonIndex) {
        board[buttonIndex] = currentPlayer;
    }

    public String getCurrentPlayerMarker() {
        return currentPlayer == COMPUTER ? "X" : "O";
    }

    public void switchTurn() {
        currentPlayer = (currentPlayer == COMPUTER) ? PLAYER : COMPUTER;
    }

    public void checkForWinner() {

        for (int i = 0; i < 9; i += 3) {
            if (board[i] != EMPTY && board[i] == board[i + 1] && board[i] == board[i + 2]) {
                announceWinner(board[i]); // Horizontals
                return;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[i] != EMPTY && board[i] == board[i + 3] && board[i] == board[i + 6]) {
                announceWinner(board[i]); // Verticals
                return;
            }
        }

        if (board[0] != EMPTY && board[0] == board[4] && board[0] == board[8]) {
            announceWinner(board[0]); // Diagonal 1
            return;
        }
        if (board[2] != EMPTY && board[2] == board[4] && board[2] == board[6]) {
            announceWinner(board[2]);// Diagonal 2
            return;
        }


        if (isDraw()) {
            announceWinner(EMPTY);
        }
    }

    private void announceWinner(int winner) {
        if (winner == PLAYER) {
            setWinner("Winner: X");
            playerXScore++;
        } else if (winner == COMPUTER) {
            setWinner("Winner: O");
            playerOScore++;
        } else if (winner == EMPTY){
            setWinner("It's a draw!");
            totalDraws++;
        }
        totalGames++;
        gameOver = true;
    }

    public boolean isDraw() {
        if (winner == "X" || winner == "O") {
            return false;
        }
        for (int cell : board) {
            if (cell == EMPTY) {
                return false;
            }
        }
        return true;
    }

    public void resetScoreboard() {
        setPlayerXScore(0);
        setPlayerOScore(0);
        setTotalDraws(0);
        setTotalGames(0);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String getPlayerXScoreAsString() {
        return String.valueOf(playerXScore);
    }

    public String getPlayerOScoreAsString() {
        return String.valueOf(playerOScore);
    }

    public String getTotalGamesAsString() {
        return String.valueOf(totalGames);
    }

    public String getTotalDrawsAsString() {
        return String.valueOf(totalDraws);
    }

    public String getWinnerToString() {
        return String.valueOf(winner);
    }

    public int getPLAYER() {
        return PLAYER;
    }

    public void setPlayerOScore(int playerOScore) {
        this.playerOScore = playerOScore;
    }

    public void setPlayerXScore(int playerXScore) {
        this.playerXScore = playerXScore;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    public void setTotalDraws(int totalDraws) {
        this.totalDraws = totalDraws;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}