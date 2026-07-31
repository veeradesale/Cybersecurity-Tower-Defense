import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Cybersecurity Tower Defense is a game with two opposing forces:
 * cyber threats and the player's cybersecurity defenses.
 *
 * The objective is to prevent 10 cyber threats from reaching the network.
 * The player begins with $200 and can buy defenses to stop the threats.
 *
 * @author Kelly
 * @version 07/30/26
 */
public class GameWorld extends World
{
    // Declare instance variables
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

    // Main 2D array used to store grid
    private Grids[][] grids;

    // Credits: Music by Alexander Nakarada
    private GreenfootSound bgm = new GreenfootSound("bloodEagle.mp3");

    private Text warningText;

    /**
     * Constructor for objects of class GameWorld.
     */
    public GameWorld()
    {
        super(1000, 600, 1, false);

        bgm.setVolume(30);

        spawnRate = 500;

        grids = new Grids[20][12];
        towers = new Towers[20][12];

        for(int i = 0; i < 20; i++)
        {
            for(int j = 0; j < 12; j++)
            {
                grids[i][j] = new Grids(i, j);
                addObject(
                    grids[i][j],
                    grids[i][j].getx(),
                    grids[i][j].gety()
                );
            }
        }

        for(int i = 0; i < 12; i++)
        {
            if(i != 6)
            {
                addObject(
                    new Base(),
                    grids[19][i].getx(),
                    grids[19][i].gety()
                );
            }
        }

        startGrid = new Grids(-1, 5);
        addObject(startGrid, -25, 275);

        addObject(scoreboard, 890, 20);

        // Gives player $200 to start with
        moneyboard.addScore(200);
        addObject(moneyboard, 770, 20);

        makePath();

        // Set order
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
        addObject(indicator, 0, 0);

        // Cybersecurity defense names and costs
        upgradeCost = new Text(
            "Upgrade Costs",
            25,
            Color.BLACK
        );

        crossbowCost = new Text(
            "Password: $50",
            20,
            Color.BLACK
        );

        crossbowUpgradeCost = new Text(
            "Strong Password: $20",
            20,
            Color.BLACK
        );

        catapultCost = new Text(
            "Firewall: $150",
            20,
            Color.BLACK
        );

        catapultUpgradeCost = new Text(
            "Firewall Upgrade: $50",
            20,
            Color.BLACK
        );

        cannonCost = new Text(
            "Update: $100",
            20,
            Color.BLACK
        );

        cannonUpgradeCost = new Text(
            "Software Update: $35",
            20,
            Color.BLACK
        );

        addObject(upgradeCost, 565, 20);
        addObject(crossbowCost, 45, 85);
        addObject(crossbowUpgradeCost, 565, 45);
        addObject(catapultCost, 145, 85);
        addObject(catapultUpgradeCost, 565, 65);
        addObject(cannonCost, 245, 85);
        addObject(cannonUpgradeCost, 565, 85);

        enemyCount = new EnemyCounter(25);

        addObject(
            enemyCount,
            getWidth() - enemyCount.getImage().getWidth() / 2 - 5,
            325
        );

        // Initialize the warning text
        warningText = new Text(
            "You don't have enough money to do that!",
            20,
            Color.RED
        );

        warningText.setTransparency(0);
        addObject(warningText, 500, 150);
    }

