import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Shoots cannonball at enemies. Medium ROF, damage, and cost.
 * 
 * @author Sisi Li
 * @version 1/28/2021
 */
public class Catapult extends Towers
{
     /**
     * Constructor for cannon. Initializes inherited variables. 
     */
    public Catapult() {    
        //Initialize animation frames
        lv1Image1 = new GreenfootImage("catapult_lv1_01.png");
        lv1Image2 = new GreenfootImage("catapult_lv1_02.png");
        lv2Image1 = new GreenfootImage("catapult_lv2_01.png");
        lv2Image1 = new GreenfootImage("catapult_lv2_02.png");
        setImage(lv1Image1);
        //Initialize firing variables
        range = 500;   
        ROF = 20; 
        newROF = 32;
        projSpeed = 7;
        damage = 3;
        newDamage = 5;
        cost = 300;
        upgradeCost = 450;
    }
    /**
     * Fires at enemy
     */
    public void act() 
    {
        count++;
        targetClosestEnemy(true);
    }    
}
