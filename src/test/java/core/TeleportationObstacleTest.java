package core;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static models.utilities.Constants.Directions.BACKWARD;
import static models.utilities.Constants.Directions.FORWARD;
import static models.utilities.Constants.TrapType.TELEPROTATION_TUNNEL;
import static models.utilities.Constants.allPlayers;
import fx.NithinRace;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import models.core.Board;
import models.core.Player;
import models.core.StartGame;
import models.utilities.BoardPrinterCMD;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import models.utilities.Constants;

@ExtendWith(ApplicationExtension.class)
public class TeleportationObstacleTest {

    Player player1, player2;


    @Start
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fx/game-view.fxml"));
        Parent root = fxmlLoader.load();
        stage.setScene(root.getScene());
        stage.show();
        stage.toFront();
        StartGame.gameViewController = fxmlLoader.getController();
    }

    @BeforeEach
    public void setUp() throws Exception {
        Constants.rows = 8;
        Constants.columns = 8;
        StartGame.board = new Board(8, 8);
        Constants.playerNames.add("Nithin");
        NithinRace.initialiseImageMaps();
        StartGame.gameViewController.setUpGameGridForTest();
        player1 = new Player(7, 4, "Nithin", 50, "black");
        StartGame.board.addPlayerToPosition(player1.getPositionX(), player1.getPositionY(), player1);
        Constants.playersInitialPos.add(new Player(7, 4, "Nithin", 50, "black"));
        player2 = new Player(2, 5, "Chethan", 50, "black");
        StartGame.board.addPlayerToPosition(player2.getPositionX(), player2.getPositionY(), player2);
        Constants.playersInitialPos.add(new Player(7, 5, "Chethan", 50, "black"));
        allPlayers = new ArrayList<>();
        allPlayers.add(player1);
        allPlayers.add(player2);
        StartGame.board.setTrap(3, 4, TELEPROTATION_TUNNEL);
        StartGame.board.setTrap(4, 4, TELEPROTATION_TUNNEL);
        StartGame.board.setTrap(5, 4, TELEPROTATION_TUNNEL);
        StartGame.board.setTrap(6, 4, TELEPROTATION_TUNNEL);
        StartGame.board.setTrap(3, 5, TELEPROTATION_TUNNEL);
        StartGame.board.setTrap(4, 5, TELEPROTATION_TUNNEL);
        StartGame.board.setTrap(5, 5, TELEPROTATION_TUNNEL);
        StartGame.board.setTrap(6, 5, TELEPROTATION_TUNNEL);
        System.out.println("Board Before Start of Test case");
        BoardPrinterCMD.printBoard();
    }

    @AfterEach
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        System.out.println("Board After Running Test case");
        BoardPrinterCMD.printBoard();
    }

    @Test
    public void telePortationObstacle_Forward_4_Test() {
        try {
            StartGame.validateMoveRoll(player1, 4, FORWARD);
            assertEquals(2, player1.getPositionX());
            assertEquals(5, player1.getPositionY());
            assertEquals(7, player2.getPositionX());
            assertEquals(4, player2.getPositionY());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void telePortationObstacle_Forward_3_Test() {
        try {
            StartGame.validateMoveRoll(player1, 3, FORWARD);
            assertEquals(2, player1.getPositionX());
            assertEquals(5, player1.getPositionY());
            assertEquals(7, player2.getPositionX());
            assertEquals(4, player2.getPositionY());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void telePortationObstacle_Forward_2_Test() {
        try {
            StartGame.validateMoveRoll(player1, 2, FORWARD);
            assertEquals(2, player1.getPositionX());
            assertEquals(5, player1.getPositionY());
            assertEquals(7, player2.getPositionX());
            assertEquals(4, player2.getPositionY());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void telePortationObstacle_Forward_1_Test() {
        try {
            StartGame.validateMoveRoll(player1, 1, FORWARD);
            assertEquals(2, player1.getPositionX());
            assertEquals(5, player1.getPositionY());
            assertEquals(7, player2.getPositionX());
            assertEquals(4, player2.getPositionY());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void telePortationObstacle_Backward_4_Test() {
        try {
            StartGame.validateMoveRoll(player2, 4, BACKWARD);
            assertEquals(2, player1.getPositionX());
            assertEquals(5, player1.getPositionY());
            assertEquals(7, player2.getPositionX());
            assertEquals(4, player2.getPositionY());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void telePortationObstacle_Backward_3_Test() {
        try {
            StartGame.validateMoveRoll(player2, 3, BACKWARD);
            assertEquals(2, player1.getPositionX());
            assertEquals(5, player1.getPositionY());
            assertEquals(7, player2.getPositionX());
            assertEquals(4, player2.getPositionY());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void telePortationObstacle_Backward_2_Test() {
        try {
            StartGame.validateMoveRoll(player2, 2, BACKWARD);
            assertEquals(2, player1.getPositionX());
            assertEquals(5, player1.getPositionY());
            assertEquals(7, player2.getPositionX());
            assertEquals(4, player2.getPositionY());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void telePortationObstacle_Backward_1_Test() {
        try {
            StartGame.validateMoveRoll(player2, 1, BACKWARD);
            assertEquals(2, player1.getPositionX());
            assertEquals(5, player1.getPositionY());
            assertEquals(7, player2.getPositionX());
            assertEquals(4, player2.getPositionY());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
