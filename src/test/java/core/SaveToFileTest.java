package core;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import exceptions.PlayerNameError;
import org.junit.jupiter.api.Test;
import utilities.Constants;
import utilities.FileHandler;

public class SaveToFileTest {

    private FileHandler fileHandler = new FileHandler();

    @Test
    public void save_13_players_to_list(){
        try {
            Constants.allPlayers.add(new Player(0,1,"Nithin", 100));
            Constants.allPlayers.add(new Player(0,1,"Karan", 40));
            Constants.allPlayers.add(new Player(0,1,"Kartik", 70));
            Constants.allPlayers.add(new Player(0,1,"Chethan", 60));
            Constants.allPlayers.add(new Player(0,1,"Rohith", 10));
            Constants.allPlayers.add(new Player(0,1,"Eshwar", 50));
            Constants.allPlayers.add(new Player(0,1,"Suhas", 20));
            Constants.allPlayers.add(new Player(0,1,"Shashank", 30));
            Constants.allPlayers.add(new Player(0,1,"Simon", 80));
            Constants.allPlayers.add(new Player(0,1,"Travis", 90));
            Constants.allPlayers.add(new Player(0,1,"Aditya", 10));
            Constants.allPlayers.add(new Player(0,1,"Kiran", 20));
            Constants.allPlayers.add(new Player(0,1,"Naveen", 30));
            fileHandler.writeWinnerScoreToFile(new Player());
            assertEquals(fileHandler.readTopScoreFile().getPlayerList().size(),10);

        } catch (PlayerNameError e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
