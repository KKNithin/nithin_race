package fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.Constants;

import java.io.IOException;

import static utilities.Constants.obstacleImages;

/**
 * @author Nithin
 * Main class for starting game in Java FX
 */
public class NithinRace extends Application {
    /**
     * @param stage Default stage to be initialised by Java FX
     * @throws IOException If the provided fxml is faulty
     */
    @Override
    public void start(Stage stage) throws IOException {
        initialiseImageMaps();
        FXMLLoader fxmlLoader = new FXMLLoader(NithinRace.class.getResource("first-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        FirstViewController fvc = fxmlLoader.getController();
        fvc.init();
        stage.setTitle("Nithin's-Race");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args System arguments
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Initialises the image maps of obstacles and its types respectively.
     */
    public static void initialiseImageMaps() {
        obstacleImages.put(Constants.TrapType.FENCE, "src/main/resources/images/fence.png");
        obstacleImages.put(Constants.TrapType.FIRE, "src/main/resources/images/fire.png");
        obstacleImages.put(Constants.TrapType.TAR_PIT, "src/main/resources/images/tar_pit.png");
        obstacleImages.put(Constants.TrapType.TELEPROTATION_TUNNEL, "src/main/resources/images/teleport.png");
    }
}