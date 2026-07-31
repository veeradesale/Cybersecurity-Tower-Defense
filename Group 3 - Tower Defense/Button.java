import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A simple and useful widget class that was made for this
 * game. Each buttons will be displayed as a text with cutomizable text, size
 * and color. When the user has his mouse ober the button, it would change it's
 * color. 
 * 
 * Widget class inspired by Jordan Cohen's mouse over button example, 
 * credits goes to him.
 * 
 * @author (Liangyi Jinjing) 
 * @version (1/28/2021)
 */
public class Button extends Actor
{
    //Declare instance variables
    private boolean mouseOver;
    private boolean clicked;
    private String text;
    private int size;
    private World world;
    /**
     * The main constructor for the Botton. Creates a button that displays the text that the    programmer inputs,
     * size of the button is customizable.
     * 
     * @param text    The text that the button displays.
     * @param size    The size of the button.
     */
    public Button(String text, int size, Color color){
        this.text = text;
        this.size = size;
        this.world = world;
        setImage(new GreenfootImage(text, size, color, null));
    }
    /**
     * The button will constantly get the user's mouse info, 
     * if the user's mouse is over the button, the button will change its color.
     * if the user clicked on the button, it'll set clicked to true so the world that the button belongs to
     * can get the clicked status through the getClicked() method and do what ever the button is supposed to do.
     */
    public void act() 
    {
            if (!mouseOver && Greenfoot.mouseMoved(this)){
                mouseOver = true;
            }
            if (mouseOver && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)){
                mouseOver = false;
            }
            if (Greenfoot.mouseClicked(this)){
                clicked = true;                
            }
            if (clicked){
                clicked = true;
            } else if (mouseOver){
                setImage(new GreenfootImage(text, size, Color.BLACK, null));
            } else {
                setImage(new GreenfootImage(text, size, Color.WHITE, null));
            }
        
    }    
    /**
     * Method to return the clicked variable. 
     */
    public boolean getClicked(){
        return clicked;
    }
}
