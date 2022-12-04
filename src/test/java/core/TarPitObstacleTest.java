package core;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static models.utilities.Constants.Directions.BACKWARD;
import static models.utilities.Constants.Directions.FORWARD;
import static models.utilities.Constants.TrapType.TAR_PIT;
import fx.NithinRace;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import models.core.Board;
import models.core.Player;
import models.core.StartGame;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import models.utilities.Constants;

@ExtendWith(ApplicationExtension.class)
public class TarPitObstacleTest {

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
        Constants.playerNames = new ArrayList<>();
        Constants.playerNames.add("Nithin");
        NithinRace.initialiseImageMaps();
        StartGame.gameViewController.setUpGameGridForTest();
        player1 = new Player(7, 4, "Nithin", 50, "black");
        StartGame.board.addPlayerToPosition(player1.getPositionX(), player1.getPositionY(), player1);
        Constants.playersInitialPos = new ArrayList<>();
        Constants.playersInitialPos.add(new Player(7, 4, "Nithin", 50, "black"));
        player2 = new Player(2, 5, "Chethan", 50, "black");
        StartGame.board.addPlayerToPosition(player2.getPositionX(), player2.getPositionY(), player2);
        Constants.playersInitialPos.add(new Player(2, 5, "Chethan", 50, "black"));
        StartGame.board.setTrap(3, 4, TAR_PIT);
        StartGame.board.setTrap(4, 4, TAR_PIT);
        StartGame.board.setTrap(5, 4, TAR_PIT);
        StartGame.board.setTrap(6, 4, TAR_PIT);
        StartGame.board.setTrap(3, 5, TAR_PIT);
        StartGame.board.setTrap(4, 5, TAR_PIT);
        StartGame.board.setTrap(5, 5, TAR_PIT);
        StartGame.board.setTrap(6, 5, TAR_PIT);
    }

    @AfterEach
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
    }

    @Test
    public void tarPitObstacle_Forward_4_Test() {
        try {
            StartGame.validateMoveRoll(player1, 4, FORWARD);
            StartGame.isEligibleToRoll(player1);
            if (StartGame.isEligibleToRoll(player1)) {
                StartGame.validateMoveRoll(player1, 1, FORWARD);
            }
            assertEquals(2, player1.getPositionX());
            assertEquals(4, player1.getPositionY());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void tarPitObstacle_Forward_3_Test() {
        try {
            StartGame.validateMoveRoll(player1, 3, FORWARD);
            StartGame.isEligibleToRoll(player1);
            if (StartGame.isEligibleToRoll(player1)) {
                StartGame.validateMoveRoll(player1, 2, FORWARD);
            }
            assertEquals(2, player1.getPositionX());
            assertEquals(4, player1.getPositionY());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void tarPitObstacle_Forward_2_Test() {
        try {
            StartGame.validateMoveRoll(player1, 2, FORWARD);
            StartGame.isEligibleToRoll(player1);
            if (StartGame.isEligibleToRoll(player1)) {
                StartGame.validateMoveRoll(player1, 3, FORWARD);
            }
            assertEquals(2, player1.getPositionX());
            assertEquals(4, player1.getPositionY());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void tarPitObstacle_Forward_1_Test() {
        try {
            StartGame.validateMoveRoll(player1, 1, FORWARD);
            StartGame.isEligibleToRoll(player1);
            if (StartGame.isEligibleToRoll(player1)) {
                StartGame.validateMoveRoll(player1, 4, FORWARD);
            }
            assertEquals(2, player1.getPositionX());
            assertEquals(4, player1.getPositionY());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void tarPitObstacle_Backward_4_Test() {
        try {
            StartGame.validateMoveRoll(player2, 4, BACKWARD);
            StartGame.isEligibleToRoll(player2);
            if (StartGame.isEligibleToRoll(player2)) {
                StartGame.validateMoveRoll(player2, 1, BACKWARD);
            }
            assertEquals(7, player2.getPositionX());
            assertEquals(5, player2.getPositionY());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void tarPitObstacle_Backward_3_Test() {
        try {
            StartGame.validateMoveRoll(player2, 3, BACKWARD);
            StartGame.isEligibleToRoll(player2);
            if (StartGame.isEligibleToRoll(player2)) {
                StartGame.validateMoveRoll(player2, 2, BACKWARD);
            }
            assertEquals(7, player2.getPositionX());
            assertEquals(5, player2.getPositionY());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void tarPitObstacle_Backward_2_Test() {
        try {
            StartGame.validateMoveRoll(player2, 2, BACKWARD);
            StartGame.isEligibleToRoll(player2);
            if (StartGame.isEligibleToRoll(player2)) {
                StartGame.validateMoveRoll(player2, 3, BACKWARD);
            }
            assertEquals(7, player2.getPositionX());
            assertEquals(5, player2.getPositionY());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void tarPitObstacle_Backward_1_Test() {
        try {
            StartGame.validateMoveRoll(player2, 1, BACKWARD);
            StartGame.isEligibleToRoll(player2);
            if (StartGame.isEligibleToRoll(player2)) {
                StartGame.validateMoveRoll(player2, 4, BACKWARD);
            }
            assertEquals(7, player2.getPositionX());
            assertEquals(5, player2.getPositionY());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
