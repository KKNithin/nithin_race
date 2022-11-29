package utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static utilities.Constants.allPlayers;
import static utilities.Constants.topScoresFile;
import core.Player;
import core.TopScores;

/**
 * @author Nithin
 * File Handler class helps save and read top player scores from file
 */
public class FileHandler {

    /**
     * @return TopScores
     * TopScores object shall have a list of players with scores
     */
    public TopScores readTopScoreFile() {
        TopScores topScores = new TopScores();
        try {
            File f = new File(topScoresFile);
            FileInputStream fileIn = new FileInputStream(f);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Object obj = objectIn.readObject();
            topScores = (TopScores) obj;
            objectIn.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Unable to read from file: " + topScores + " " + e);
        }
        return topScores;
    }

    /**
     * Shall save all the player scores to a flat file
     */
    public void writeWinnerScoreToFile() {
        List<Player> tempTopScores = new ArrayList<>();
        TopScores topScores = new TopScores();
        topScores.setPlayerList(tempTopScores);

        try {
            File f = new File(topScoresFile);
            if (f.exists() && !f.isDirectory()) {
                FileInputStream fileIn = new FileInputStream(f);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                Object obj = objectIn.readObject();
                topScores = (TopScores) obj;
                objectIn.close();
            } else {
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Top Scores file Not found " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Object stored in the Top Scores file is invalid " + e);
        }

        tempTopScores = topScores.getPlayerList();

        for (Player p : allPlayers) {
            boolean isPlayerAlreadyExists = false;
            for (int i = 0; i < topScores.getPlayerList().size(); i++) {
                if (p.getName().equalsIgnoreCase(tempTopScores.get(i).getName())) {
                    if (p.getScore() > tempTopScores.get(i).getScore()) {
                        tempTopScores.get(i).replace(p);
                    }
                    isPlayerAlreadyExists = true;
                }
            }


            if (tempTopScores.size() == 0 || !isPlayerAlreadyExists) {
                tempTopScores.add(new Player(p.getName(), p.getScore()));
            }
        }

        Collections.sort(tempTopScores);
        if (tempTopScores.size() > 10) {
            tempTopScores = new ArrayList<>(tempTopScores.subList(0, 10));
        }
        topScores.setPlayerList(tempTopScores);

        try {
            FileOutputStream fileOut = new FileOutputStream(topScoresFile);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(topScores);
            objectOut.close();
        } catch (FileNotFoundException e) {
            System.out.println("Top Scores file Not found " + e);
        } catch (IOException e) {
            System.out.println("Not able to write to Top scores file " + e);
        }
    }
}
