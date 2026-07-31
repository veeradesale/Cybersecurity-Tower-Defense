import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Either a ball or arrow is spawned. Deals enemies damage.
 * 
 * @author Sisi Li and Cassidy Li
 * @version 1/28/2021
 */
public class Projectile extends Actor
{
    //Initialize variables
    private int speed;
    private int damage;
    /**
     * Contructor for Projectile.
     * 
     * @param int speed           Speed of projectile
     * @param int damage          Amount of damage
     * @param boolean isBall      True to spawn ball, false to spawn arrow
     */
    public Projectile(int speed, int damage, boolean isBall) { 
        this.speed = speed;
        this.damage = damage;
        if (isBall) 
            setImage(new GreenfootImage("cannon_ball.png"));
        else
            setImage(new GreenfootImage("arrow.png"));
    }
    /**
     * Moves. Hits enemy and disappear. Also disappears at world edge.
     */
    public void act() {
        move(speed);
        checkEdges();   
        if (getWorld() != null) { 
            checkHitEnemy(); 
        }
    } 
    //Checks if projectile hits an enemy. If so, deal damage.
    private void checkHitEnemy() { 
        Enemies enemy = (Enemies) getOneIntersectingObject(Enemies.class);
        if(enemy != null ){ 
            enemy.decreaseHealth(damage); 
            getWorld().removeObject(this);
        }        
    }    
    //Checks if it hits the world edge. If so, disappear.
    private void checkEdges() { 
        if(this.getWorld() != null){
            if (getX() <= getImage().getWidth() || getX() >= getWorld().getWidth()){
                getWorld().removeObject(this);
            }  
        }
    }
}
