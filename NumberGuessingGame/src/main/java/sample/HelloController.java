package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;


public class HelloController implements Initializable {

    private final Random random = new Random();
    private int randomNumber;
    private int guessCount = 0;
    private final int MAX_GUESSES = 3;

    @FXML
    private TextField guess;
    @FXML
    private Text guessCounterText;
    @FXML
    private ImageView upArrow;
    @FXML
    private ImageView downArrow;
    @FXML
    private ImageView correct;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        randomNumber = random.nextInt(100)+1;
        System.out.println("Generated Number: " + randomNumber);

        upArrow.setImage(new Image(getClass().getResourceAsStream("/images/up1.png")));
        downArrow.setImage(new Image(getClass().getResourceAsStream("/images/down.png")));
        correct.setImage(new Image(getClass().getResourceAsStream("/images/correct.png")));


        downArrow.setVisible(false);
        upArrow.setVisible(false);
        correct.setVisible(false);

    }
    @FXML
    void checkGuess(ActionEvent event) {
        if (guessCount >= MAX_GUESSES){
            guessCounterText.setText("Game Over! No guesses left.");
            guess.setDisable(true);
            return;
        }
        try {
            int userGuess = Integer.parseInt(guess.getText());
            guessCount++;

            if (userGuess == randomNumber) {
                guessCounterText.setStyle("-fx-fill: #4ADE80;");
                downArrow.setVisible(false);
                upArrow.setVisible(false);
                correct.setVisible(true);
                guessCounterText.setText("Correct! Guesses: " + guessCount);
                guess.setDisable(true);

            } else if (userGuess > randomNumber) {
                guessCounterText.setStyle("-fx-fill: #F43F5E;");
                downArrow.setVisible(true);
                upArrow.setVisible(false);
                correct.setVisible(false);
                if (guessCount == MAX_GUESSES) {
                    guessCounterText.setText("Too High! Game Over!");
                    guess.setDisable(true);
                } else {
                    guessCounterText.setText("Too High! Guesses: " + guessCount);
                }
            } else {
                guessCounterText.setStyle("-fx-fill: #F43F5E;");
                downArrow.setVisible(false);
                upArrow.setVisible(true);
                correct.setVisible(false);
            if (guessCount == MAX_GUESSES) {
                guessCounterText.setText("Too Low! Game Over!");
                guess.setDisable(true);
            } else {
                guessCounterText.setText("Too Low! Guesses : " + guessCount);
            }
        }
        }catch (NumberFormatException e){
            guessCounterText.setText("Please enter a valid number.");
        }
    }

    @FXML
    void reset(ActionEvent event){
        randomNumber = random.nextInt(100) + 1;
        System.out.println("New Generated Number: " + randomNumber);
        downArrow.setVisible(false);
        upArrow.setVisible(false);
        correct.setVisible(false);
        guessCount=0;
        guessCounterText.setText("No Of Guesses: 0 ");
        guess.clear();
        guess.setDisable(false);
    }
}