package fx.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static models.utilities.Constants.*;
import models.core.Player;
import models.core.StartGame;
import models.core.TopScores;
import models.exceptions.BoundaryCaseException;
import fx.NithinRace;
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
import models.utilities.*;

/**
 * @author Nithin
 * Controller for the main game Java FX screen
 * <p>
 *     It has three views,
 *     First is the game board with gird of rows and columns
 *     Second is the player control view, with roll dice and direction buttons and player score view.
 *     Third is the score board with scores of all the players in the current game.
 * </p>
 */
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

    /**
     * Sets size of the child anchor panes and grid pane based on the screen size
     */
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

    /**
     * Initialises the game grid pane with initial players and obstacles.
     */
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
                sp.setId(String.valueOf(i) + "-" + String.valueOf(j));
                sp.setPrefHeight(reqTileHeight);
                sp.setPrefWidth(reqTileWidth);
                gameGrid.add(sp, j, i);
            }
        }
        initializeScore();
        StartGame.main(this);
        playerName.setText(allPlayers.get(currentPlayer).getName());
    }

    /**
     * Grid tile modification method
     * @param type Type of grid operation to be performed.
     * @param row Row position of player/obstacle
     * @param column Column position of player/obstacle
     * @param player Player object to be placed/removed
     * @param obstacle Obstacle type to be placed/removed
     * @param com Comment to be displayed
     * <p>
     *            This method shall removes any player/obstacle placed on the initial grid tile.
     *            Based on the given operation type it shall add/remove the required player/obstacle.
     * </p>
     */
    public void modifyGridTile(Constants.GridOperationtype type, int row, int column, Player player,
                               TrapType obstacle, String com) {
        StackPane stackPane = (StackPane) gameGrid.lookup("#" + String.valueOf(row) + "-" + String.valueOf(column));

        if (stackPane.getChildren().size() == 2) {
            System.out.println("Removed index" + row + " " + column);
            stackPane.getChildren().remove(1);
        }
        switch (type) {
            case ADD_PLAYER:
                if (null != player) {
                    updateScore(player);
                }
                System.out.println(stackPane.toString());
                System.out.println(player);
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

    /**
     * Helper method to modify grid tile
     * @param type Type of grid operation to be performed.
     * @param row Row position of player/obstacle
     * @param column Column position of player/obstacle
     * @param player Player object to be placed/removed
     * @param obstacle Obstacle type to be placed/removed
     * <p>
     *     This is a support method without comment parameter
     * </p>
     */
    public void modifyGridTile(Constants.GridOperationtype type, int row, int column, Player player,
                               TrapType obstacle) {
        modifyGridTile(type, row, column, player,
                obstacle, null);
    }

    /**
     * method to modify an image on grid tile
     * @param stackPane Stack pane object of the tile
     * @param filePath Image file path
     * <p>
     *      This is a helper method which shall fit the image to the tile size.
     * </p>
     */
    public void modifyImageOnGridTile(StackPane stackPane, String filePath) {
        File imageFile = new File(filePath);
        ImageView imageView = new ImageView(imageFile.toURI().toString());
        imageView.setFitWidth(reqTileWidth);
        imageView.setFitHeight(reqTileHeight);
        imageView.setPreserveRatio(true);
        imageView.setPickOnBounds(true);
        stackPane.getChildren().add(imageView);
    }

    /**
     * updates score
     * @param player Player object
     * <p>Updates the score of the given player on screen</p>
     */
    public void updateScore(Player player) {
        HBox hBox = (HBox) scoreVbox.lookup("#" + player.getName());
        if(null!=hBox){
            Label label = (Label) hBox.lookup("#" + player.getName() + "score");
            label.setText(String.valueOf(player.getScore()));
        }
    }

    /**
     * Initialises the score board for all the players of current game.
     */
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

    /**
     * This method is called when the roll dice button is clicked.
     * @throws BoundaryCaseException Boundary exception
     * @throws IOException If the fxml is not valid.
     * <p>
     *     This method shall roll the dice for a player and invoke the backend to move the player.
     *     It shall first check the eligibility of player to make a move.
     *     Once the game is done, it shall invoke the file handler to save the players to top score list.
     *     It shall switch the screen context to the winner screen view.
     * </p>
     */
    public void rollDiceForPlayer() throws BoundaryCaseException, IOException {
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
            fileHandler.writeWinnerScoreToFile();
            TopScores topScores = fileHandler.readTopScoreFile();
            FXMLLoader fxmlLoader = new FXMLLoader(NithinRace.class.getResource("winner-top-scores-view.fxml"));
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

    /**
     * Enables buttons for player to choose a direction
     * @param str String to decide the buttons to be enabled
     * <p>
     *      Helper method to enable eligible of three buttons based on the string provided.
     * </p>
     */
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

    /**
     * Provides players direction choice to the backend
     * @return String button text
     * <p>
     *    It enters a nested loop, waits of the players input.
     *    Once the input is received it disables all the movement buttons and returns the button text to caller.
     * </p>
     */
    public String playerMoveResponse() {
        String buttonResponse = (String) Platform.enterNestedEventLoop(waitKey);
        String res = String.valueOf(buttonResponse.toCharArray()[0]);
        leftButton.setDisable(true);
        rightButton.setDisable(true);
        stayButton.setDisable(true);
        return res;
    }

    /**
     * Exits the nested loop once the right button is pressed by the user.
     */
    public void rightButtonResponse() {
        buttonResponse = rightButton.getText();
        Platform.exitNestedEventLoop(waitKey, buttonResponse);
    }

    /**
     * Exits the nested loop once the left button is pressed by the user.
     */
    public void leftButtonResponse() {
        buttonResponse = leftButton.getText();
        Platform.exitNestedEventLoop(waitKey, buttonResponse);
    }

    /**
     * Exits the nested loop once the middle/stay button is pressed by the user.
     */
    public void stayButtonResponse() {
        buttonResponse = stayButton.getText();
        Platform.exitNestedEventLoop(waitKey, buttonResponse);
    }

    /**
     * Displays a teleportation choice window
     * @param players List of players to be displayed
     * @return Name of selected player
     * <p>
     *     Method shall display a choice dialog if the number of players are greater than 1.
     * </p>
     */
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


    /**
     * Support method used to initialise the game grid for Junit tests.
     */
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
