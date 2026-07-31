import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * This Medieval Tower Defense Game is a game that consists of two opposing forces: the 
 * invading force, which is the Enemies, and the defending force, which is the players themselves.
 * The objective of this game is to prevent a set amount of Enemies, in this case,ten, from
 * invading the Base. The player will begin the game with a starting currency of 200 dollars
 * and can buy Towers to defend and prevent the Enemies from invading the Base. The
 * currency and score will increase depending on the type on Enemies killed during the game, 
 * which allows the player to buy more Towers to strengthen their defencing force. The game 
 * will continue running until the player loses the game, and the player with the highest 
 * score will be recoreded.
 * Note: All images without credit comments are by Liangyi.
 * 
 * @author (Liangyi Jinjing, Sisi L, Cassidy Li, Connie Lin) 
 * @version (1/28/2021)
 */
public class GameWorld extends World {
    //declare instance variables
    private boolean constructing;
    private boolean selected;
    private MouseInfo m;
    private static int score;
    private int money;
    private Towers[][] towers;
    private int randomize;
    private int spawnRate;
    private ArrayList enemies = new ArrayList();
    private Grids startGrid;
    private ScoreBoard scoreboard = new ScoreBoard("Score: "); 
    private ScoreBoard moneyboard = new ScoreBoard("Money: "); 
    private BuildMenu menu;
    private Icon indicator;
    private Icon crossbowIcon;
    private Icon catapultIcon;
    private Icon cannonIcon;
    private Icon upgradeIcon;
    private GreenfootImage tempImage;
    private int counter = 0;
    private EnemyCounter enemyCount;
    private boolean changeSpawnRate;
    private boolean warning;
    private Text crossbowCost;
    private Text crossbowUpgradeCost;
    private Text cannonCost;
    private Text cannonUpgradeCost;
    private Text catapultCost;
    private Text catapultUpgradeCost;
    private Text upgradeCost;
    //main 2D array used to store grid
    private Grids[][] grids;
    //Credits: Music by Alexander Nakarada
    private GreenfootSound bgm = new GreenfootSound("bloodEagle.mp3");
    private Text warningText;
    
