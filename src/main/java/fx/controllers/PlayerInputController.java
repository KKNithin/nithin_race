package fx.controllers;

import java.io.IOException;
import java.util.ArrayList;

import static models.utilities.Constants.*;
import fx.NithinRace;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * @author Nithin
 * Controller for the player names request view.
 */
public class PlayerInputController {

    @FXML
    private VBox secondPaneVbox;

    @FXML
    private Label secondBottomLabel;

    @FXML
    private Button secondOKButton;


    /**
     * Generates text fields to take input.
     * @param noOfPlayers Total number of players
     * <p>
     *      Sets the text fields to enter the player names
     * </p>
     */
    protected void setFieldsToAcceptPlayerNames(int noOfPlayers) {
        for (int i = 1; i <= noOfPlayers; i++) {
            String num = i < 10 ? ("0" + i) : String.valueOf(i);
            TextField tf = new TextField("");
            String id = "player" + i + "tf";
            tf.setId(id);
            HBox hBox = new HBox(new Label("Player (" + num + ")"), tf);
            hBox.setMargin(tf, new Insets(0, 0, 10, 40));

            secondPaneVbox.getChildren().add(hBox);
        }
    }

    /**
     * Gets all the player names entered on the dialog
     * @throws IOException If the fxml of next screen is not valid
     * <p>
     *     Validates the inputs provided by user, stores the details.
     *     Displays appropriate message if a duplicate name is entered by user.
     *     Shall switch the context of the current screen to the main game screen.
     * </p>
     */
    public void getPlayerNames() throws IOException {
        String playerName = null;
        Boolean validation = true;
        for (Node node :
                secondPaneVbox.getChildren()) {
            HBox hBox = (HBox) node;
            TextField tf = (TextField) hBox.getChildren().get(1);

            if (tf != null) {
                playerName = tf.getText();
                if (playerName.isEmpty() || playerNames.contains(playerName)) {
                    secondBottomLabel.setText("Please enter unique player names /Don't leave any field as blank");
                    playerNames = new ArrayList<>();
                    validation = false;
                    break;
                } else {
                    playerNames.add(playerName);
                }
            } else {
                throw new RuntimeException();
            }
        }
        if(validation){
            FXMLLoader fxmlLoader = new FXMLLoader(NithinRace.class.getResource("game-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) secondOKButton.getScene().getWindow();
            stage.setX(0);
            stage.setY(0);
            GameViewController gameViewController = fxmlLoader.getController();
            gameViewController.setGameViewScreen();
            stage.setTitle("Nithin's-Race");
            stage.setScene(scene);
            stage.show();
            gameViewController.initializeGrid();
        }
    }
}
