import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A type of enemy
 * 
 * @author (Cassidy Li) 
 * @version (01/28/21)
 */
public class Cavalry extends Enemies
{
    /**
     * Main constructors, sets image, speed, and Hp of cavalry
     */

    public Cavalry(){
        image = new GreenfootImage("cavalry.png");
        setImage(image);
        speed = 2;
        maxHp = 20;
        currHp = maxHp;
        name = "Calvary";
        score = 20;
        money = 15;
    }
    /**
     * Act - Moves sword man and removes it if it died
     */
    public void act() 
    {
        move();  
        removeIfDead();
    }    
}
