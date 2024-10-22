import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MenuScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MenuScreen extends World
{

    public MenuScreen()
    {    
        super(500, 700, 1); 
        addObject(new Button(this::goInstructions, "Instructions"), 300, 360);
        addObject(new Button(this::goHighScores, "High Scores"), 300, 310); 
        addObject(new Button(this::goGame, "Start Game"), 300, 260);
    }
    
    
    public void goInstructions() {
        Greenfoot.setWorld(new InstructionScreen(this));
    }
    
    //going to high score screen
    public void goHighScores(){
        Greenfoot.setWorld(new HighScore());  
    }
    public void goGame(){
        Greenfoot.setWorld(new Game());
    }
}
