import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A phishing attack enemy.
 * 
 * @author Kelly
 * @version 07/30/26
 */
public class CrossbowMan extends Enemies
{
    /**
     * Sets the image, speed, health, name, score, and money reward.
     */
    public CrossbowMan()
    {
        image = new GreenfootImage("phishing.png");
        setImage(image);

        speed = 3;
        maxHp = 50;
        currHp = maxHp;

        name = "Phishing";
        score = 20;
        money = 10;
    }

    /**
     * Moves the phishing attack and removes it when defeated.
     */
    public void act()
    {
        move();
        removeIfDead();
    }
}