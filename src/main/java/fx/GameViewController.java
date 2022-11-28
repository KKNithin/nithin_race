package fx;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static utilities.Constants.*;
import core.Player;
import core.StartGame;
import core.TopScores;
import exceptions.BoundaryCaseException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import utilities.*;

public class GameViewController {

    @FXML
    private Label playerName;

    @FXML
    private Label moveDice;

    @FXML
    private Label directionDice;
    @FXML
    private Label previousPlayerName;

    @FXML
    private Button rollButton;

    @FXML
    private AnchorPane mainGameAnchor;
    @FXML
    private SplitPane mainGameSplitPane;

    @FXML
    private GridPane gameGrid;

    @FXML
    private SplitPane sideSplitPane;

    @FXML
    private Button leftButton;

    @FXML
    private Button stayButton;

    @FXML
    private Button rightButton;

    @FXML
    private VBox scoreVbox;
    @FXML
    private Label comment;

    private int currentPlayer = 0;
    private Integer previousMoveVal = 0;
    private Directions previousDirectionVal = Directions.MISS_A_TURN;
    private String buttonResponse = null;

    private final Object waitKey = new Object();

    private FileHandler fileHandler = new FileHandler();

    public void setGameViewScreen() {
        mainGameAnchor.setPrefWidth(screenWidth);
        mainGameAnchor.setPrefHeight(screenHeight);
        mainGameSplitPane.setPrefWidth(screenWidth - 10);
        mainGameSplitPane.setPrefHeight(screenHeight - 50);
        gameGrid.prefHeight(screenHeight - 100);
        gameGrid.prefWidth(screenWidth * 0.75);
        sideSplitPane.prefHeight(screenHeight - 100);
        sideSplitPane.prefWidth(screenWidth * 0.25);
        leftButton.setDisable(true);
        rightButton.setDisable(true);
        stayButton.setDisable(true);
    }

    public void initializeGrid() {
        reqTileWidth = (screenWidth * 0.75) / Constants.columns;
        reqTileHeight = (screenHeight - 100) / Constants.rows;
        for (int i = 0; i < Constants.rows; i++) {
            for (int j = 0; j < Constants.columns; j++) {

                Rectangle rectangle = new Rectangle();
                rectangle.setHeight(reqTileHeight);
                rectangle.setWidth(reqTileWidth);
                if (i == 0) {
                    rectangle.setFill(Color.GREEN);
                } else if (i == Constants.rows - 1) {
                    rectangle.setFill(Color.RED.desaturate());
                } else {
                    rectangle.setFill(Color.WHITE);
                }
                StackPane sp = new StackPane(rectangle);
                sp.setId(String.valueOf(i) + String.valueOf(j));
                sp.setPrefHeight(reqTileHeight);
                sp.setPrefWidth(reqTileWidth);
                gameGrid.add(sp, j, i);
            }
        }
        initializeScore();
        StartGame.main(this);
        playerName.setText(allPlayers.get(currentPlayer).getName());
    }

    public void modifyGridTile(Constants.GridOperationtype type, int row, int column, Player player,
                               TrapType obstacle, String com) {
        StackPane stackPane = (StackPane) gameGrid.lookup("#" + String.valueOf(row) + String.valueOf(column));
        if (stackPane.getChildren().size() == 2) {
            stackPane.getChildren().remove(1);
        }
        switch (type) {
            case ADD_PLAYER:
                if (null != player) {
                    updateScore(player);
                }
                modifyImageOnGridTile(stackPane, "src/main/resources/images/man-" + player.getColor() + ".png");
                break;
            case ADD_OBSTACLE:
                if (null != comment) {
                    comment.setText(com);
                }
                modifyImageOnGridTile(stackPane, obstacleImages.get(obstacle));
                break;
            case ADD_TEMP_PLAYER:
                modifyImageOnGridTile(stackPane, "src/main/resources/images/running.png");
                break;
            case PLAYER_IN_TAR:
                modifyImageOnGridTile(stackPane, "src/main/resources/images/man-in-pit.png");
                break;
            case CLEAR:
                break;
        }
    }

    public void modifyGridTile(Constants.GridOperationtype type, int row, int column, Player player,
                               TrapType obstacle) {
        modifyGridTile(type, row, column, player,
                obstacle, null);
    }

    public void modifyImageOnGridTile(StackPane stackPane, String filePath) {
        File imageFile = new File(filePath);
        ImageView imageView = new ImageView(imageFile.toURI().toString());
        imageView.setFitWidth(reqTileWidth);
        imageView.setFitHeight(reqTileHeight);
        imageView.setPreserveRatio(true);
        imageView.setPickOnBounds(true);
        stackPane.getChildren().add(imageView);
    }

