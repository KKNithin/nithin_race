package core;

import java.io.Serializable;

/**
 * @author Nithin
 * Used to store number of points of each player
 */
public class Score implements Serializable {
    /**
     * Number of points
     */
    private int score;

    /**
     * @param points
     * constructor used to create the score object
     */
    public Score(int points) {
        this.score = points;
    }

    /**
     * @return number of points
     */
    public int getScore() {
        return score;
    }

    /**
     * @param number of points
     */
    public void setScore(int points) {
        this.score = points;
    }

    /**
     * @param points
     * updated the existing score with the points passed
     */
    public void updateScoreBy(int points) {
        this.score += points;
    }
}
