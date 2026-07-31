import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The instruction on how to play the game, the user can click the button to
 * get back to the main menu.
 * 
 * @author (Liangyi Jinjing) 
 * @version (1/28/2021)
 */
public class Instruction extends World
{
    //Declare variables
    private Text title;
    private Text line1;
    private Text line2;
    private Text line3;
    private Text line4;
    private Text line5;
    private Text line6;
    private Text line7;
    private Text line8;
    private Text line9;
    private Button backButton;
    private TextBox textBox;
    /**
     * Constructor for objects of class Instruction.
     * Takes no parameter, creates an instruction menu for this game.
     */
    public Instruction()
    {    
        // Create a new world with 1000x600 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1); 
        // Set the background.
        setBackground(new GreenfootImage("background.png"));
        // Initialize the text box.
        textBox = new TextBox();
        // Add the text box to the world.
        addObject(textBox, 500, 300);
        
        // Initialize all the texts.
        title = new Text("INSTRUCTION", 60, Color.WHITE);
        line1 = new Text("Welcome to our medieval tower defense game", 20, Color.WHITE);
        line2 = new Text("The objective is to prevent the enemies from reaching your base", 20, Color.WHITE);
        line3 = new Text("Use the money that you have to begin with to build towers", 20, Color.WHITE);
        line4 = new Text("To build a tower, click the icon of the tower that you want to build and click again on a empty grid", 20, Color.WHITE);
        line5 = new Text("To upgrade a tower: lick on the upgarde icon and click on the building that you want to upgrade", 20, Color.WHITE);
        line6 = new Text("Note that you can only build 1 tower in every grid and you can only upgrade a tower once", 20, Color.WHITE);
        line7 = new Text("Defeating an enemy will grant you scores and money to build more towers", 20, Color.WHITE);
        line8 = new Text("The spawn rate of the enemies will increase over time", 20, Color.WHITE);
        line9 = new Text("If total of 10 enemies has reached your base, you lose", 20, Color.WHITE);
        // Add all the texts to the world.
        addObject(title, 500, 100);
        addObject(line1, 500, 150);
        addObject(line2, 500, 180);
        addObject(line3, 500, 210);
        addObject(line4, 500, 240);
        addObject(line5, 500, 270);
        addObject(line6, 500, 300);
        addObject(line7, 500, 330);
        addObject(line8, 500, 360);
        addObject(line9, 500, 390);
        // Initialize the back button
        backButton = new Button("Back", 40, Color.WHITE);
        // Add the back button to the world.
        addObject(backButton, 500, 500);
    }
    /**
     * The act method for the instruction menu.
     * Gets called once every act.
     * Detect if the player has clicked on the back button, 
     * if the player did, set the world back to the starting screen.
     */
    public void act(){
        if(backButton.getClicked() == true){
            Greenfoot.setWorld(new StartingScreen());
        }
    }
}
