import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Software Update tower.
 * Protects the computer from cyber attacks.
 * 
 * @author Kelly
 * @version 07/30/26
 */
public class Cannon extends Towers
{
    /**
     * Constructor for Software Update.
     */
    public Cannon()
    {
        // Images
        lv1Image1 = new GreenfootImage("update.png");
        lv1Image2 = new GreenfootImage("update.png");
        lv2Image1 = new GreenfootImage("update.png");
        lv2Image2 = new GreenfootImage("update.png");
        setImage(lv1Image1);

        // Tower stats
        range = 400;
        ROF = 30;
        newROF = 25;
        projSpeed = 5;
        damage = 20;
        newDamage = 30;

        cost = 100;
        upgradeCost = 35;
    }

    /**
     * Fires at the closest enemy.
     */
    public void act()
    {
        count++;
        targetClosestEnemy(true);
    }
}