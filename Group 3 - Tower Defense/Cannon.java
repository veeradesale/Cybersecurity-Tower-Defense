
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Shoots catapult at enemies. High ROF, low damage, and low cost.
 * 
 * @author Sisi Li
 * @version 1/28/2021
 */
public class Cannon extends Towers
{
    /**
     * Act - do whatever the Cannon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Cannon() {    
        lv1Image1 = new GreenfootImage("cannon_lv1.png");
        lv1Image2 = new GreenfootImage("cannon_lv1.png");
        lv2Image1 = new GreenfootImage("cannon_lv2.png");
        lv2Image2 = new GreenfootImage("cannon_lv2.png");
        setImage(lv1Image1);
        range = 400;   
        ROF = 30;
        newROF = 25;
        projSpeed = 5;
        damage = 2;
        newDamage = 5;
        upgradeCost = 300;
        cost = 200;
    }
    public void act() 
    {
        count++;
        targetClosestEnemy(true);
    }    
}
