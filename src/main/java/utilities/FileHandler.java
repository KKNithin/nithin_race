package utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static utilities.Constants.allPlayers;
import core.Player;
import core.TopScores;

public class FileHandler {

    public TopScores readTopScoreFile() {
        TopScores topScores = new TopScores();
        try {
            File f = new File("top_scores.ser");
            if(f.exists() && !f.isDirectory()) {
                FileInputStream fileIn = new FileInputStream(f);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                Object obj = objectIn.readObject();
                topScores = (TopScores) obj;
                objectIn.close();
            } else {
                f.createNewFile();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Top Scores file Not found");
        }
        return topScores;
    }

    public void writeWinnerScoreToFile(Player p1) throws IOException, ClassNotFoundException {
        List<Player> tempTopScores = new ArrayList<>();
        TopScores topScores = new TopScores();
        topScores.setPlayerList(tempTopScores);

        try {
            File f = new File("top_scores.ser");
            if(f.exists() && !f.isDirectory()) {
                FileInputStream fileIn = new FileInputStream(f);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                Object obj = objectIn.readObject();
                topScores = (TopScores) obj;
                objectIn.close();
            } else {
                f.createNewFile();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Top Scores file Not found");
        }


        tempTopScores = topScores.getPlayerList();

        for(Player p:allPlayers){
            boolean isPlayerAlreadyExists = false;
            for (int i = 0; i < topScores.getPlayerList().size(); i++) {
                if(p.getName().equalsIgnoreCase(tempTopScores.get(i).getName())) {
                    if(p.getScore() > tempTopScores.get(i).getScore()) {
                        tempTopScores.get(i).replace(p);
                    }
                    isPlayerAlreadyExists = true;
                }
            }


            if(tempTopScores.size() == 0 || !isPlayerAlreadyExists) {
                tempTopScores.add(new Player(p.getName(), p.getScore()));
            }
        }

        Collections.sort(tempTopScores);
        if (tempTopScores.size() > 10) {
            tempTopScores = new ArrayList<>(tempTopScores.subList(0, 10));
        }
        topScores.setPlayerList(tempTopScores);

        FileOutputStream fileOut = new FileOutputStream("top_scores.ser");
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(topScores);
        objectOut.close();
    }
}
