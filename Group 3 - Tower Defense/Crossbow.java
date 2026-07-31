import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Strong Password tower.
 * Blocks phishing attacks with fast protection.
 * 
 * @author Kelly
 * @version 07/30/26
 */
public class Crossbow extends Towers
{
    /**
     * Constructor for Strong Password.
     */
    public Crossbow()
    {
        lv1Image1 = new GreenfootImage("password.png");
        lv1Image2 = new GreenfootImage("password.png");
        lv2Image1 = new GreenfootImage("password.png");
        lv2Image2 = new GreenfootImage("password.png");
        setImage(lv1Image1);

        range = 100;
        ROF = 20;
        newROF = 15;
        projSpeed = 7;
        damage = 10;
        newDamage = 15;
        cost = 50;
        upgradeCost = 20;
    }

    /**
     * Fires at the closest enemy.
     */
    public void act()
    {
        count++;
        targetClosestEnemy(false);
    }
}