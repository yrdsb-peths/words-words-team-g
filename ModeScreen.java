import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ModeScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ModeScreen extends World
{
    /**
     * Constructor for objects of class ModeScreen.
     * 
     */
    public ModeScreen()
    {    
        super(500, 700, 1);
        setBackground(new GreenfootImage("Background.jpg")); 
        addObject(new Button(this::hardMode, "Hard"), 250, 360);
        addObject(new Button(this::mediumMode, "Medium"), 250, 310); 
        addObject(new Button(this::easyMode, "Easy"), 250, 260);
        addObject(new Button(this::extremeMode, "Extreme"), 250, 410);
        
    }
    
    public void easyMode()
    {
        goGame(1);
    }
    
    public void mediumMode()
    {
        goGame(2);
    }
    
    public void hardMode()
    {
        goGame(3);
    }
    
    public void extremeMode()
    {
        goGame(4);
    }
    
    public void goGame(int difficulty)
    {
        Greenfoot.setWorld(new Game(difficulty));
    }
}
