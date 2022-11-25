package fx;

import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

import static utilities.Constants.*;
import core.StartGame;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SecondViewController {

    @FXML
    private VBox secondPaneVbox;

    @FXML
    private Label secondBottomLabel;

    @FXML
    private Button secondOKButton;

    //    private List<String> playerIds = new ArrayList<>();

    protected void setFieldsToAcceptPlayerNames(int noOfPlayers) {
        for (int i = 1; i <= noOfPlayers; i++) {
            String num = i < 10 ? ("0" + i) : String.valueOf(i);
//            Label l = new Label("Player(" + i + ")");
            TextField tf = new TextField("");
            String id = "player" + i + "tf";
//            playerIds.add(id);
            tf.setId(id);
            HBox hBox = new HBox(new Label("Player (" + num + ")"), tf);
            hBox.setMargin(tf, new Insets(0, 0, 20, 60));

            secondPaneVbox.getChildren().add(hBox);
        }
    }

    public void getPlayerNames(ActionEvent actionEvent) throws IOException {
        String playerName = null;
        for (Node node :
                secondPaneVbox.getChildren()) {
            HBox hBox = (HBox) node;
            TextField tf = (TextField) hBox.getChildren().get(1);

            if (tf != null) {
                playerName = tf.getText();
                if (playerName.isEmpty()) {
                    secondBottomLabel.setText("Please enter all the Names properly");
                } else {
                    playerNames.add(playerName);
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
