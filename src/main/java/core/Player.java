package core;

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
     * @param x
     * @param y
     * @param name
     * @param initialPoints
     * Player Constructor with above parameters
     */
    public Player(int x, int y,String name, int initialPoints) {
        this.name = name;
        this.positionX = x;
        this.positionY = y;
        this.score = new Score(initialPoints);
    }

    /**
     * @param x
     * @param y
     * @param name
     * @param initialPoints
     * @param color
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
     * @param name
     * @param score
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
     * @return Row position of player
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * @return Column position of player
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * @return name of player
     */
    public String getName() {
        return name;
    }

    /**
     * @return color of player
     */
    public String getColor() {
        return color;
    }

    /**
     * @param positionX
     * updates the horizontal position of player
     */
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    /**
     * @param positionY
     * updates the vertical position of player
     */
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    /**
     * @param positionX
     * @param positionY
     * updates both row and column of the player
     */
    public void setPosition(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /**
     * @return score of player
     */
    public int getScore() {
        return this.score.getScore();
    }

    /**
     * @param points are updated to player
     */
    public void setScore(int points) {
        if(this.score != null){
            this.score.setScore(points);
        }else {
            this.score = new Score(points);
        }

    }

    /**
     * @param player
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
     * @param points will be updated to the existing player score
     */
    public void updateScore(int points) {
        this.score.updateScoreBy(points);
    }

    /**
     * @param player
     * @return true if the player names are same else returns false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }

    /**
     * @return shall give the hash of the player object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }

    /**
     * @return the player object in string format
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
     * @param p the object to be compared.
     * @return true if the score of passing object is greater else return false
     * This method is used by the
     */
    @Override
    public int compareTo(Player p) {
        return (Integer.compare(p.getScore(), this.getScore()));
    }
}
