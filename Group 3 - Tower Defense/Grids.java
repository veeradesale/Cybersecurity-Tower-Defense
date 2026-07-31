import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A class for all the grids in the game, each grid will have a x and y variable that indicates their location.
 * They will also have a isPath and isEmpty to indicate if they are empty and if they are paths. 
 * If the player's mouse is over a grass grid (the grid that the player can build on), the grass grid will
 * be set to half transpant to indicate that it has been selected.
 * 
 * @author (Liangyi Jinjing) 
 * @version (1/28/2021)
 */
public class Grids extends Actor
{
    // Declare variables
    private int x;
    private int y;
    private boolean isPath;
    private boolean isEmpty;
    private boolean mouseOver;
    private GameWorld world;
    private MouseInfo m;
    private GreenfootImage grassImage;
    private GreenfootImage pathImage;
    /**
     * The main constructor for Grids, takes in a x index and a y index as parameter
     * (their index in the 2d array that contains them), uses the x index and the y index to
     * calculate the x and y coordinate of its center.
     * 
     * @param    xIndex    The grid's x index in the 2d array.
     * @param    yIndex    The grid's y index in the 2d array.
     */
    public Grids(int xIndex, int yIndex){
        //Credits: Robotao
        grassImage = new GreenfootImage("grass_tile.png");
        pathImage = new GreenfootImage("path.png");
        pathImage.scale(50, 50);
        isEmpty = true;
        setImage(grassImage);
        x = xIndex * 50 + 25;
        y = yIndex * 50 + 25;
    }
    /**
     * The addedToWorld method, called when the object is added to the world. Get the world that it is in.
     */
    public void addedToWorld(World w){
        world = (GameWorld)w;
    }
    /**
     * Get the mouseInfo from the world that it is in, if the mouse is over the grid, set transparency to 180.
     */
    public void act(){
        m = world.getMouseInfo();
        if (m != null){
            if (!mouseOver && m.getX() > (x - 25) && m.getX() < (x + 25) && m.getY() > (y - 25) && m.getY() < (y + 25)){
                mouseOver = true;
                grassImage.setTransparency(180);
            }
            if (mouseOver){
                if(m.getX() < (x - 25) || m.getX() > (x + 25) || m.getY() > (y + 25) || m.getY() < (y - 25)){
                    mouseOver = false;
                    grassImage.setTransparency(255);
                }
            }
        }
    }
    /**
     * Determines if path is available for new enemies to spawn
     */
    public boolean isPathAvailable(){
        return this.isTouching(Enemies.class);
    }
    /**
     * Set the grid to a path.
     */
    public void setPath(){
        isPath = true;
        isEmpty = false;
        setImage(pathImage);
    }
    /**
     * Set the grid to empty or not empty depending on the input.
     * 
     * @param input   Set to true if grid is empty
     */
    public void setEmpty(boolean input){
        isEmpty = input;
    }
    /**
     * Getter method that tells the user if the grid is empty.
     * 
     * @return isEmpty   The boolean variable that indicates if the grid is empty.
     */
    public boolean getEmpty(){
        return isEmpty;
    }
    /**
     * Getter method that tells the user if the grid is a path.
     * 
     * @return isPath    The boolean variable that indicates if the grid is a path.
     */
    public boolean getPath(){
        return isPath;
    }
    /**
     * Getter method that tells the user the x coordinate of the grid's center.
     * @return x        the int variable that indicates the x coordinate of the grid's center.
     */
    public int getx(){
        return x;
    }
    /**
     * Getter method that tells the user the y coordinate of the grid's center.
     * @return y        the int variable that indicates the y coordinate of the grid's center.
     */
    public int gety(){
        return y;
    }
}
