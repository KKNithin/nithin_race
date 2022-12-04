package fx.controllers;

import java.io.IOException;
import java.util.ArrayList;

import fx.NithinRace;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static models.utilities.Constants.*;
import models.core.Player;
import models.core.TopScores;

/**
 * @author Nithin
 * Controller for the final winner announcement screen.
 */
public class WinnerTopScoresViewController {
    @FXML
    private TableView topScoreTable;
    @FXML
    private TableColumn<PlayerScore, String> topScoreNameColumn;

    @FXML
    private TableColumn<PlayerScore, String> topScoreValueColumn;

    @FXML
    private Label winnerPlayerName;
    @FXML
    private Label winnerPlayerScore;

    @FXML
    private Button playAgain;

    /**
     * Sets the winner screen along with top scores
     * @param topScores All the top scores to be displayed.
     * @param currentWinner Current game winner.
     */
    public void initialize(TopScores topScores, Player currentWinner) {
        winnerPlayerName.setText(currentWinner.getName());
        winnerPlayerScore.setText(String.valueOf(currentWinner.getScore()));
        topScoreNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        topScoreValueColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        for(Player topScorePlayer: topScores.getPlayerList()) {
            topScoreTable.getItems().add(new PlayerScore(topScorePlayer.getName(), topScorePlayer.getScore()));
        }
    }

    public void startGameFromBegin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NithinRace.class.getResource("board-input-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        BoardInputController boardInputController = fxmlLoader.getController();
        boardInputController.init();
        playerNames = new ArrayList<>();
        allPlayers = new ArrayList<>();
        playersInitialPos = new ArrayList<>();
        Stage stage = (Stage) playAgain.getScene().getWindow();
        stage.setTitle("Nithin's-Race");
        stage.setScene(scene);
        stage.show();
    }
}
