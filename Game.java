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
    public Game()
    {    
        super(500, 700, 1);
        setBackground(new GreenfootImage("Background.jpg"));
        MainShip userShip = new MainShip(2);
        addObject(userShip, 250, 600);
    }

    public void makeWord() { // placeholder for making stuff fall down
        Enemy enemy = new Enemy();
        int  randomNum = Greenfoot.getRandomNumber(300);
        enemy.setStartX(randomNum);
        addObject(enemy, randomNum, 0);
    }
    public void act() { // press W key to make stuff fall down
        if(Greenfoot.isKeyDown("W"))
        {
            makeWord();
        }
    }
}