    public void updateScore(Player player) {
        HBox hBox = (HBox) scoreVbox.lookup("#" + player.getName());
        if(null!=hBox){
            Label label = (Label) hBox.lookup("#" + player.getName() + "score");
            label.setText(String.valueOf(player.getScore()));
        }
    }
    public void initializeScore() {
        for (String player : playerNames) {
            Label name = new Label(player);
            name.setStyle("-fx-font-size: 18;-fx-padding: 0 0 0 30");
            name.setPrefWidth(150);
            Label score = new Label();
            score.setStyle("-fx-font-size: 18;-fx-padding: 0 0 0 30");
            score.setId(player+"score");
            score.setPrefWidth(75);
            HBox hbox = new HBox(name, score);
            hbox.setId(player);
            scoreVbox.getChildren().add(hbox);
        }
    }
    public void rollDiceForPlayer() throws BoundaryCaseException, IOException, ClassNotFoundException {
        rollButton.setDisable(true);
        comment.setText("");
        Player p = allPlayers.get(currentPlayer);
        System.out.println("Rolling dice for: " + p.getName());
        if (StartGame.isEligibleToRoll(p)) {
            Integer moveVal = MoveDice.rollDice();
            System.out.println("Move Dice Roll Value: " + moveVal);
            moveDice.setText(moveVal.toString());
            Directions directionVal = DirectionDice.rollDice();
            System.out.println("Direction Dice Roll Value: " + directionVal);
            directionDice.setText(directionVal.toString());
            if (directionVal == Directions.MISS_A_TURN) {
                System.out.println("###You missed your turn###");
            }
            if (StartGame.validateMoveRoll(p, moveVal, directionVal)) {
                System.out.println(p);
                System.out.println();
                BoardPrinterCMD.printBoard();
                System.out.println("=============================");
            }
        }
        if (p.getPositionX() == 0) {
            fileHandler.writeWinnerScoreToFile(p);
            TopScores topScores = fileHandler.readTopScoreFile();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("winner-top-scores-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) rollButton.getScene().getWindow();
            WinnerTopScoresViewController winnerTopScoresViewController = fxmlLoader.getController();
            winnerTopScoresViewController.initialize(topScores, p);
            stage.setX(screenWidth/4);
            stage.setY(0);
            stage.setTitle("Nithin's-Race");
            stage.setScene(scene);
            stage.show();
        }
        previousPlayerName.setText(p.getName());
        currentPlayer++;
        currentPlayer = currentPlayer >= allPlayers.size() ? 0 : currentPlayer;
        playerName.setText(allPlayers.get(currentPlayer).getName());
        rollButton.setDisable(false);
    }

    public void enableButtonsForMovement(String str) {
        String[] strArray = str.split("/");
        for (String s : strArray) {
            if (s.equalsIgnoreCase("S")) {
                stayButton.setDisable(false);
            }
            if (s.equalsIgnoreCase("L")) {
                leftButton.setText("Left");
                leftButton.setDisable(false);
            }
            if (s.equalsIgnoreCase("R")) {
                rightButton.setText("Right");
                rightButton.setDisable(false);
            }
            if (s.equalsIgnoreCase("U")) {
                rightButton.setText("Up");
                rightButton.setDisable(false);
            }
            if (s.equalsIgnoreCase("D")) {
                leftButton.setText("Down");
                leftButton.setDisable(false);
            }
        }
    }

    public String playerMoveResponse() {
        String buttonResponse = (String) Platform.enterNestedEventLoop(waitKey);
        String res = String.valueOf(buttonResponse.toCharArray()[0]);
        leftButton.setDisable(true);
        rightButton.setDisable(true);
        stayButton.setDisable(true);
        return res;
    }

    public void rightButtonResponse() {
        buttonResponse = rightButton.getText();
        Platform.exitNestedEventLoop(waitKey, buttonResponse);
    }

    public void leftButtonResponse() {
        buttonResponse = leftButton.getText();
        Platform.exitNestedEventLoop(waitKey, buttonResponse);
    }

    public void stayButtonResponse() {
        buttonResponse = stayButton.getText();
        Platform.exitNestedEventLoop(waitKey, buttonResponse);
    }

    public String showTeleportationDialog(List<String> players) {

        String playerChoosen = players.get(0);
        if (players.size() != 1) {
            ChoiceDialog<String> dialog = new ChoiceDialog<>(players.get(0), players);
            dialog.setTitle("Teleportation Dialog");
            dialog.setHeaderText("You are in a Teleportation Tunnel");
            dialog.setContentText("Please choose a player you want to teleport to:");

            // Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                System.out.println("Your choice: " + result.get());
                playerChoosen = result.get();
                return playerChoosen;
            }
        }
        return playerChoosen;
    }

    public void setUpGameGridForTest() {
        for (int i = 0; i < Constants.rows; i++) {
            for (int j = 0; j < Constants.columns; j++) {

                Rectangle rectangle = new Rectangle();
                rectangle.setHeight(reqTileHeight);
                rectangle.setWidth(reqTileWidth);
                if (i == 0) {
                    rectangle.setFill(Color.GREEN);
                } else if (i == Constants.rows - 1) {
                    rectangle.setFill(Color.RED.desaturate());
                } else {
                    rectangle.setFill(Color.WHITE);
                }
                StackPane sp = new StackPane(rectangle);
                sp.setId(String.valueOf(i) + String.valueOf(j));
                sp.setPrefHeight(reqTileHeight);
                sp.setPrefWidth(reqTileWidth);
                gameGrid.add(sp, j, i);
            }
        }
    }

}
