package models.core;

import java.io.Serializable;
import java.util.Objects;


/**
 * @author Nithin
 * Player contains name, color, row position, column position
 */
public class Player implements Comparable<Player>, Serializable {
    /**
     * Name of the player
     */
    private String name;
    /**
     * Color of the player on UI
     */
    private String color;
    /**
     * Row position of the player.
     */
    private int positionX;
    /**
     * Column position of the player.
     */
    private int positionY;
    /**
     * Score of the player
     */
    private Score score;

    /**
     * Player constructor
     * @param x Row position of player
     * @param y Column position of player
     * @param name player name
     * @param initialPoints initial score of player
     * Player Constructor with above parameters
     */
    public Player(int x, int y,String name, int initialPoints) {
        this.name = name;
        this.positionX = x;
        this.positionY = y;
        this.score = new Score(initialPoints);
    }

    /**
     * Player Constructor
     * @param x Row position of player
     * @param y Column position of player
     * @param name Player name
     * @param initialPoints Initial score of player
     * @param color Player color in UI
     * Player Constructor with above parameters.
     */
    public Player(int x, int y,String name, int initialPoints, String color) {
        this.name = name;
        this.positionX = x;
        this.positionY = y;
        this.score = new Score(initialPoints);
        this.color = color;
    }

    /**
     * Player Constructor
     * @param name player name
     * @param score player score
     * Player Constructor with above parameters
     */
    public Player(String name, int score) {
        this.name = name;
        this.score = new Score(score);
    }

    /**
     * Default player constructor
     */
    public Player() {
    }

    /**
     * Gets row position of player
     * @return Row position of player
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * Gets column position of player
     * @return Column position of player
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * Gets name of player
     * @return Name of player
     */
    public String getName() {
        return name;
    }

    /**
     * Gets color of player
     * @return Color of player
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets horizontal position of player
     * @param positionX Row position
     * updates the horizontal position of player
     */
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    /**
     * Sets vertical position of player
     * @param positionY Column position
     * updates the vertical position of player
     */
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    /**
     * Sets position of player on board
     * @param positionX Row position
     * @param positionY Column position
     * updates both row and column of the player
     */
    public void setPosition(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /**
     * Gives the score of player
     * @return Score of player
     */
    public int getScore() {
        return this.score.getScore();
    }

    /**
     * Sets the score of player
     * @param points Number of points updated to player
     */
    public void setScore(int points) {
        if(this.score != null){
            this.score.setScore(points);
        }else {
            this.score = new Score(points);
        }

    }

    /**
     * Modifies the existing player
     * @param p Player object
     * shall replace the player entirely
     */
    public void replace(Player p) {
        this.name = p.getName();
        this.setScore(p.getScore());
        this.positionX = p.getPositionX();
        this.positionY = p.getPositionY();
        this.color = p.getColor();
    }

    /**
     * Updates score of the player
     * @param points Number of points will be updated to the existing player score
     */
    public void updateScore(int points) {
        this.score.updateScoreBy(points);
    }

    /**
     * Compares one player to other.
     * @param o Player object to be compared
     * @return Boolean True if the player names are same else returns false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }

    /**
     * Gives the hashcode
     * @return Shall give the hash of the player object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }

    /**
     * Gives string representation of the player
     * @return The player object in string format
     */
    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                ", score=" + score +
                '}';
    }

    /**
     * Compares one player to other
     * @param p Player object to be compared.
     * @return Boolean True if the score of passing object is greater else return false
     * This method is used by the
     */
    @Override
    public int compareTo(Player p) {
        return (Integer.compare(p.getScore(), this.getScore()));
    }
}
