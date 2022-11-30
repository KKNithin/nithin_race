package core;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utilities.Constants.Directions.*;
import exceptions.BoundaryCaseException;
import fx.NithinRace;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import utilities.Constants;

@ExtendWith(ApplicationExtension.class)
public class PlayerBasicMoveTest {

    Player player1, player2;

    @Start
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fx/game-view.fxml"));
        Parent root = fxmlLoader.load();
        stage.setScene(root.getScene());
        stage.show();
        stage.toFront();
        StartGame.gvc = fxmlLoader.getController();
    }

    @BeforeEach
    public void setUp() throws Exception {
        Constants.rows = 8;
        Constants.columns = 8;
        StartGame.b = new Board(8, 8);
        Constants.playerNames.add("Nithin");
        NithinRace.initialiseImageMaps();
        StartGame.gvc.setUpGameGridForTest();
        player1 = new Player(7, 4, "Nithin", 50, "black");
        StartGame.b.addPlayerToPosition(player1.getPositionX(), player1.getPositionY(), player1);
        player2 = new Player(3, 5, "Chethan", 50, "black");
        StartGame.b.addPlayerToPosition(player2.getPositionX(), player2.getPositionY(), player2);
    }

    @AfterEach
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
    }

    @Test
    public void movePlayer_Forward_1_Test() {
        try {
            StartGame.validateMoveRoll(player1, 1, FORWARD);
            assertEquals(6, player1.getPositionX());
            assertEquals(4, player1.getPositionY());
        } catch (BoundaryCaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void movePlayer_Forward_2_Test() {
        try {
            StartGame.validateMoveRoll(player1, 2, FORWARD);
            assertEquals(5, player1.getPositionX());
            assertEquals(4, player1.getPositionY());
        } catch (BoundaryCaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void movePlayer_Forward_3_Test() {
        try {
            StartGame.validateMoveRoll(player1, 3, FORWARD);
            assertEquals(4, player1.getPositionX());
            assertEquals(4, player1.getPositionY());
        } catch (BoundaryCaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void movePlayer_Forward_4_Test() {
        try {
            StartGame.validateMoveRoll(player1, 4, FORWARD);
            assertEquals(3, player1.getPositionX());
            assertEquals(4, player1.getPositionY());
        } catch (BoundaryCaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void movePlayer_Backward_1_Test() {
        try {
            StartGame.validateMoveRoll(player2, 1, BACKWARD);
            assertEquals(4, player2.getPositionX());
            assertEquals(5, player2.getPositionY());
        } catch (BoundaryCaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void movePlayer_Backward_2_Test() {
        try {
            StartGame.validateMoveRoll(player2, 2, BACKWARD);
            assertEquals(5, player2.getPositionX());
            assertEquals(5, player2.getPositionY());
        } catch (BoundaryCaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void movePlayer_Backward_3_Test() {
        try {
            StartGame.validateMoveRoll(player2, 3, BACKWARD);
            assertEquals(6, player2.getPositionX());
            assertEquals(5, player2.getPositionY());
        } catch (BoundaryCaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void movePlayer_Forward_4_Boundary_Test() {
        try {
            StartGame.validateMoveRoll(player2, 4, FORWARD);
            assertEquals(0, player2.getPositionX());
            assertEquals(5, player2.getPositionY());
        } catch (BoundaryCaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void movePlayer_Backward_4_Boundary_Test() {
        try {
            StartGame.validateMoveRoll(player1, 4, BACKWARD);
            assertEquals(7, player1.getPositionX());
            assertEquals(4, player1.getPositionY());
        } catch (BoundaryCaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void movePlayer_MissATurn_4_Boundary_Test() {
        try {
            StartGame.validateMoveRoll(player2, 4, MISS_A_TURN);
            assertEquals(3, player2.getPositionX());
            assertEquals(5, player2.getPositionY());
        } catch (BoundaryCaseException e) {
            throw new RuntimeException(e);
        }
    }
}
