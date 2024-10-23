import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Game extends World
{

    /**
     * Constructor for objects of class Game.
     * 
     */
    public Game(int difficulty)
    {    
        super(500, 700, 1);
        setBackground(new GreenfootImage("Background.jpg"));
        MainShip userShip = new MainShip(2);
        addObject(userShip, 250, 600);
    }
}
