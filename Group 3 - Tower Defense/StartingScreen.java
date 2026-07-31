import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartingScreen here.
 * 
 * @author (Liangyi Jinjing) 
 * @version (a version number or a date)
 */
public class StartingScreen extends World
{
    
    //declares instance variables
    private Button startButton;
    private Button instruction;
    private Text medieval;
    private Text towerDefense;
    /**
     * Main constructor, sets the layout of the screen
     */
    public StartingScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1); 
        setBackground(new GreenfootImage("background.png"));
        startButton = new Button("START", 40, Color.WHITE);
        instruction = new Button("INSTRUCTION", 40, Color.WHITE);
        medieval = new Text("MEDIEVAL", 60, Color.WHITE);
        towerDefense = new Text("TOWER DEFENSE", 60, Color.WHITE);
        addObject(medieval, 770, 65);
        addObject(towerDefense, 770, 120);
        addObject(startButton, 770, 190);
        addObject(instruction, 770, 230);
    }
    /**
     * Checks if buttons have been click and will respond accordingly
     */
    public void act(){
        
        if(startButton.getClicked() == true){
            Greenfoot.setWorld(new GameWorld());
        }
        if(instruction.getClicked() == true){
            Greenfoot.setWorld(new Instruction());
        }
    }
}