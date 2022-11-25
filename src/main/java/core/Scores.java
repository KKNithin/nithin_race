package core;

import java.util.HashMap;
import java.util.Map;

import exceptions.PlayerNotFoundException;

public class Scores {
    public static Map<Player, Integer> allPlayerScores = new HashMap<>();

    public static boolean updateScore(Player p, Integer val) throws PlayerNotFoundException {
        if (allPlayerScores.containsKey(p)) {
            int currentScore = allPlayerScores.get(p);
            allPlayerScores.put(p, currentScore + val);
            return true;
        } else {
            throw new PlayerNotFoundException("Player not found to update score");
        }
    }
}
