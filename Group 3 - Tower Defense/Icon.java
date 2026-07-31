import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A simple widget class that is written for this game. create icons with a customizable image. 
 * The icon is just for displaying, it does not do anything on its own.
 * 
 * @author (Liangyi Jinjing) 
 * @version (1/27/2021)
 */
public class Icon extends Actor
{
    // Declare variables
    private GreenfootImage tempImage;
    private GreenfootImage Image1;
    private GreenfootImage Image2;
    private GreenfootImage Image3;
    private GreenfootImage Image4;
    private int myType;
    /**
     * The main constructor for Icon, takes in an int variable that indicate what type of icon this class is,
     * also takes in an int variable that indicates how transparent the image will be.
     * 
     * @param    int type            int variable that indicate what type of icon this class is
     * @param    int transparency    int variable that indicates how transparent the image will be
     */
    public Icon(int type, int transparency){
        myType = type;
        tempImage = new GreenfootImage(1, 1);
        Image1 = new GreenfootImage("crossbow_lv1_01.png");
        Image1.mirrorHorizontally();
        Image2 = new GreenfootImage("catapult_lv1_01.png");
        Image2.mirrorHorizontally();
        Image3 = new GreenfootImage("cannon_lv1.png");
        Image3.mirrorHorizontally();
        Image4 = new GreenfootImage("upgrade.png");
        if(type == 0){
            setImage(tempImage);
        }
        if(type == 1){           
            Image1.setTransparency(transparency);
            setImage(Image1);
        }
        if(type == 2){
            Image2.setTransparency(transparency);
            setImage(Image2);
        }
        if(type == 3){
            Image3.setTransparency(transparency);
            setImage(Image3);
        }     
        if(type == 4){
            Image4.setTransparency(transparency);
            setImage(Image4);
        }     
    }
    /**
     * Getter method that tells the user what type of icon this is.
     * 
     * @return    mtType    The int variable that indicates what type of icon this is.
     */
    public int getType(){
        return myType;
    }
    /**
     * Change the icon to a new type and a new transparency.
     * 
     * @param    int type            The int variable that indicates what type of icon the original icon will change into.
     * @param    int transparency    How transparent the new icon will be.
     */
    public void change(int type, int transparency){
        myType = type;
        if(type == 0){
            setImage(tempImage);
        }
        if(type == 1){           
            Image1.setTransparency(transparency);
            setImage(Image1);
        }
        if(type == 2){
            Image2.setTransparency(transparency);
            setImage(Image2);
        }
        if(type == 3){
            Image3.setTransparency(transparency);
            setImage(Image3);
        }        
        if(type == 4){
            Image4.setTransparency(transparency);
            setImage(Image4);
        }     
    }
}
