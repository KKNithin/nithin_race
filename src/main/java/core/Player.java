package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import exceptions.PlayerNameError;

public class Player implements Comparable<Player>, Serializable {
    private String name;
    private String color;
    private int positionX;
    private int positionY;
    private List<String> names = new ArrayList<>();

    private Score score;

    public Player(String name) throws PlayerNameError {
        if(names.contains(name)){
            throw new PlayerNameError("Player Already Exists");
        }
        this.name = name;
    }

    public Player(int x, int y,String name, int initialPoints) throws PlayerNameError {
        if(names.contains(name)){
            throw new PlayerNameError("Player Already Exists");
        }
        this.name = name;
        this.positionX = x;
        this.positionY = y;
        this.score = new Score(initialPoints);
    }

    public Player(int x, int y,String name, int initialPoints, String color) throws PlayerNameError {
        if(names.contains(name)){
            throw new PlayerNameError("Player Already Exists");
        }
        this.name = name;
        this.positionX = x;
        this.positionY = y;
        this.score = new Score(initialPoints);
        this.color = color;
    }
    public Player(String name, int score) {
        this.name = name;
        this.score = new Score(score);
    }

    public Player() {
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public void setPosition(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getScore() {
        return this.score.getScore();
    }

    public void setScore(int points) {
        if(this.score != null){
            this.score.setScore(points);
        }else {
            this.score = new Score(points);
        }

    }
    public void replace(Player p) {
        this.name = p.getName();
        this.setScore(p.getScore());
        this.positionX = p.getPositionX();
        this.positionY = p.getPositionY();
    }

    public void updateScore(int points) {
        this.score.updateScoreBy(points);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name) && Objects.equals(color, player.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", posX=" + positionX +
                ", posY=" + positionY +
                ", Score=" + score.getScore() +
                '}';
    }

    @Override
    public int compareTo(Player p) {
        return (Integer.compare(p.getScore(), this.getScore()));
    }
}