    /**
     * Determines which cybersecurity defenses to build or upgrade.
     */
    public void act()
    {
        setSpawnRate();

        score = scoreboard.getScore();

        bgm.playLoop();
        spawnEnemies();

        m = Greenfoot.getMouseInfo();

        if(m != null)
        {
            if(selected == false)
            {
                // Strong Password defense
                if(Greenfoot.mouseClicked(crossbowIcon))
                {
                    if(moneyboard.getScore() >= 50)
                    {
                        indicator.change(1, 180);
                        selected = true;
                    }
                    else if(moneyboard.getScore() < 50)
                    {
                        warning = true;

                        warningText.setText(
                            "You don't have enough money to do that!"
                        );

                        warningText.setTransparency(255);
                    }
                }

                // Firewall defense
                else if(Greenfoot.mouseClicked(catapultIcon))
                {
                    if(moneyboard.getScore() >= 150)
                    {
                        indicator.change(2, 180);
                        selected = true;
                    }
                    else if(moneyboard.getScore() < 150)
                    {
                        warning = true;

                        warningText.setText(
                            "You don't have enough money to do that!"
                        );

                        warningText.setTransparency(255);
                    }
                }

                // Software Update defense
                else if(Greenfoot.mouseClicked(cannonIcon))
                {
                    if(moneyboard.getScore() >= 100)
                    {
                        indicator.change(3, 180);
                        selected = true;
                    }
                    else if(moneyboard.getScore() < 100)
                    {
                        warning = true;

                        warningText.setText(
                            "You don't have enough money to do that!"
                        );

                        warningText.setTransparency(255);
                    }
                }

                else if(Greenfoot.mouseClicked(upgradeIcon))
                {
                    indicator.change(4, 180);
                    selected = true;
                }
            }

            if(selected == true)
            {
                indicator.setLocation(m.getX(), m.getY());

                if(Greenfoot.mouseClicked(indicator))
                {
                    if(m.getX() > 400 || m.getY() > 100)
                    {
                        int x = m.getX() / 50;
                        int y = m.getY() / 50;

                        if(grids[x][y].getEmpty() == true)
                        {
                            // Builds Strong Password
                            if(indicator.getType() == 1)
                            {
                                Crossbow tempCrossbow =
                                    new Crossbow();

                                addObject(
                                    tempCrossbow,
                                    grids[x][y].getx(),
                                    grids[x][y].gety()
                                );

                                towers[x][y] = tempCrossbow;
                                grids[x][y].setEmpty(false);

                                indicator.change(0, 0);
                                selected = false;

                                moneyboard.addScore(
                                    -towers[x][y].getCost()
                                );
                            }

                            // Builds Firewall
                            else if(indicator.getType() == 2)
                            {
                                Catapult tempCatapult =
                                    new Catapult();

                                addObject(
                                    tempCatapult,
                                    grids[x][y].getx(),
                                    grids[x][y].gety()
                                );

                                towers[x][y] = tempCatapult;
                                grids[x][y].setEmpty(false);

                                indicator.change(0, 0);
                                selected = false;

                                moneyboard.addScore(
                                    -towers[x][y].getCost()
                                );
                            }

                            // Builds Software Update
                            else if(indicator.getType() == 3)
                            {
                                Cannon tempCannon =
                                    new Cannon();

                                addObject(
                                    tempCannon,
                                    grids[x][y].getx(),
                                    grids[x][y].gety()
                                );

                                towers[x][y] = tempCannon;
                                grids[x][y].setEmpty(false);

                                indicator.change(0, 0);
                                selected = false;

                                moneyboard.addScore(
                                    -towers[x][y].getCost()
                                );
                            }
                        }

                        if(grids[x][y].getEmpty() == false)
                        {
                            if(indicator.getType() == 4)
                            {
                                if(
                                    moneyboard.getScore()
                                    >= towers[x][y].getUpgradeCost()
                                    &&
                                    towers[x][y].getIsUpgraded() == false
                                )
                                {
                                    towers[x][y].upgrade();

                                    moneyboard.addScore(
                                        -towers[x][y].getUpgradeCost()
                                    );

                                    indicator.change(0, 0);
                                    selected = false;
                                }

                                else if(
                                    moneyboard.getScore()
                                    < towers[x][y].getUpgradeCost()
                                )
                                {
                                    indicator.change(0, 0);
                                    selected = false;
                                    warning = true;

                                    warningText.setText(
                                        "You don't have enough money to do that!"
                                    );

                                    warningText.setTransparency(255);
                                }

                                else if(
                                    towers[x][y].getIsUpgraded() == true
                                )
                                {
                                    warning = true;

                                    warningText.setText(
                                        "That defense has already been upgraded!"
                                    );

                                    warningText.setTransparency(255);

                                    indicator.change(0, 0);
                                    selected = false;
                                }
                            }
                        }
                    }
                }
            }

            if(
                warning == true
                &&
                warningText.getTransparency() > 0
            )
            {
                warningText.fade();
            }
            else if(warningText.getTransparency() == 0)
            {
                warning = false;
            }
        }
    }

    /**
     * Gets the current mouse information.
     *
     * @return Mouse information
     */
    public MouseInfo getMouseInfo()
    {
        return m;
    }

    /**
     * Makes a path for the cyber threats to travel on.
     */
    public void makePath()
    {
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
     * Changes the cyber threat spawn rate based on the score.
     */
    private void setSpawnRate()
    {
        if(
            changeSpawnRate
            &&
            scoreboard.getScore() % 100 == 0
            &&
            spawnRate > 200
        )
        {
            spawnRate -= 10;
            changeSpawnRate = false;
        }
        else if(score % 100 != 0)
        {
            changeSpawnRate = true;
        }
    }

    /**
     * Spawns cyber threats in the GameWorld.
     */
    private void spawnEnemies()
    {
        randomize = Greenfoot.getRandomNumber(spawnRate);

        if(!startGrid.isPathAvailable())
        {
            // Malware
            if(randomize == 1 && score > 500)
            {
                addObject(new Cavalry(), -25, 275);
            }

            // Phishing
            if(randomize == 2)
            {
                addObject(new CrossbowMan(), -25, 275);
            }

            // Other cyber threat
            if(randomize == 3 && score > 250)
            {
                addObject(new SwordMan(), -25, 275);
            }
        }
    }

    /**
     * Returns whether the player is constructing.
     */
    public boolean getConstructing()
    {
        return constructing;
    }

    /**
     * Returns a grid at the specified position.
     */
    public Grids getGrid(int indexX, int indexY)
    {
        return grids[indexX][indexY];
    }

    /**
     * Returns the score board.
     */
    public ScoreBoard getScoreBoard()
    {
        return scoreboard;
    }

    /**
     * Returns the money board.
     */
    public ScoreBoard getMoneyBoard()
    {
        return moneyboard;
    }

    /**
     * Pauses the background music.
     */
    public void stopped()
    {
        bgm.pause();
    }

    /**
     * Starts the background music.
     */
    public void started()
    {
        bgm.playLoop();
    }

    /**
     * Returns the number of threats that reached the network.
     */
    public int getCounter()
    {
        return counter;
    }

    /**
     * Increases the threat counter by one.
     */
    public void addToCounter()
    {
        counter++;
        enemyCount.addEnemyCount(counter, 25);
    }

    /**
     * Returns the player's score.
     */
    public static int getScore()
    {
        return score;
    }

    /**
     * Returns the background music.
     */
    public GreenfootSound getBGM()
    {
        return bgm;
    }
}