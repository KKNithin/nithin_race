package fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.Constants;

import java.io.IOException;

import static utilities.Constants.obstacleImages;

public class NithinRace extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        initialise();
        FXMLLoader fxmlLoader = new FXMLLoader(NithinRace.class.getResource("first-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        FirstViewController fvc = fxmlLoader.getController();
        fvc.init();
        stage.setTitle("Nithin's-Race");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void initialise() {
        obstacleImages.put(Constants.TrapType.FENCE, "src/main/resources/images/fence.png");
        obstacleImages.put(Constants.TrapType.FIRE, "src/main/resources/images/fire.png");
        obstacleImages.put(Constants.TrapType.TAR_PIT, "src/main/resources/images/tar_pit.png");
        obstacleImages.put(Constants.TrapType.TELEPROTATION_TUNNEL, "src/main/resources/images/teleport.png");
    }
}