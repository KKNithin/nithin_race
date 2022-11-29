package fx;

import core.Player;
import core.TopScores;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    /**
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
}
