import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The ending screen that displays the players score and gives the option to
 * restart or return to the main menu
 * 
 * @author (Cassidy Li) 
 * @version (01/28/21)
 */
public class GameOver extends World
{

    //declare instance variables
    private Button restartButton;
    private Button menuButton;
    private Text gameOver;
    private Text gameScore;
    private int score;
    /**
     * Main constructor, sets the layout of the screen including the buttons
     */
    public GameOver()
    {    
        // Create a new world with 1000x600 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1); 
        //sets background image
        setBackground(new GreenfootImage("background.png"));
        //gets score from game world
        score = GameWorld.getScore();
        
        //initalizes buttons
        restartButton = new Button("RESTART", 40, Color.WHITE);
        menuButton = new Button("MAIN MENU", 40, Color.WHITE);
        gameOver = new Text("GAME OVER", 60, Color.WHITE);
        gameScore = new Text("SCORE: " + score, 30, Color.WHITE);
        //adds buttons to world
        addObject(restartButton, 800, 190);
        addObject(menuButton, 800, 230);
        addObject(gameOver, 800, 65);
        addObject(gameScore, 800, 125);
        
    }
    /**
     * Checks if buttons have been click and will respond accordingly
     */
    public void act(){
        
        if(restartButton.getClicked() == true){
            Greenfoot.setWorld(new GameWorld());
        }else if(menuButton.getClicked() == true){
            Greenfoot.setWorld(new StartingScreen());
        }
        
    }
}
