import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Simple and not the most efficient, but functional path finding algorithm.
 * Checks grids surrounding enemy to determine the direction of the next path.
 * 
 * @author (Cassidy Li) 
 * @version (21/01/21)
 */
public class PathFinding
{
    //declare instance variables
    private static Grids currentGrid;
    private static Grids endPoint;
    private static Grids startPoint;
    private static GameWorld world;
    private static String direction;
    private static Enemies theEnemy;
    private static boolean[][] visited = new boolean[20][12]; 
    
    /**
     * Pathfinding method for enemy, checks surrounding grids to determine a 
     * path
     * 
     * @param enemy         The enemy that needs to implement pathfinding
     * @param numGridsHor   The number of grids horizontally
     * @param numGridsVer   The number of grids vertically
     * 
     * @return              Returns the direction of the next path relative to
     *                      the enemy's current position. Returns end if no more
     *                      paths are available
     */
    public static String findPath(Enemies enemy, int numGridsHor, int numGridsVer){
        theEnemy = enemy;
        int indexX = enemy.getX() / 50;
        int indexY = enemy.getY() / 50;
        world = (GameWorld)enemy.getWorld();
        
        if(checkLeft(enemy, indexX, indexY, numGridsHor - 1, numGridsVer - 1)){
            enemy.setVisited(indexX, indexY);
            int x = world.getGrid(indexX - 1, indexY).getX();
            int y = world.getGrid(indexX - 1, indexY).getY();
            enemy.turnTowards(x, y);
            return "left";
        }else if(checkRight(enemy, indexX, indexY, numGridsHor - 1, numGridsVer - 1)){
            enemy.setVisited(indexX, indexY);
            int x = world.getGrid(indexX + 1, indexY).getX();
            int y = world.getGrid(indexX + 1, indexY).getY();
            enemy.turnTowards(x, y);
            return "right";
        }else if(checkUp(enemy, indexX, indexY, numGridsHor - 1, numGridsVer - 1)){
            enemy.setVisited(indexX, indexY);
            int x = world.getGrid(indexX, indexY - 1).getX();
            int y = world.getGrid(indexX, indexY - 1).getY();
            enemy.turnTowards(x, y);
            return "up";
        }else if(checkDown(enemy, indexX, indexY, numGridsHor - 1, numGridsVer - 1)){
            enemy.setVisited(indexX, indexY);
            int x = world.getGrid(indexX, indexY + 1).getX();
            int y = world.getGrid(indexX, indexY + 1).getY();
            enemy.turnTowards(x, y);
            return "down";
        }
        //returns end if no more paths are available
        return "end";
    }
    //checks if there is a path to the left of the enemy
    private static boolean checkLeft(Enemies enemy, int indexX, int indexY, int maxX, int maxY){
        
        if(indexX > 0){
            Grids grid = world.getGrid(indexX - 1, indexY);
            if(!enemy.hasVisited(indexX - 1,indexY)){
                return grid.getPath();
            }
        }
        return false;
    }
    //checks if there is a path to the right of the enemy
    private static boolean checkRight(Enemies enemy, int indexX, int indexY, int maxX, int maxY){
        
        if(indexX < maxX){
            Grids grid = world.getGrid(indexX + 1, indexY);
            if(!enemy.hasVisited(indexX + 1,indexY)){
                return grid.getPath();
            }
        }
        return false;
    }
    //checks if there is a path above the enemy
    private static boolean checkUp(Enemies enemy, int indexX, int indexY, int maxX, int maxY){
        
        if(indexY > 0){
            Grids grid = world.getGrid(indexX, indexY - 1);
            if(!enemy.hasVisited(indexX,indexY - 1)){
                return grid.getPath();
            }
        }
        return false;
    }
    //checks if there is a path below the enemy
    private static boolean checkDown(Enemies enemy, int indexX, int indexY, int maxX, int maxY){
        
        if(indexY < maxY){
            Grids grid = world.getGrid(indexX, indexY + 1);
            if(!enemy.hasVisited(indexX,indexY + 1)){
                return grid.getPath();
            }
        }
        return false;
    }
}
