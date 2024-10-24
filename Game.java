import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Game extends World
{
    private GreenfootSound gameMusic;

    public Game(int difficulty)
    {    
        super(500, 700, 1);
        setBackground(new GreenfootImage("Background.jpg"));
        gameMusic = new GreenfootSound("GameMusic.mp3");  
        MainShip userShip = new MainShip(2);
        addObject(userShip, 250, 600);
        gameMusic.setVolume(0);
        gameMusic.playLoop();
    }
    
    public void started() {
        // Ensure the music resumes when the world starts
        gameMusic.playLoop();
    }
    
    public void stopped() {
        // Pause the music when the world is stopped
        gameMusic.pause();
    }
}
