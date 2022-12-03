package fx;

import java.io.IOException;

import static utilities.Constants.difficultyLevel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import utilities.Constants;

/**
 * @author Nithin
 * Controller for the initial board details request screen of Java FX
 */
public class BoardInputController {
    @FXML
    private Button FirstSceneOK;

    @FXML
    private MenuButton rowMenu;

    @FXML
    private MenuButton colMenu;
    @FXML
    private MenuButton playerMenu;
    @FXML
    private MenuButton difficultyLevelMenu;
    @FXML
    private Label errorLabel;

    private Integer rows;
    private Integer columns;
    private Integer noOfPlayers;

    /**
     * Shall initialise the row, column, number of player and difficulty level dropdown menus.
     */
    public void init() {
        for (int i = 4; i <= 20; i++) {
            MenuItem rowItem = new MenuItem(String.valueOf(i));
            rowItem.setOnAction(e -> rowMenu.setText(((MenuItem) e.getSource()).getText()));
            MenuItem colItem = new MenuItem(String.valueOf(i));
            colItem.setOnAction(e -> colMenu.setText(((MenuItem) e.getSource()).getText()));
            rowMenu.getItems().add(rowItem);
            colMenu.getItems().add(colItem);
        }
        for (int i = 2; i < 16; i++) {
            MenuItem playerItem = new MenuItem(String.valueOf(i));
            playerItem.setOnAction(e -> playerMenu.setText(((MenuItem)e.getSource()).getText()));
            playerMenu.getItems().add(playerItem);
        }

        MenuItem easy = new MenuItem("Easy");
        MenuItem medium = new MenuItem("Medium");
        MenuItem difficult = new MenuItem("Difficult");
        easy.setOnAction(e -> difficultyLevelMenu.setText(((MenuItem)e.getSource()).getText()));
        medium.setOnAction(e -> difficultyLevelMenu.setText(((MenuItem)e.getSource()).getText()));
        difficult.setOnAction(e -> difficultyLevelMenu.setText(((MenuItem)e.getSource()).getText()));
        difficultyLevelMenu.getItems().add(easy);
        difficultyLevelMenu.getItems().add(medium);
        difficultyLevelMenu.getItems().add(difficult);
    }

    /**
     * Shall be called when the OK button is pressed by the user.
     * <p>
     *     Validates the given row, column, and number of players input.
     *     Error message is shown if the number of players are more than column.
     *     Shall store the input details and switch to the next screen if everything is valid.
     * </p>
     */
    @FXML
    protected void processFirstSecneOKEvent() {
        try {
            rows = Integer.parseInt(rowMenu.getText());
            columns = Integer.parseInt(colMenu.getText());
            noOfPlayers = Integer.parseInt(playerMenu.getText());
            difficultyLevel = difficultyLevelMenu.getText();
            if (noOfPlayers > columns) {
                errorLabel.setText("No. of players should be lesser than No. of columns");
            } else {
                Constants.rows = rows + 2;
                Constants.columns = columns;
                Constants.numberOfPlayers = noOfPlayers;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("second-view.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) FirstSceneOK.getScene().getWindow();
                PlayerInputController playerInputController = fxmlLoader.getController();
                playerInputController.setFieldsToAcceptPlayerNames(noOfPlayers);
                stage.setTitle("Nithin's-Race");
                stage.setScene(scene);
                stage.show();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}