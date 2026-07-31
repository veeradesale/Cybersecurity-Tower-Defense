import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Simple and useful widget class to create text. 
 * Text, color and size are customizable.
 * 
 * @author (Liangyi Jinjing) 
 * @version (1/28/2021)
 */
public class Text extends Actor
{
    /**
     * The main constructor, create a greenfoot image with the text and the size inputed.
     * 
     * @param title    The text for the title.
     * @param size     The size of the image.
     */
    private GreenfootImage myImage;
    private int transparency;
    private int size;
    private Color myColor;
    /**
     * Main constructor, that sets the text String, size of text, and colour 
     * of text.
     * 
     * @param text    The text to display
     * @param size    The size of the text
     * @param color   The colour othe text
     */
    public Text(String text, int size, Color color){
        this.size = size;
        this.myColor = color;
        myImage = new GreenfootImage(text, size, color, null);
        transparency = 255;
        myImage.setTransparency(transparency);
        setImage(myImage);
    }
    /**
     * Returns the transparency of the image
     * 
     * @return transparency   The transparency of the image
     */
    public int getTransparency(){
        return transparency;
    }
    /**
     * Sets the transparency of the image
     * 
     * @param input  The new transparency of the image
     * 
     */
    public void setTransparency(int input){
        transparency = input;
        myImage.setTransparency(transparency);
        setImage(myImage);
    }
    /**
     * Sets the text of the image
     * 
     * @param text   The new text to display
     */
    public void setText(String text){
        myImage = new GreenfootImage(text, size, myColor, null);
        setImage(myImage);
    }
    /**
     * Decreases the transparency of the image to create a fading effect
     * 
     */
    public void fade(){
        transparency--;
        myImage.setTransparency(transparency);
        setImage(myImage);
    }
}
