package fx;

public class PlayerScore implements Comparable<PlayerScore>{
    private String name;
    private Integer score;

    public PlayerScore(){
    }

    public PlayerScore(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public int compareTo(PlayerScore ps) {
        return (Integer.compare(this.getScore(), ps.getScore()));
    }
}
