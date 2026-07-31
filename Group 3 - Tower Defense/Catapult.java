import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Firewall tower.
 * Blocks cyber threats with powerful protection.
 * 
 * @author Kelly
 * @version 07/30/26
 */
public class Catapult extends Towers
{
    /**
     * Constructor for Firewall. Initializes inherited variables.
     */
    public Catapult()
    {
        // Initialize animation frames
        // Replace these image names later if you get firewall images.
        lv1Image1 = new GreenfootImage("firewall.png");
        lv1Image2 = new GreenfootImage("firewall.png");
        lv2Image1 = new GreenfootImage("firewall.png");
        lv2Image2 = new GreenfootImage("firewall.png");
        setImage(lv1Image1);

        // Initialize tower stats
        range = 160;
        ROF = 20;
        newROF = 32;
        projSpeed = 7;
        damage = 30;
        newDamage = 40;
        cost = 150;
        upgradeCost = 50;
    }

    /**
     * Fires at enemies.
     */
    public void act()
    {
        count++;
        targetClosestEnemy(true);
    }
}