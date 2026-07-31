import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A class that is simply a image for the build menu, only works for this game.
 * 
 * @author (Liangyi Jinjing) 
 * @version (1/28/2021)
 */
public class BuildMenu extends Actor
{
    // Declare variables
    private GreenfootImage myImage;
    /**
     * The main constructor for the BuildMenu class, takes no parameter, create a image for the build menu.
     */
    public BuildMenu(){
        myImage = new GreenfootImage(393, 100);
        myImage.setColor(Color.BLACK);
        myImage.fill();
        myImage.setColor(Color.WHITE);
        myImage.fillRect(4, 4, 92, 91);
        myImage.fillRect(101, 4, 92, 91);
        myImage.fillRect(198, 4, 92, 91);
        myImage.fillRect(295, 4, 92, 91);
        myImage.setColor(Color.BLACK);
        myImage.fillRect(8, 8, 84, 65);
        myImage.fillRect(105, 8, 84, 65);
        myImage.fillRect(202, 8, 84, 65);
        myImage.fillRect(299, 8, 84, 65);
        setImage(myImage);
    }
}
