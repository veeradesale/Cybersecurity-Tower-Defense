import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The instruction screen for the Cybersecurity Tower Defense game.
 * 
 * @author Kelly
 * @version 07/30/26
 */
public class Instruction extends World
{
    // Declare variables
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
     */
    public Instruction()
    {
        super(1000, 600, 1);

        setBackground(new GreenfootImage("background.png"));

        textBox = new TextBox();
        addObject(textBox, 500, 300);

        title = new Text("INSTRUCTIONS", 60, Color.WHITE);
        line1 = new Text("Welcome to Cybersecurity Tower Defense!", 20, Color.WHITE);
        line2 = new Text("Stop cyber threats from reaching your network.", 20, Color.WHITE);
        line3 = new Text("Use your money to build cybersecurity defenses.", 20, Color.WHITE);
        line4 = new Text("Click a defense, then click an empty grid to place it.", 20, Color.WHITE);
        line5 = new Text("Click the upgrade icon, then click a defense to upgrade it.", 20, Color.WHITE);
        line6 = new Text("Each grid holds one defense, and each defense upgrades once.", 20, Color.WHITE);
        line7 = new Text("Defeat threats to earn money and score.", 20, Color.WHITE);
        line8 = new Text("Cyber attacks become more frequent over time.", 20, Color.WHITE);
        line9 = new Text("If 10 threats reach your network, you lose.", 20, Color.WHITE);

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

        backButton = new Button("Back", 40, Color.WHITE);
        addObject(backButton, 500, 500);
    }

    public void act()
    {
        if(backButton.getClicked())
        {
            Greenfoot.setWorld(new StartingScreen());
        }
    }
}