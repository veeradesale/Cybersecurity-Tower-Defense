import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Widget class inspired by Jordan Cohen's HealthBar class in his OOPLessonInBugs. 
 * The method of getting the actor that the stat bar belongs to 
 * and the method of redrawing the bar are from his original code. 
 * Even though changes and improvement were made to it, 
 * the base code and the idea are still his and thus credits goes to him.
 * <p>
 * A useful widget class, creates an image that represents the status of an actor when the constructor is called, 
 * can be customized in many ways depending on which constructor is called.
 * 
 * @author Liangyi Jinjing 
 * @version 12/10/2020
 */
public class LJStatBar extends Actor
{
    // Declare instance variables
    private int maxHp;
    private int currHp;
    private int hpBarSize;
    private int maxMana;
    private int currMana;
    private int manaBarSize;
    private int maxStamina;
    private int manaBarY;
    private int currStamina;
    private int staminaBarSize;
    private int level;
    private int barWidth;
    private int barHeight;
    private int barY1 = 1;
    private int barY2 = barHeight + 2;
    private int barY3 = barHeight * 2 + 3;
    private int barY4 = barHeight * 3 + 4;
    private double percentageHp;
    private double percentageStamina;
    private double percentageMana;
    private boolean stamina;
    private boolean mana;
    private boolean numDisplay;
    private String name;
    private Font myFont;
    
    // Declare instance images
    private GreenfootImage bar;
    
    // Declare constance variables
    private int offset = 75;
    
    // Declare the colors
    private Color hpColor = new Color (232, 44, 37);
    private Color manaColor = new Color (57, 41, 240);
    private Color staminaColor = new Color (122, 214, 86);
    private Color barColor = new Color (207, 207, 207);
    private Color outlineColor = new Color (255, 255, 255);
    private Color fontColor = new Color (0, 0, 0);
    private Color white;
    // Declare the Actor that this stat bar belongs to.
    private Actor target;
    
