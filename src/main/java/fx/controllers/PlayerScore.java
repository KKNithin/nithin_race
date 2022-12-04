package fx.controllers;

/**
 * @author Nithin
 * Class used to store the name and score of players
 */
public class PlayerScore implements Comparable<PlayerScore>{
    private String name;
    private Integer score;

    /**
     * Default constructor
     */
    public PlayerScore(){
    }

    /**
     * @param name Player name
     * @param score Player score
     */
    public PlayerScore(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    /**
     * @return Name of player
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Player name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Score of player
     */
    public Integer getScore() {
        return score;
    }

    /**
     * @param score Player score to set
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * @param ps the object to be compared.
     * @return Integer value
     * <p>
     *      The value 0 if both objects are equal; a value greater than 0 if first player score is lesser than the second
     *      player; and a value greater than 0 if  first player score is greater than second player
     * </p>
     *
     */
    @Override
    public int compareTo(PlayerScore ps) {
        return (Integer.compare(this.getScore(), ps.getScore()));
    }
}
