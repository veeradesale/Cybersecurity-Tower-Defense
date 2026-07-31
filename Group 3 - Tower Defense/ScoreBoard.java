import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Displays the score and money of the player
 * 
 * @author (Sisi Li) 
 * @version (01/28/21)
 */
public class ScoreBoard extends Actor {
    // Declare Objects
    private GreenfootImage board;
    private String text;

    // Declare Variables:
    private int score;
    /**
     * Construct a ScoreBar of the appropriate size.
     * 
     * @param width     width of the World where the
     *                  ScoreBar will be placed
     */
    public ScoreBoard (String name) {
        text = name;
        setImage(new GreenfootImage(text + score, 25, Color. WHITE, Color.BLACK));
    } 
    /**
     * Act - updates image of scoreboard display
     */
    public void act() {
        setImage(new GreenfootImage(text + score, 25, Color. WHITE, Color.BLACK));
    }
    /**
     * Adds points to score
     * 
     * @param points  Point to add to score
     */
    public void addScore(int points) {
        score += points;
    }
    /**
     * Returns score
     * @return score   Score to return
     */
    public int getScore() {
        return score;
    }
}
