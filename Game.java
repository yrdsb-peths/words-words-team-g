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
    int wave = 1;
    boolean clearedWave = true;
    
    public Game(int difficulty)
    {    
        //creating new world
        super(500, 700, 1, false);
        setBackground(new GreenfootImage("Background.jpg"));
        
        //music
        gameMusic = new GreenfootSound("GameMusic.mp3");  
        gameMusic.setVolume(50);
        gameMusic.playLoop();
        
        //mainship
        MainShip userShip = new MainShip(2);
        addObject(userShip, 250, 600);
        userShip.turnTowards(250, 0);
    }

    public void makeWord() { // placeholder for making stuff fall down
        int  randomNum = Greenfoot.getRandomNumber(500);
        Enemy enemy = new Enemy(250, 600, randomNum);
        addObject(enemy, randomNum, 0);
    }

    public void removeEnemy(Enemy toRemove) { // removes object
        removeObject(toRemove);
    }

    public void act() { // press W key to make stuff fall down
        if(Greenfoot.isKeyDown("W"))
        {
            createEnemies();
        }
    }
    
    public void createEnemies()
    {
        if(clearedWave == true)
        {
            for(int i = 0; i < wave; i++)
            {
                int startX = Greenfoot.getRandomNumber(500);
                Enemy enemy = new Enemy(250, 600, startX);
                addObject(enemy, startX, 0);
            }
            clearedWave = false;
        }
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
