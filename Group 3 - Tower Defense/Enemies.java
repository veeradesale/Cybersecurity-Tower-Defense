import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Enemies will walk along a set path and reach the base. They will be attacked
 * by towers. Their goal is to infiltrate the base.
 * 
 * @author (Cassidy Li) 
 * @version (01/28/21)
 */


public class Enemies extends Actor
{
    //declare instance variables
    private int numGridsHor = 20;
    private int numGridsVer = 12;
    private int distance;
    private int xSpeed;
    private int ySpeed;
    protected int speed;
    private Grids grid;
    private GameWorld world;
    protected GreenfootImage image;
    private boolean[][] visited;
    
    protected int currHp;
    protected int maxHp;  
    protected boolean isDead;
    private int width;
    private int height;
    protected LJStatBar statBar;
    protected String name;
    protected int score;
    protected int money;
    /**
     * 
     * Main contructor, sets enemy isDead to false and sets width and height 
     * for StatBar
     */
    public Enemies(){
        //temp image for testing
        //world = (GameWorld)this.getWorld();
        distance = 0;
        isDead = false;
        width = 30;
        height = 5;
        visited = new boolean[numGridsHor][numGridsVer];
    }
    /**
     * Moves the player
     */
    public void move(){
        if(distance >= 50){
            checkPath();
            distance = 0;
        }
        distance += speed;
        if(!isDead){
            this.setLocation(getX() + xSpeed, getY() + ySpeed);
        }
    }
    /**
     * Checks for a path and changes directions accordingly
     */
    public void checkPath(){
        String direction = PathFinding.findPath(this, numGridsHor, numGridsVer);
        if(direction.equals("left")){
            xSpeed = -speed;
            ySpeed = 0;
        }else if(direction.equals("right")){
            xSpeed = speed;
            ySpeed = 0;
        }else if(direction.equals("up")){
            xSpeed = 0;
            ySpeed = -speed;
        }else if(direction.equals("down")){
            xSpeed = 0;
            ySpeed = speed;
        }else if(direction.equals("end")){
            world = (GameWorld)this.getWorld();
            world.addToCounter();
            isDead = true;
            getWorld().removeObject(this);
        }
    }
    /**
     * Sets visited at index true when path has been visited by enemy already
     * 
     * @param indexX   The x index of the path
     * @param indexY   The y index of the path
     */
    public void setVisited(int indexX, int indexY){
        visited[indexX][indexY] = true;
    }
    /**
     * Returns whether path has been visited by enemy already
     * 
     * @param indexX   The x index of the path
     * @param indexY   The y index of the path
     * 
     * @return         Returns true if path has already been visited
     */
    public boolean hasVisited(int indexX, int indexY){
        return visited[indexX][indexY];
    }
    /**
     * Decreases the enemy's HP
     * 
     * @param healthAmount   Amount of HP to decrease
     */
    public void decreaseHealth(int healthAmount)
    {
        currHp -= healthAmount;
        statBar.updateHp(currHp);
    }
    
    /**
     * Removes the Troop from the World if the Troop is dead and displays a cybersecurity fact
     */
    public void removeIfDead()
    {
        //checks if HP is less than or equal to zero to determine if Troop 
        //is dead
        if(!isDead){            
            if (currHp <= 0)
            {
                isDead = true;
                GameWorld gameWorld = (GameWorld) getWorld();             
                gameWorld.getScoreBoard().addScore(score);
                gameWorld.getMoneyBoard().addScore(money);
                
                //New: call the static method in CyberInfo
                String fact = CyberInfo.getAttackFact(name);
    
                //New: display the fact through the public method in GameWorld
                gameWorld.showCyberFact(fact);
                
                getWorld().removeObject(this);
            }
            else
            {
                isDead = false;
            }
        }
    }
    
    /**
     * Adds the StatBar to the world whenever a new Troop is created
     */
    public void addedToWorld(World w)
    {
        statBar = new LJStatBar(width, height, maxHp, false, 0, false, 0, this, name);
        statBar.setOffset(this.getImage().getHeight()/2 + 10);
        
        w.addObject(statBar, getX(), getY() - this.getImage().getHeight());
    
    }
    
    /**
     * Returns the current HP of the troop
     * @Return currHp    the current HP of the troop
     */
    public int getHP() {
        return currHp;
    }
    
}
