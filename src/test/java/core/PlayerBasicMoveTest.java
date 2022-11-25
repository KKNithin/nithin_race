package core;

import java.io.IOException;

import static utilities.Constants.Directions.BACKWARD;
import static utilities.Constants.Directions.FORWARD;
import exceptions.BoundaryCaseException;
import fx.NithinRace;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import utilities.Constants;

public class PlayerBasicMoveTest extends ApplicationTest {

    Player player1, player2;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fx/game-view.fxml"));
        Parent root = fxmlLoader.load();
        stage.setScene(root.getScene());
        stage.show();
        stage.toFront();
        StartGame.gvc = fxmlLoader.getController();
    }

    @Before
    public void setUp() throws Exception {
        Constants.rows = 8;
        Constants.columns = 8;
        StartGame.b = new Board(8, 8);
        Constants.playerNames.add("Nithin");
        NithinRace.initialise();
        StartGame.gvc.setUpGameGridForTest();
        player1 = new Player(7, 4, "Nithin", 50, "black");
        StartGame.b.addPlayerToPosition(player1.getPositionX(), player1.getPositionY(), player1);
        player2 = new Player(3, 5, "Chethan", 50, "black");
        StartGame.b.addPlayerToPosition(player2.getPositionX(), player2.getPositionY(), player2);
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
    }

    @Test
    public void movePlayer_Forward_1_Test() {
        try {
            StartGame.validateMoveRoll(player1, 1, FORWARD);
            Assert.assertEquals(6, player1.getPositionX());
            Assert.assertEquals(4, player1.getPositionY());
        } catch (BoundaryCaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void movePlayer_Forward_2_Test() {
        try {
            StartGame.validateMoveRoll(player1, 2, FORWARD);
            Assert.assertEquals(5, player1.getPositionX());
            Assert.assertEquals(4, player1.getPositionY());
        } catch (BoundaryCaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void movePlayer_Forward_3_Test() {
        try {
            StartGame.validateMoveRoll(player1, 3, FORWARD);
            Assert.assertEquals(4, player1.getPositionX());
            Assert.assertEquals(4, player1.getPositionY());
        } catch (BoundaryCaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void movePlayer_Forward_4_Test() {
        try {
            StartGame.validateMoveRoll(player1, 4, FORWARD);
            Assert.assertEquals(3, player1.getPositionX());
            Assert.assertEquals(4, player1.getPositionY());
        } catch (BoundaryCaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void movePlayer_Backward_1_Test() {
        try {
            StartGame.validateMoveRoll(player2, 1, BACKWARD);
            Assert.assertEquals(4, player2.getPositionX());
            Assert.assertEquals(5, player2.getPositionY());
        } catch (BoundaryCaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void movePlayer_Backward_2_Test() {
        try {
            StartGame.validateMoveRoll(player2, 2, BACKWARD);
            Assert.assertEquals(5, player2.getPositionX());
            Assert.assertEquals(5, player2.getPositionY());
        } catch (BoundaryCaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void movePlayer_Backward_3_Test() {
        try {
            StartGame.validateMoveRoll(player2, 3, BACKWARD);
            Assert.assertEquals(6, player2.getPositionX());
            Assert.assertEquals(5, player2.getPositionY());
        } catch (BoundaryCaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void movePlayer_Backward_4_Test() {
        try {
            StartGame.validateMoveRoll(player2, 4, BACKWARD);
            Assert.assertEquals(7, player2.getPositionX());
            Assert.assertEquals(5, player2.getPositionY());
        } catch (BoundaryCaseException e) {
            throw new RuntimeException(e);
        }
    }
}
