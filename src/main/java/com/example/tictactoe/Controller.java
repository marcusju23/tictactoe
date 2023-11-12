package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.List;

public class Controller {

    @FXML
    private Button
            button1, button2, button3,
            button4, button5, button6,
            button7, button8, button9;
    @FXML
    private List<Button> buttons;
    @FXML
    private Button newGameButton;
    @FXML
    private Text winnerText;
    @FXML
    private Label playerXLabel;
    @FXML
    private Label playerOLabel;
    @FXML
    private Label totalGamesLabel;
    @FXML
    private Label totalDrawsLabel;

    private static final Model model = new Model();

    public void initialize() {
        buttons = Arrays.asList(
                button1, button2, button3,
                button4, button5, button6,
                button7, button8, button9
        );
    }

    @FXML
    private void onResetAction() {
        onNewGame();
        model.resetScoreboard();
        updateScoreboard();
    }

    @FXML
    private void onNewGame() {
        winnerText.setText("");
        model.resetBoard();
        model.setCurrentPlayer(model.getPLAYER());
        model.setWinner("");
        newGameButton.setDisable(false);
        resetButtons();
    }

    @FXML
    private void onButtonClick(ActionEvent actionEvent) {
        newGameButton.setDisable(true);

        Button clickedButton = (Button) actionEvent.getSource();
        int buttonIndex = buttons.indexOf(clickedButton);
        model.gameTurn(buttonIndex);
        clickedButton.setText(model.getCurrentPlayerMarker());
        clickedButton.setDisable(true);
        updateGameState();

        updateGameState();
        if (!model.isGameOver()) {
            computerTurn();
            updateGameState();
        }
    }

    private void computerTurn() {
        int buttonIndex = model.computerTurn();
        model.gameTurn(buttonIndex);
        buttons.get(buttonIndex).setText(model.getCurrentPlayerMarker());
        buttons.get(buttonIndex).setDisable(true);
        updateGameState();
    }

    private void updateScoreboard() {
        totalGamesLabel.setText("Total Games: " + model.getTotalGamesAsString());
        playerXLabel.setText("Player : " + model.getPlayerXScoreAsString());
        playerOLabel.setText("Computer : " + model.getPlayerOScoreAsString());
        totalDrawsLabel.setText("Draws: " + model.getTotalDrawsAsString());
    }

    private void resetButtons() {
        for (Button button : buttons) {
            button.setText("");
            button.setDisable(false);
        }
    }

    private void disableAllButtons() {
        for (Button button : buttons) {
            button.setDisable(true);
        }
    }

    private void updateWinnerText() {
        winnerText.setText(model.getWinnerToString());
    }

    private void updateGameState() {
        if (model.isGameOver()) {
            disableAllButtons();
            updateWinnerText();
            updateScoreboard();
            newGameButton.setDisable(false);
        }
    }
}