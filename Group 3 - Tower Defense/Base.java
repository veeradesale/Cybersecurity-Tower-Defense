import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The enemies' final destination. Player will lose if too many enemies enter
 * the base
 * 
 * @author Connie Lin
 * @version (1/28/2021)
 */
public class Base extends Actor
{
    //declare instance variables
    private GreenfootImage base;
    /**
     * Main constructir that sets the image of the Base
     */
    public Base()
    {
        base = new GreenfootImage("wall.png");
        setImage(base);
    }
    /**
     * Act - Method that remove counter, stop background music and store the highest score obtained
     * by players
     */
    public void act() 
    {
        GameWorld world = (GameWorld)getWorld();
        if(world.getCounter() == 10) {
            //Saves high score to greenfoot server
            if (UserInfo.isStorageAvailable()) {
                UserInfo myInfo = UserInfo.getMyInfo();
                myInfo.setScore(world.getScoreBoard().getScore());               
                myInfo.store();  // write back to server               
            }
            Greenfoot.setWorld(new GameOver());
            world.getBGM().stop();
            getWorld().removeObject(this);
        }
    } 
}