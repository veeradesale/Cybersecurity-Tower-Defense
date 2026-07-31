import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Fires at enemies.
 * 
 * @author Sisi Li 
 * @version 1/28/2021
 */
public class Towers extends Actor
{
    private Enemies target;
    protected int count = 0;
    protected int range;   
    protected int ROF;
    protected int newROF;
    protected int projSize;
    protected int projSpeed;
    protected int damage;
    protected int newDamage;
    protected int cost;
    protected int upgradeCost;
    //Declare animation images
    protected GreenfootImage lv1Image1;
    protected GreenfootImage lv1Image2;
    protected GreenfootImage lv2Image1;
    protected GreenfootImage lv2Image2;

    protected boolean isUpgraded;
    protected boolean fireProjectile;
    /**
     * Fires at closest enemy. Animation included as well.
     */
    protected void targetClosestEnemy(boolean isBall) {       
        List<Enemies> enemies = getObjectsInRange(range, Enemies.class);
        //If list is not empty
        if (enemies.size() > 0) {      
            //Calculates distance of all enemies in range and takes the shortest one
            Enemies temp = enemies.get(0);
            double shortestDistance = Math.sqrt(Math.pow(enemies.get(0).getX() - getX(), 2) - Math.pow(enemies.get(0).getY() - getY(), 2));
            for(Enemies Eachenemies : enemies) {   
                double dist = Math.sqrt(Math.pow(Eachenemies.getX() - getX(), 2) - Math.pow(Eachenemies.getY() - getY(), 2));
                if (dist > shortestDistance){
                    temp = Eachenemies;
                    shortestDistance = dist;
                }             
            }  
            turnTowards (temp.getX(), temp.getY());
            //Fire at the enemy
            if(count > ROF) { //lower number = faster
                Projectile projectile = new Projectile(projSpeed, damage, isBall); 
                if (fireProjectile) {
                    getWorld().addObject(projectile, getX(), getY());
                    projectile.turnTowards(temp.getX(),temp.getY()); 
                }
                count = 0;
                //Animation
                if (!isUpgraded) {
                    if (getImage().equals(lv1Image1)) {  
                        setImage (lv1Image2);  
                        fireProjectile = false;
                    }  
                    else {  
                        setImage (lv1Image1);
                        fireProjectile = true;
                    }
                }
                else {
                    if (getImage().equals(lv2Image1)) {  
                        setImage (lv2Image2);  
                        fireProjectile = false;
                    }  
                    else {  
                        setImage (lv2Image1);  
                        fireProjectile = true;
                    }
                } 
            }            
        }        
    }

    /**
     * Upgrades tower. Changes ROF and damage. 
     */
    public void upgrade() {
        setImage(lv2Image1);
        ROF = newROF;
        damage = newDamage;
        isUpgraded = true; 
    }

    /**
     * Returns cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * Returns cost of upgrading tower
     */
    public int getUpgradeCost() {
        return upgradeCost;
    }

    /**
     * returns boolean of whether or not tower is upgraded. 
     */
    public boolean getIsUpgraded(){
        return isUpgraded;
    }
}
