package core;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static models.utilities.Constants.topScoresFile;
import models.core.Player;
import org.junit.jupiter.api.Test;
import models.utilities.Constants;
import models.utilities.FileHandler;

public class SaveToFileTest {

    private FileHandler fileHandler = new FileHandler();

    @Test
    public void save_13_players_to_list() {
        try {
            File f = new File(topScoresFile);
            f.delete();
        } catch (NullPointerException e) {
            System.out.println("Top scores file doesn't exist");
        }

        Constants.allPlayers.add(new Player(0, 1, "Shashank", 30));
        Constants.allPlayers.add(new Player(0, 1, "Simon", 80));
        Constants.allPlayers.add(new Player(0, 1, "Travis", 90));
        Constants.allPlayers.add(new Player(0, 1, "Aditya", 10));
        Constants.allPlayers.add(new Player(0, 1, "Kiran", 20));
        Constants.allPlayers.add(new Player(0, 1, "Naveen", 30));
        Constants.allPlayers.add(new Player(0, 1, "Nithin", 100));
        Constants.allPlayers.add(new Player(0, 1, "Karan", 40));
        Constants.allPlayers.add(new Player(0, 1, "Kartik", 70));
        Constants.allPlayers.add(new Player(0, 1, "Chethan", 60));
        Constants.allPlayers.add(new Player(0, 1, "Rohith", 10));
        Constants.allPlayers.add(new Player(0, 1, "Eshwar", 50));
        Constants.allPlayers.add(new Player(0, 1, "Suhas", 20));
        fileHandler.writeWinnerScoreToFile();
        assertEquals(fileHandler.readTopScoreFile().getPlayerList().size(), 10);
        assertEquals(fileHandler.readTopScoreFile().getPlayerList().get(0).getScore(), 100);
    }

}
