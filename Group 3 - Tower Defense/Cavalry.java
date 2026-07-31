import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A malware enemy.
 * 
 * @author Kelly
 * @version 07/30/26
 */
public class Cavalry extends Enemies
{
    /**
     * Main constructor, sets image, speed, and HP of malware.
     */
    public Cavalry()
    {
        image = new GreenfootImage("malware.png");
        setImage(image);

        speed = 2;
        maxHp = 100;
        currHp = maxHp;

        name = "Malware";
        score = 30;
        money = 15;
    }

    /**
     * Moves the malware and removes it if it dies.
     */
    public void act()
    {
        move();
        removeIfDead();
    }
}