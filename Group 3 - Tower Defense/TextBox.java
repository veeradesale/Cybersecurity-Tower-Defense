import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Rectangle images that will hold text in instruction menu
 * 
 * @author (Liangyi Jinjing) 
 * @version (1/28/2021)
 */
public class TextBox extends Actor
{
    private GreenfootImage myImage;
    /**
     * Main construction for Textbox. Takes no parameters and creates a 
     * rectangular image.
     */
    public TextBox(){
        // Initialize the text box.
        myImage = new GreenfootImage(750, 480);
        myImage.setColor(Color.BLACK);
        myImage.fill();
        myImage.setTransparency(180);
        setImage(myImage);
    }
}
