import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Starting screen for the Cybersecurity Tower Defense game.
 * 
 * @author Kelly
 * @version 07/30/26
 */
public class StartingScreen extends World
{
    // Declares instance variables
    private Button startButton;
    private Button instruction;
    private Text cybersecurity;
    private Text towerDefense;

    /**
     * Main constructor, sets the layout of the screen
     */
    public StartingScreen()
    {
        super(1000, 600, 1);

        setBackground(new GreenfootImage("background.png"));

        startButton = new Button("START", 40, Color.WHITE);
        instruction = new Button("INSTRUCTIONS", 40, Color.WHITE);

        cybersecurity = new Text("CYBERSECURITY", 60, Color.WHITE);
        towerDefense = new Text("TOWER DEFENSE", 60, Color.WHITE);

        addObject(cybersecurity, 770, 65);
        addObject(towerDefense, 770, 120);
        addObject(startButton, 770, 190);
        addObject(instruction, 770, 230);
    }

    /**
     * Checks if buttons have been clicked
     */
    public void act()
    {
        if(startButton.getClicked())
        {
            Greenfoot.setWorld(new GameWorld());
        }

        if(instruction.getClicked())
        {
            Greenfoot.setWorld(new Instruction());
        }
    }
}