    //Added variable to access cybersecurity facts. Private so it can only be accessed in GameWorld
    private Text cyberFactText;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public GameWorld() {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1, false); 
        bgm.setVolume(30);
        //setBackground();
        spawnRate = 500;
        grids = new Grids [20] [12];
        towers = new Towers [20] [12];
        
        
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 12; j++){
                grids[i][j] = new Grids(i, j);
                addObject(grids[i][j], grids[i][j].getx(), grids[i][j].gety());
            }
        }
        for(int i = 0; i < 12; i++){
            if(i != 6)
            addObject(new Base(), grids[19][i].getx(), grids[19][i].gety());
        }
        startGrid = new Grids(-1, 5);
        addObject(startGrid, -25, 275);
        addObject(scoreboard, 890, 20);
        //Gives player 500 dollars to start with
        moneyboard.addScore(200);
        addObject(moneyboard, 770, 20);
        makePath();
        // set order
        setActOrder(GameWorld.class);
        setPaintOrder(Icon.class);
        // Initialize and add the build menu
        menu = new BuildMenu();
        addObject(menu, 196, 50);
        // Initialize and add the icons
        crossbowIcon = new Icon(1, 255);
        addObject(crossbowIcon, 50, 40);
        catapultIcon = new Icon(2, 255);
        addObject(catapultIcon, 150, 40);
        cannonIcon = new Icon(3, 255);
        addObject(cannonIcon, 250, 40);
        upgradeIcon = new Icon(4, 255);
        addObject(upgradeIcon, 340, 40);
        // Initialize the indicator
        indicator = new Icon(0, 255);
        addObject(indicator, 0 , 0);
        
        upgradeCost = new Text("Upgrade Costs", 25, Color.BLACK);
        crossbowCost = new Text("Password $100", 14, Color.BLACK);
        crossbowUpgradeCost = new Text("Strong Password: $150", 17, Color.BLACK);
        catapultCost = new Text("Firewall $300", 14, Color.BLACK);
        catapultUpgradeCost = new Text("Firewall: $450", 17, Color.BLACK);
        cannonCost = new Text("Update $200", 14, Color.BLACK);
        cannonUpgradeCost = new Text("Software Update: $300", 17, Color.BLACK);
        
        addObject(upgradeCost, 565, 20);
        addObject(crossbowCost, 45, 85);
        addObject(crossbowUpgradeCost, 565, 45);
        addObject(catapultCost, 145, 85);
        addObject(catapultUpgradeCost, 565, 65);
        addObject(cannonCost, 245, 85);
        addObject(cannonUpgradeCost, 565, 85);
        
        enemyCount = new EnemyCounter(25);
        addObject(enemyCount, getWidth() - enemyCount.getImage().getWidth()/2 - 5, 325);
        // Initialize the warning text
        warningText = new Text("You don't have enough money to do that!", 20, Color.RED);
        warningText.setTransparency(0);
        addObject(warningText, 500, 150);
        
        // New: create educational fact display of the attacks
        cyberFactText = new Text("Defeat an attack to learn about it.", 16, Color.BLACK);
        addObject(cyberFactText, 500, 120);
    }
    /**
     * Act - Mainly for user interface, determines which towers to build and 
     * upgrade based on player's choice
     */
    public void act(){
        //sets spawn rate
        setSpawnRate();
        
        score = scoreboard.getScore();
        bgm.playLoop();
        spawnEnemies();
        m = Greenfoot.getMouseInfo();
        if(m != null){         
            if(selected == false){
                if(Greenfoot.mouseClicked(crossbowIcon)){
                    if(moneyboard.getScore() >= 100){
                        indicator.change(1, 180);   
                        selected = true;
                    }else if(moneyboard.getScore() < 100){
                        warning = true;
                        warningText.setText("You don't have enough money to do that!");
                        warningText.setTransparency(255);
                    }
                }
                else if(Greenfoot.mouseClicked(catapultIcon)){
                    if( moneyboard.getScore() >= 300){
                        indicator.change(2, 180);
                        selected = true;
                    }else if(moneyboard.getScore() < 300){
                        warning = true;
                        warningText.setText("You don't have enough money to do that!");
                        warningText.setTransparency(255);
                    }
                }
                else if(Greenfoot.mouseClicked(cannonIcon)){
                    if(moneyboard.getScore() >= 200){
                        indicator.change(3, 180);
                        selected = true;
                    }else if(moneyboard.getScore() < 200){
                        warning = true;
                        warningText.setText("You don't have enough money to do that!");
                        warningText.setTransparency(255);
                    }
                }
                else if(Greenfoot.mouseClicked(upgradeIcon)){
                    indicator.change(4, 180);
                    selected = true;
                }
            }            
            if(selected == true){
                indicator.setLocation(m.getX(), m.getY());
                if(Greenfoot.mouseClicked(indicator)){
                    if(m.getX() > 400 || m.getY() > 100){
                        int x = m.getX()/50;
                        int y = m.getY()/50;
                        if(grids[x][y].getEmpty() == true){
                            if(indicator.getType() == 1){ 
                                Crossbow tempCrossbow = new Crossbow();
                                addObject(tempCrossbow, grids[x][y].getx(), grids[x][y].gety());
                                towers[x][y] = tempCrossbow;
                                grids[x][y].setEmpty(false);
                                indicator.change(0, 0);
                                selected = false;
                                moneyboard.addScore(-towers[x][y].getCost());
                            }
                            else if(indicator.getType() == 2){
                                Catapult tempCatapult = new Catapult();
                                addObject(tempCatapult, grids[x][y].getx(), grids[x][y].gety());
                                towers[x][y] = tempCatapult;
                                grids[x][y].setEmpty(false);
                                indicator.change(0, 0);
                                selected = false;
                                moneyboard.addScore(-towers[x][y].getCost());
                            }
                            else if(indicator.getType() == 3){
                                Cannon tempCannon = new Cannon();
                                addObject(tempCannon, grids[x][y].getx(), grids[x][y].gety());
                                towers[x][y] = tempCannon;
                                grids[x][y].setEmpty(false);
                                indicator.change(0, 0);
                                selected = false;
                                moneyboard.addScore(-towers[x][y].getCost());
                            }                           
                        }
                        if(grids[x][y].getEmpty() == false){
                            if(indicator.getType() == 4){
                                 
                                if(moneyboard.getScore() >= towers[x][y].getUpgradeCost() && towers[x][y].getIsUpgraded() == false){
                                    towers[x][y].upgrade();
                                    moneyboard.addScore(-towers[x][y].getUpgradeCost());
                                    indicator.change(0, 0); 
                                    selected = false;
                                } else if(moneyboard.getScore() < towers[x][y].getUpgradeCost()){
                                    indicator.change(0, 0); 
                                    selected = false;
                                    warning = true;
                                    warningText.setText("You don't have enough money to do that!");
                                    warningText.setTransparency(255);
                                } else if(towers[x][y].getIsUpgraded() == true){
                                    warning = true;
                                    warningText.setText("That building has already been upgraded!");
                                    warningText.setTransparency(255);
                                    indicator.change(0, 0); 
                                    selected = false;
                                }
                            }
                        }
                    }
                }
            }
            if(warning == true && warningText.getTransparency() > 0){
                warningText.fade();
            } else if(warningText.getTransparency() == 0){
                warning = false;
            }
        }
    }
    /**
     * Get information regarding the current status of the mouse 
     * 
     * @return m   The MouseInfo contains information about the current status of the mouse
     */
    public MouseInfo getMouseInfo(){
        return m;
    }
    /**
     * Make a path in the GameWorld for the Enemies to walk on
     */
    public void makePath(){       
        grids[0][5].setPath();
        grids[1][5].setPath();
        grids[2][5].setPath();
        grids[3][5].setPath();
        grids[3][4].setPath();
        grids[3][3].setPath();
        grids[4][3].setPath();
        grids[5][3].setPath();
        grids[6][3].setPath();
        grids[6][4].setPath();
        grids[6][5].setPath();
        grids[6][6].setPath();
        grids[6][7].setPath();
        grids[7][7].setPath();
        grids[8][7].setPath();
        grids[9][7].setPath();
        grids[10][7].setPath();
        grids[11][7].setPath();
        grids[11][8].setPath();
        grids[11][9].setPath();
        grids[12][9].setPath();
        grids[13][9].setPath();
        grids[14][9].setPath();
        grids[14][8].setPath();
        grids[14][7].setPath();
        grids[14][6].setPath();
        grids[14][5].setPath();
        grids[14][4].setPath();
        grids[14][3].setPath();
        grids[15][3].setPath();
        grids[16][3].setPath();
        grids[17][3].setPath();
        grids[17][3].setPath();
        grids[17][4].setPath();
        grids[17][5].setPath();
        grids[17][6].setPath();
        grids[18][6].setPath();
        grids[19][6].setPath();   
    }
    /**
     * Method that sets the spawn rate of the Enemies base on the player's score 
     */
    private void setSpawnRate(){
        if(changeSpawnRate && scoreboard.getScore() % 100 == 0 && spawnRate > 200){
            spawnRate -= 10;
            changeSpawnRate = false;
        }else if(score % 100 != 0){
            changeSpawnRate = true;
        }
    }
    /**
     * Spwan Enemies in the GameWorld
     */
    private void spawnEnemies()
    {      
        randomize = Greenfoot.getRandomNumber(spawnRate);
        if(!startGrid.isPathAvailable()){
            if(randomize == 1 && score > 500)
            {
                addObject(new Cavalry(), -25, 275);
            }
            if(randomize == 2)
            {
                addObject(new CrossbowMan(), -25, 275);
            }
            if(randomize == 3 && score > 250)
            {
                addObject(new SwordMan(), -25, 275);
            }
        }
    }
    /**
     * Getter method that returns whether if the player is constructing
     * 
     * @return constructing   The boolean variable that indicates if the player is constructing
     */
    public boolean getConstructing(){
        return constructing;
    }
    /**
     * Getter method that returns the money board 
     * 
     * @return moneyboard   The ScoreBoard that indicates 
     */
    public Grids getGrid(int indexX, int indexY){
        return grids[indexX][indexY];
    }
    /**
     * Getter Method that returns the score board 
     * 
     * @return scoreboard   The ScoreBoard that indicates the amount of money player has
     */
    public ScoreBoard getScoreBoard() {
        return scoreboard;
    }
    /**
     * Getter method that returns the money board
     * 
     * @return moneyboard   The ScoreBoard that indicates the amount of money player has
     */
    public ScoreBoard getMoneyBoard() {
        return moneyboard;
    }
    /**
     * Stop playing background music 
     */
    public void stopped() {
        bgm.pause();
    }
    /**
     * Start playing background music in loop
     */
    public void started() {
        bgm.playLoop();
    }
    /**
     * Return counter
     * 
     * @return counter   The int variable that tracks how many Enemies invaded the Base
     */
    public int getCounter() {
        return counter;
    }
    /**
     * Increase counter by one 
     */
    public void addToCounter() {
        counter++;
        enemyCount.addEnemyCount(counter, 25);
    }
    /**
     * Getter method that tells the user the score 
     * 
     * @return score   The int variable that tracks the score of the player
     */
    public static int getScore(){
        return score;
    }
    /**
     * Method that returns the background music 
     * 
     * @return bgm   The background music 
     */
    public GreenfootSound getBGM() {
        return bgm;
    }   
    /**
     * New: displays information about the cyberattack that was defeated
     */
    public void showCyberFact(String fact)
    {
        cyberFactText.setText(fact);
    }
}

