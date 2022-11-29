package core;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static utilities.Constants.Directions.FORWARD;
import static utilities.Constants.TrapType.FENCE;
import fx.GameViewController;
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
public class BorderAndObstacleTest {
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
        NithinRace.initialise();
        StartGame.gvc.setUpGameGridForTest();
        player1 = new Player(7, 6, "Nithin", 50, "black");
        StartGame.b.addPlayerToPosition(player1.getPositionX(), player1.getPositionY(), player1);
        Constants.playersInitialPos.add(new Player(7, 6, "Nithin", 50, "black"));
        player2 = new Player(5, 2, "Chethan", 50, "black");
        StartGame.b.addPlayerToPosition(player2.getPositionX(), player2.getPositionY(), player2);
        Constants.playersInitialPos.add(new Player(5, 2, "Chethan", 50, "black"));
        StartGame.b.setTrap(6, 6, FENCE);
    }

    @AfterEach
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
    }

    @Test
    public void obstacle_Boundary_4_Forward_Test() {
        try {
            GameViewController mockGVC = mock(GameViewController.class);
            StartGame.gvc = mockGVC;
            when(StartGame.gvc.playerMoveResponse()).thenReturn("R","U");
            StartGame.validateMoveRoll(player1, 4, FORWARD);
            assertEquals(4, player1.getPositionX());
            assertEquals(7, player1.getPositionY());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void obstacle_Boundary_4_Forward_Left_Test() {
        try {
            GameViewController mockGVC = mock(GameViewController.class);
            StartGame.gvc = mockGVC;
            when(StartGame.gvc.playerMoveResponse()).thenReturn("L");
            StartGame.validateMoveRoll(player1, 4, FORWARD);
            assertEquals(7, player1.getPositionX());
            assertEquals(2, player1.getPositionY());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