    //The overloaded constructors.
    /** 
     * The main constructor, takes no input and creates a default stat bar that has very basic functions, 
     * creates a bar that represents a hit point value, it has the default size, default color and the default values.
     */
    public LJStatBar()
    {
        maxHp = 100;
        currHp = 100;
        barWidth = 150;
        barHeight = 15;
        hpBarSize = barWidth;
        barY1 = 1;
        barY2 = barHeight + 2;
        barY3 = barHeight * 2 + 3;
        barY4 = barHeight * 3 + 4;
        myFont = new Font ("Courier New", false, false, barHeight - 2);
        bar = new GreenfootImage((barWidth + 2), (barHeight * 2 + 3));
        bar.setColor(barColor);
        bar.fill();
        bar.setColor(hpColor);
        bar.fillRect(1, barY2, barWidth, barHeight);
        bar.setColor(outlineColor);
        bar.drawRect(0, 0, barWidth + 1, barHeight + 1);
        bar.drawRect(0, barY2 - 1 , barWidth + 1, barHeight + 1);
        this.setImage(bar);
    }
    /**
     * Constructor that takes a interger width input and a interger height input, creates a stat bar with customized size with the inputs, 
     * values such as width and height of each seperate bar or the size of the font changes along with it.
     * 
     * @param inputWidth     The width of the stat bar
     * @param inputHeight    The height of the stat bar
     */
    public LJStatBar(int inputWidth, int inputHeight){
        this();
        barWidth = inputWidth;
        barHeight = inputHeight;
        barY1 = 1;
        barY2 = barHeight + 2;
        barY3 = barHeight * 2 + 3;
        barY4 = barHeight * 3 + 4;
        hpBarSize = barWidth;
        myFont = new Font ("Courier New", false, false, barHeight - 2);
        bar = new GreenfootImage((barWidth + 2), (barHeight * 2 + 3));
        bar.setColor(barColor);
        bar.fill();
        bar.setColor(hpColor);
        bar.fillRect(1, barY2, barWidth, barHeight);
        bar.setColor(outlineColor);
        bar.drawRect(0, 0, barWidth + 1, barHeight + 1);
        bar.drawRect(0, barY2 - 1 , barWidth + 1, barHeight + 1);
        this.setImage(bar);        
    }
    /**
     * Constructor that inherits the function of the previous constructor, takes another interger input called inputMaxHp, 
     * creates a stat bar with customized maximum hp value with that input and set the current hp to that maximum value.
     * 
     * @param inputWidth     The width of the stat bar
     * @param inputHeight    The height of the stat bar
     * @param inputMaxHp     The maximum hp value
     */
    public LJStatBar(int inputWidth, int inputHeight, int inputMaxHp){
        this(inputWidth, inputHeight);
        maxHp = inputMaxHp;
        currHp = maxHp;
    }
    /**
     * Constructor that inherits the function of the previous constructor, take another boolean input called stamina, 
     * if stamina == true, draw another bar with different color that reprsents the stamina value.
     * 
     * @param inputWidth     The width of the stat bar
     * @param inputHeight    The height of the stat bar
     * @param inputMaxHp     The maximum hp value
     * @param stamina        Does the stat bar display the stamina value
     */
    public LJStatBar(int inputWidth, int inputHeight, int inputMaxHp, boolean stamina){
        this(inputWidth, inputHeight, inputMaxHp);
        this.stamina = stamina;
        if(stamina == true){ 
            maxStamina = 100;
            currStamina = 100;
            manaBarSize = barWidth;
            bar = new GreenfootImage((barWidth + 2), (barHeight * 3 + 4));
            bar.setColor(barColor);
            bar.fill();
            bar.setColor(hpColor);
            bar.fillRect(1, barY2, barWidth, barHeight);
            bar.setColor(staminaColor);
            bar.fillRect(1, barY3, barWidth, barHeight);
            bar.setColor(outlineColor);
            bar.drawRect(0, 0, barWidth + 1, barHeight + 1);
            bar.drawRect(0, barY2 - 1, barWidth + 1, barHeight + 1);
            bar.drawRect(0, barY3 - 1, barWidth + 1, barHeight + 2);
            this.setImage(bar);
        }
    }
    /**
     * Constructor that inherits the functions of the previous constroctor, takes another interger input called inputMaxStamina, 
     * allows the programmer to customize the maximum stamina value of the stat bar by input.
     * 
     * @param inputWidth         The width of the stat bar
     * @param inputHeight        The height of the stat bar
     * @param inputMaxHp         The maximum hp value
     * @param stamina            Does the stat bar display the stamina value
     * @param inputMaxStamina    The maximum stamina value
     */
    public LJStatBar(int inputWidth, int inputHeight, int inputMaxHp, boolean stamina, int inputMaxStamina){
        this(inputWidth, inputHeight, inputMaxHp, stamina);
        if(stamina == true){
            maxStamina = inputMaxStamina;
            currStamina = maxStamina;
        }
    }
    /**
     * Constructor that inherits the functions of the previous constructor, takes another boolean input called mana, 
     * if mana == true, draw another bar with different color that represents the mana value.
     * 
     * @param inputWidth         The width of the stat bar
     * @param inputHeight        The height of the stat bar
     * @param inputMaxHp         The maximum hp value
     * @param stamina            Does the stat bar display the stamina value
     * @param inputMaxStamina    The maximum stamina value
     * @param mana               Does the stat bar display the mana value
     */
    public LJStatBar(int inputWidth, int inputHeight, int inputMaxHp, boolean stamina, int inputMaxStamina, boolean mana){
        this(inputWidth, inputHeight, inputMaxHp, stamina, inputMaxStamina);
        this.mana = mana;
        if(mana == true){ 
            maxMana = 100;
            currMana = 100;
            staminaBarSize = barWidth;
            if(stamina == true){
                bar = new GreenfootImage(barWidth + 2, barHeight * 4 + 5);
                bar.setColor(barColor);
                bar.fill();
                bar.setColor(hpColor);
                bar.fillRect(1, barY2, barWidth, barHeight);
                bar.setColor(staminaColor);
                bar.fillRect(1, barY3, barWidth, barHeight);
                bar.setColor(manaColor);
                bar.fillRect(1, barY4, barWidth, barHeight);
                bar.setColor(outlineColor);
                bar.drawRect(0, 0, barWidth + 1, barHeight + 1);
                bar.drawRect(0, barY2 - 1, barWidth + 1, barHeight + 1);
                bar.drawRect(0, barY3 - 1, barWidth + 1, barHeight + 2);
                bar.drawRect(0, barY4 - 1, barWidth + 1, barHeight + 2);                
                this.setImage(bar);
            }else if(stamina == false){
                bar = new GreenfootImage(barWidth + 2, barHeight * 3 + 4);
                bar.setColor(barColor);
                bar.fill();
                bar.setColor(hpColor);
                bar.fillRect(1, barY2, barWidth, barHeight);
                bar.setColor(manaColor);
                bar.fillRect(1, barY3, barWidth, barHeight);
                bar.setColor(outlineColor);
                bar.drawRect(0, 0, barWidth + 1, barHeight + 1);                
                bar.drawRect(0, barY2 - 1, barWidth + 1, barHeight + 1);
                bar.drawRect(0, barY3 - 2, barWidth + 1, barHeight + 2);
                this.setImage(bar);
            }
        }
    }
    /**
     * Constructor that inhetits the functions of the previous constructor, takes another interger input called inputMaxMana,
     * allows the programmer to customize the the maximum mana value of the stat bar by input.
     * 
     * @param inputWidth         The width of the stat bar
     * @param inputHeight        The height of the stat bar
     * @param inputMaxHp         The maximum hp value
     * @param stamina            Does the stat bar display the stamina value
     * @param inputMaxStamina    The maximum stamina value
     * @param mana               Does the stat bar display the mana value
     * @param inputMaxMana       The maximum mana value
     */
    public LJStatBar(int inputWidth, int inputHeight, int inputMaxHp, boolean stamina, int inputMaxStamina, boolean mana, 
    int inputMaxMana){
        this(inputWidth, inputHeight, inputMaxHp, stamina, inputMaxStamina, mana);
        if(mana == true){
            maxMana = inputMaxMana;
            currMana = maxMana;
        }
    }
    /**
     * Constroctor that inherits the functions of the previous constructor, takes another actor input called target,
     * allows the programmer to assign an actor to th stat bar by input.
     * 
     * @param inputWidth         The width of the stat bar
     * @param inputHeight        The height of the stat bar
     * @param inputMaxHp         The maximum hp value
     * @param stamina            Does the stat bar display the stamina value
     * @param inputMaxStamina    The maximum stamina value
     * @param mana               Does the stat bar display the mana value
     * @param target             The actor that the stat bar belongs to
     */
    public LJStatBar(int inputWidth, int inputHeight, int inputMaxHp, boolean stamina, int inputMaxStamina, boolean mana, 
    int inputMaxMana, Actor target){
        this(inputWidth, inputHeight, inputMaxHp, stamina, inputMaxStamina, mana, inputMaxMana);
        this.target = target;
    }
    public LJStatBar(int inputWidth, int inputHeight, int inputMaxHp, boolean stamina, int inputMaxStamina, boolean mana, 
    int inputMaxMana, Actor target, String inputName){
        this(inputWidth, inputHeight, inputMaxHp, stamina, inputMaxStamina, mana, inputMaxMana, target);
        name = inputName;
        bar.setColor(fontColor);
        bar.setFont(myFont);
        bar.drawString(name, 3, barHeight - barHeight/4);
        this.setImage(bar);
    }
    /**
     * Constructor that inherits the functions of the previous constructor, takes two additional String inputs called inputName and 
     * inputLevel, allows the programmer to input the name and the level of the actor and it will be printed on top of the stat bars.
     * 
     * @param inputWidth         The width of the stat bar
     * @param inputHeight        The height of the stat bar
     * @param inputMaxHp         The maximum hp value
     * @param stamina            Does the stat bar display the stamina value
     * @param inputMaxStamina    The maximum stamina value
     * @param mana               Does the stat bar display the mana value
     * @param target             The actor that the stat bar belongs to
     * @param inputName          The name of the actor that the stat bar belongs to
     * @param inputLevel         The Level of the actor that the stat bar belongs to
     */
    public LJStatBar(int inputWidth, int inputHeight, int inputMaxHp, boolean stamina, int inputMaxStamina, boolean mana, 
    int inputMaxMana, Actor target, String inputName, int inputLevel){
        this(inputWidth, inputHeight, inputMaxHp, stamina, inputMaxStamina, mana, inputMaxMana, target);
        name = inputName;
        level = inputLevel;
        bar.setColor(fontColor);
        bar.setFont(myFont);
        bar.drawString("Lv." + level + " " + name, 3, barHeight - barHeight/4);
        this.setImage(bar);
    }
    /**
     * Constructor that inherits the functions of the previous constructor, takes an additional boolean input called numDisplay,
     * if numDisplay == true, prints (current value) / (maximum value) on each stat bar for display.
     * 
     * @param inputWidth         The width of the stat bar
     * @param inputHeight        The height of the stat bar
     * @param inputMaxHp         The maximum hp value
     * @param stamina            Does the stat bar display the stamina value
     * @param inputMaxStamina    The maximum stamina value
     * @param mana               Does the stat bar display the mana value
     * @param target             The actor that the stat bar belongs to
     * @param inputName          The name of the actor that the stat bar belongs to
     * @param inputLevel         The Level of the actor that the stat bar belongs to
     * @param numDisplay         Does the stat bar display the numbers
     */
    public LJStatBar(int inputWidth, int inputHeight, int inputMaxHp, boolean stamina, int inputMaxStamina, boolean mana, 
    int inputMaxMana, Actor target, String inputName, int inputLevel, boolean numDisplay){
        this(inputWidth, inputHeight, inputMaxHp, stamina, inputMaxStamina, mana, inputMaxMana, target, inputName, inputLevel);
        this.numDisplay = numDisplay;
        if(numDisplay == true){
            bar.setColor(fontColor);
            bar.setFont(myFont);
            bar.drawString(currHp + " / " + maxHp, 3, barY3 - barHeight/4);
            if(stamina == true){
                bar.drawString(currStamina + " / " + maxStamina, 3, barY4 - barHeight/4);
            }else if(stamina == false && mana == true){
                bar.drawString(currMana + " / " + maxMana, 3, barY4 - barHeight/4);
            }
            if(mana == true && stamina == true){
                bar.drawString(currMana + " / " + maxMana, 3, barY4 + barHeight - barHeight/4);
            }
            this.setImage(bar);
        }
    }
    // The act method
    /** Find the actor that this stat bar belongs to and move along with it,
     *  remove the stat bar when the corresponding actor is removed.
     */
    public void act() 
    {
        if (target != null && target.getWorld() != null){
            setLocation (target.getX(), target.getY() - offset);
        }else{
            getWorld().removeObject(this);
        }
    }    
    // Overloaded updated methods
    /** 
     * Update method that takes an interger input called newCurrHp, sets the current hp value of the stat bar to it.
     * 
     * @param newCurrHp    The new current hp value of the stat bar
     */
    public void update(int newCurrHp){
        updateHp(newCurrHp);
    }
    /**
     * Overloaded update method that inherits the functions of the previous update method, takes an additional interger input called 
     * newCurrStamina, sets the current stamina value of the stat bar to it.
     * 
     * @param newCurrHp         The new current hp value of the stat bar
     * @param newCurrStamina    The new current stamina value of the stat bar
     */
    public void update(int newCurrHp, int newCurrStamina){
        updateHp(newCurrHp);
        updateStamina(newCurrStamina);
    }
    /**
     * Overloaded update method that inherits the functions of the previous update method, takes an additional interger input called 
     * newCurrMana, sets the current mana value of the stat bar to it.
     * 
     * @param newCurrHp         The new current hp value of the stat bar
     * @param newCurrStamina    The new current stamina value of the stat bar
     * @param newCurrMana       The new current mana value of the stat bar
     */
    public void update(int newCurrHp, int newCurrStamina, int newCurrMana){
        updateHp(newCurrHp);
        updateStamina(newCurrStamina);
        updateMana(newCurrMana);
    }
    /**
     * Overloaded update method that inherits the functions of the previous update method, takes an additional interger input called 
     * newMaxHp, sets the maximum hp value of the stat bar to it.
     * 
     * @param newCurrHp         The new current hp value of the stat bar
     * @param newCurrStamina    The new current stamina value of the stat bar
     * @param newCurrMana       The new current mana value of the stat bar
     * @param newMaxHp          The new maximum hp value of the stat bar
     */
    public void update(int newCurrHp, int newCurrStamina, int newCurrMana, int newMaxHp){
        updateHp(newCurrHp);
        updateStamina(newCurrStamina);
        updateMana(newCurrMana);
        updateMaxHp(newMaxHp);
    }
    /**
     * Overloaded update method that inherits the functions of the previous update method, takes an additional interger input called 
     * newMaxStamina, sets the maximum stamina value of the stat bar to it.
     * 
     * @param newCurrHp         The new current hp value of the stat bar
     * @param newCurrStamina    The new current stamina value of the stat bar
     * @param newCurrMana       The new current mana value of the stat bar
     * @param newMaxHp          The new maximum hp value of the stat bar
     * @param newMaxStamina     The new maximum stamina value of the stat bar
     */
    public void update(int newCurrHp, int newCurrStamina, int newCurrMana, int newMaxHp, int newMaxStamina){
        updateHp(newCurrHp);
        updateStamina(newCurrStamina);
        updateMana(newCurrMana);
        updateMaxHp(newMaxHp);
        updateMaxStamina(newMaxStamina);
    }
    /**
     * Overloaded update method that inherits the functions of the previous update method, takes an additional interger input called 
     * newMaxMana, sets the maximum mana value of the stat bar to it.
     * 
     * @param newCurrHp         The new current hp value of the stat bar
     * @param newCurrStamina    The new current stamina value of the stat bar
     * @param newCurrMana       The new current mana value of the stat bar
     * @param newMaxHp          The new maximum hp value of the stat bar
     * @param newMaxStamina     The new maximum stamina value of the stat bar
     * @param newMaxMana        The new maximum mana value of the stat bar
     */
    public void update(int newCurrHp, int newCurrStamina, int newCurrMana, int newMaxHp, int newMaxStamina, int newMaxMana){
        updateHp(newCurrHp);
        updateStamina(newCurrStamina);
        updateMana(newCurrMana);
        updateMaxHp(newMaxHp);
        updateMaxStamina(newMaxStamina);
        updateMaxMana(newMaxMana);
    }
    /**
     * Overloaded update method that inherits the functions of the previous update method, takes an additional String input called 
     * inputName, sets the name of the actor that displays on the stat bar to it.
     * 
     * @param newCurrHp         The new current hp value of the stat bar
     * @param newCurrStamina    The new current stamina value of the stat bar
     * @param newCurrMana       The new current mana value of the stat bar
     * @param newMaxHp          The new maximum hp value of the stat bar
     * @param newMaxStamina     The new maximum stamina value of the stat bar
     * @param newMaxMana        The new maximum mana value of the stat bar
     * @param inputName         The name of the actor that displays on top of the stat bar
     */
    public void update(int newCurrHp, int newCurrStamina, int newCurrMana, int newMaxHp, int newMaxStamina, int newMaxMana,
    String inputName){
        updateHp(newCurrHp);
        updateStamina(newCurrStamina);
        updateMana(newCurrMana);
        updateMaxHp(newMaxHp);
        updateMaxStamina(newMaxStamina);
        updateMaxMana(newMaxMana);
        updateName(inputName);
    }
    /**
     * Overloaded update method that inherits the functions of the previous update method, takes an additional interger input called
     * inputLevel, set the level that displays on top of the stat bar to it.
     * 
     * @param newCurrHp         The new current hp value of the stat bar
     * @param newCurrStamina    The new current stamina value of the stat bar
     * @param newCurrMana       The new current mana value of the stat bar
     * @param newMaxHp          The new maximum hp value of the stat bar
     * @param newMaxStamina     The new maximum stamina value of the stat bar
     * @param newMaxMana        The new maximum mana value of the stat bar
     * @param inputName         The name of the actor that displays on top of the stat bar
     * @param inputLevel        The level value that displays on top of the stat bar.
     */
    public void update(int newCurrHp, int newCurrStamina, int newCurrMana, int newMaxHp, int newMaxStamina, int newMaxMana,
    String inputName, int inputLevel){
        updateHp(newCurrHp);
        updateStamina(newCurrStamina);
        updateMana(newCurrMana);
        updateMaxHp(newMaxHp);
        updateMaxStamina(newMaxStamina);
        updateMaxMana(newMaxMana);
        updateName(inputName);
        updateLevel(inputLevel);
    }
    // Methods for reducing redundant codes.
    /**
     * the code that updates the current Hp, since it is used in many overloaded update methods, it was made into a method to 
     * reduce redundant codes.
     * 
     * @param newCurrHp         The new current hp value of the stat bar
     */
    public void updateHp(int newCurrHp){
        if(currHp != newCurrHp){
            currHp = newCurrHp;
            percentageHp = (double) newCurrHp / maxHp;
            hpBarSize = (int) (percentageHp * barWidth);
            bar.setColor(barColor);
            bar.fillRect(1, barY2, barWidth, barHeight);
            bar.setColor(hpColor);
            bar.fillRect(1, barY2, hpBarSize, barHeight);
            this.setImage(bar);
            if(numDisplay == true){
                bar.setColor(fontColor);
                bar.setFont(myFont);
                bar.drawString(currHp + " / " + maxHp, 3, barY3 - barHeight/4);
            }
        }
    }
    /**
     * the code that updates the current stamina, since it is used in many overloaded update methods, it was made into a method to 
     * reduce redundant codes.
     * 
     * @param newCurrStamina    The new current stamina value of the stat bar
     */
    public void updateStamina(int newCurrStamina){
        if(stamina == true && currStamina != newCurrStamina){
            currStamina = newCurrStamina;
            percentageStamina = (double) newCurrStamina / maxStamina;
            staminaBarSize = (int) (percentageStamina * barWidth);
            bar.setColor(barColor);
            bar.fillRect(1, barY3, barWidth, barHeight);
            bar.setColor(staminaColor);
            bar.fillRect(1, barY3, staminaBarSize, barHeight);
            this.setImage(bar);
            if(numDisplay == true){
                bar.setColor(fontColor);
                bar.setFont(myFont);
                bar.drawString(currStamina + " / " + maxStamina, 3, barY4 - barHeight/4);
            }
        }
    }
    /**
     * the code that updates the current mana, since it is used in many overloaded update methods, it was made into a method to 
     * reduce redundant codes.
     * 
     * @param newCurrMana       The new current mana value of the stat bar
     */
    public void updateMana(int newCurrMana){  
        if(mana == true && currMana != newCurrMana){
            currMana = newCurrMana;
            percentageMana = (double) newCurrMana / maxMana;
            manaBarSize = (int) (percentageMana * barWidth);
            if(stamina == true){
                bar.setColor(barColor);            
                bar.fillRect(1, barY4, barWidth, barHeight);
                bar.setColor(manaColor);
                bar.fillRect(1, barY4, manaBarSize, barHeight);
                if(numDisplay == true){
                    bar.setColor(fontColor);
                    bar.setFont(myFont);
                    bar.drawString(currMana + " / " + maxMana, 3, barY4 + barHeight - barHeight/4);
                }
                this.setImage(bar);
            }else{
                bar.setColor(barColor);            
                bar.fillRect(1, barY3, barWidth, barHeight);
                bar.setColor(manaColor);
                bar.fillRect(1, barY3, manaBarSize, barHeight);
                if(numDisplay == true){
                    bar.setColor(fontColor);
                    bar.setFont(myFont);
                    bar.drawString(currMana + " / " + maxMana, 3, barY4 - barHeight/4);
                }
                this.setImage(bar);
            }
        }
    }
    /**
     * the code that updates the maximum Hp, since it is used in many overloaded update methods, it was made into a method to 
     * reduce redundant codes.
     * 
     * @param newMaxHp          The new maximum hp value of the stat bar
     */
    public void updateMaxHp(int newMaxHp){
        if(maxHp != newMaxHp){
            maxHp = newMaxHp;
            percentageHp = (double) currHp / maxHp;
            hpBarSize = (int) (percentageHp * barWidth);
            bar.setColor(barColor);
            bar.fillRect(1, barY2, barWidth, barHeight);
            bar.setColor(hpColor);
            bar.fillRect(1, barY2, hpBarSize, barHeight);
            if(numDisplay == true){
                bar.setColor(fontColor);
                bar.setFont(myFont);
                bar.drawString(currHp + " / " + maxHp, 3, barY3 - barHeight/4);
            }
            this.setImage(bar);
        }
    }
    /**
     * the code that updates the maximum stamina, since it is used in many overloaded update methods, it was made into a method to 
     * reduce redundant codes.
     * 
     * @param newMaxStamina     The new maximum stamina value of the stat bar
     */
    public void updateMaxStamina(int newMaxStamina){
        if(stamina == true && maxStamina != newMaxStamina){
            maxStamina = newMaxStamina;
            percentageStamina = (double) currStamina / maxStamina;
            staminaBarSize = (int) (percentageStamina * barWidth);
            bar.setColor(barColor);
            bar.fillRect(1, barY3, barWidth, barHeight);
            bar.setColor(staminaColor);
            bar.fillRect(1, barY3, staminaBarSize, barHeight);
            if(numDisplay == true){
                bar.setColor(fontColor);
                bar.setFont(myFont);
                bar.drawString(currStamina + " / " + maxStamina, 3, barY4 - barHeight/4);
            }
            this.setImage(bar);
        }
    }
    /**
     * the code that updates the maximum Mana, since it is used in many overloaded update methods, it was made into a method to 
     * reduce redundant codes.
     * 
     * @param newMaxMana     The new maximum mana value of the stat bar
     */
    public void updateMaxMana(int newMaxMana){           
        if(mana == true && maxMana != newMaxMana){
            maxMana = newMaxMana;
            percentageMana = (double) currMana / maxMana;
            manaBarSize = (int) (percentageMana * barWidth);
            if(stamina == true){
                bar.setColor(barColor);            
                bar.fillRect(1, barY4, barWidth, barHeight);
                bar.setColor(manaColor);
                bar.fillRect(1, barY4, manaBarSize, barHeight);
                if(numDisplay == true){
                    bar.setColor(fontColor);
                    bar.setFont(myFont);
                    bar.drawString(currMana + " / " + maxMana, 3, barY4 + barHeight - barHeight/4);
                }
                this.setImage(bar);            
            }else{
                bar.setColor(barColor);            
                bar.fillRect(1, barY3, barWidth, barHeight);
                bar.setColor(manaColor);
                bar.fillRect(1, barY3, manaBarSize, barHeight);
                if(numDisplay == true){
                    bar.setColor(fontColor);
                    bar.setFont(myFont);
                    bar.drawString(currMana + " / " + maxMana, 3, barY4 - barHeight/4);
                }
                this.setImage(bar);
            }      
        }
    }
    /**
     * the code that updates the name of the actor that displays on top of the stat bar, 
     * since it is used in many overloaded update methods, it was made into a method to reduce redundant codes.
     * 
     * @param inputName         The name of the actor that displays on top of the stat bar
     */
    public void updateName(String inputName){
        if(name != null){
            if(name != inputName){
                bar.setColor(barColor);            
                bar.fillRect(1, barY1, barWidth, barHeight);
                name = inputName;
                bar.setColor(fontColor);
                bar.setFont(myFont);
                bar.drawString("Lv." + level + " " + name, 3, barHeight - 3);
                this.setImage(bar);
            }
        }
    }
    /**
     * the code that updates the level of the actor that displays on top of the stat bar, 
     * since it is used in many overloaded update methods, it was made into a method to reduce redundant codes.
     * 
     * @param inputLevel        The level value that displays on top of the stat bar.
     */
    public void updateLevel(int inputLevel){
        if(numDisplay == true && level != inputLevel){
            level = inputLevel;
            bar.setColor(barColor);            
            bar.fillRect(1, barY1, barWidth, barHeight);
            bar.setColor(fontColor);
            bar.setFont(myFont);
            bar.drawString("Lv." + level + " " + name, 3, barHeight - 3);
            this.setImage(bar);
        }
    }
    /** 
     * Method that set the transparency level of the stat bar, set it to 255 for the original image, set it to 0 for the image
     * to be totally transparent, values smaller than 0 or bigger than 255 will not be taken.
     * 
     * @param transparency    The transparency value of the stat bar
     */
    public void setTransparency(int transparency){
        if(0 <= transparency && transparency <= 255){
            bar.setTransparency(transparency);
        }
    }
    public void setOffset(int newOffset)
    {
        offset = newOffset;
    }
}
