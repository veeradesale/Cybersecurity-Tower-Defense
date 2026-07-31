import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A type of enemy
 * 
 * @author (Cassidy Li) 
 * @version (01/28/21)
 */
public class CrossbowMan extends Enemies
{
    /**
     * Main constructors, sets image, speed, and Hp of crossbow man
     */
    public CrossbowMan(){
        image = new GreenfootImage("crossbow_knight_01.png");
        setImage(image);
        speed = 1;
        maxHp = 10;
        currHp = maxHp;
        name = "Crossbow";
        score = 15;
        money = 10;
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
