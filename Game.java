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
        //creating new world
        super(500, 700, 1);
        setBackground(new GreenfootImage("Background.jpg"));
        
        //music
        gameMusic = new GreenfootSound("GameMusic.mp3");  
        gameMusic.setVolume(50);
        gameMusic.playLoop();
        
        //mainship
        MainShip userShip = new MainShip(2);
        addObject(userShip, 250, 600);
    }

    public void makeWord() { // placeholder for making stuff fall down
        Enemy enemy = new Enemy();
        int  randomNum = Greenfoot.getRandomNumber(500);
        enemy.setStartX(randomNum);
        addObject(enemy, randomNum, 0);
    }

    public void removeEnemy(Enemy toRemove) { // removes object
        removeObject(toRemove);
    }

    public void act() { // press W key to make stuff fall down
        if(Greenfoot.isKeyDown("W"))
        {
            makeWord();
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
