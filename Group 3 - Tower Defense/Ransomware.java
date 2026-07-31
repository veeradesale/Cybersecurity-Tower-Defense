import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ransomware - a cyberattack that encrypts files and demands payment.
 * Extends Enemies and uses the inherited protected fields for
 * image, speed, HP, name, score, and money.
 * 
 * @author (Caitlyn) 
 * @version (7/30/26)
 */
public class Ransomware extends Enemies
{
    /**
     * Main constructor - sets the image, speed, HP, name, score, and money reward
     */
    public Ransomware(){
        image = new GreenfootImage("ransomware.png");
        setImage(image);
        speed = 1;
        maxHp = 20;
        currHp = maxHp;
        name = "Ransomware";
        score = 30;
        money = 20;
    }
    /**
     * Act - Moves the Ransomware and removes it if defeated
     */
    public void act() 
    {
        move();
        removeIfDead();
    }    
}
