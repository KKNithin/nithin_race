package fx;

import java.io.IOException;

import static utilities.Constants.*;
import javafx.event.ActionEvent;
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
public class SecondViewController {

    @FXML
    private VBox secondPaneVbox;

    @FXML
    private Label secondBottomLabel;

    @FXML
    private Button secondOKButton;


    /**
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
            hBox.setMargin(tf, new Insets(0, 0, 20, 60));

            secondPaneVbox.getChildren().add(hBox);
        }
    }

    /**
     * @throws IOException If the fxml of next screen is not valid
     * <p>
     *     Validates the inputs provided by user, stores the details.
     *     Displays appropriate message if a duplicate name is entered by user.
     *     Shall switch the context of the current screen to the main game screen.
     * </p>
     */
    public void getPlayerNames() throws IOException {
        String playerName = null;
        for (Node node :
                secondPaneVbox.getChildren()) {
            HBox hBox = (HBox) node;
            TextField tf = (TextField) hBox.getChildren().get(1);

            if (tf != null) {
                playerName = tf.getText();
                if (playerName.isEmpty()) {
                    secondBottomLabel.setText("Please enter all the Names properly");
                } else if(!playerNames.contains(playerName)){
                        playerNames.add(playerName);
                } else {
                    secondBottomLabel.setText("Player Name: '"+playerName + "'" + ", seems to be there - Please " +
                            "keep the names unique");
                }
            } else {
                throw new RuntimeException();
            }
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) secondOKButton.getScene().getWindow();
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        screenHeight = screenBounds.getHeight();
        screenWidth = screenBounds.getWidth();
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
