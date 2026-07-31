import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Counts how many enemies have entered the base and will update display 
 * accordingly
 * 
 * @author (Connie Lin) 
 * @version (01/28/21)
 */
public class EnemyCounter extends Actor
{
    //instance variable of the max number of enemies that can enter base before
    //game ends
    private int max;
    
    /**
     * The main constructor, create a greenfoot image with the text and the size inputed.
     * 
     * @param size     The size of the image.
     */
    public EnemyCounter(int size){
        max = 10;
        setImage(new GreenfootImage(0 + "/" + 10, size, Color.WHITE, null));
    }
    /**
     * Adds to the enemy count
     * 
     * @param counter   The new number of enemies
     * @param size      The size of the image
     */
    public void addEnemyCount(int counter, int size){
        setImage(new GreenfootImage(counter + "/" + max, size, Color.WHITE, null));
    }
}
