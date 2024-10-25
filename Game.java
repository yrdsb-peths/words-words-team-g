import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashSet;
import java.util.Set;

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
    Set<Enemy> enemyHolder = new HashSet<>();
    SimpleTimer timer = new SimpleTimer();
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
        timer.mark();
    }

    public void act() { // press W key to make stuff fall down
        createEnemies();
        checkCleared();
    }
    
    public void checkCleared()
    {
        if(enemyHolder.isEmpty() && clearedWave == false)
        {
            wave++;
            clearedWave = true;
        }
    }
    
    public void createEnemies()
    {
        if(enemyHolder.size() < wave && timer.millisElapsed()>1500 && clearedWave == true)
        {
            int startX = Greenfoot.getRandomNumber(500);
            Enemy enemy = new Enemy(250, 600);
            addObject(enemy, startX, 0);
            enemyHolder.add(enemy);
            addObject(enemy.label, startX, 0);
            timer.mark();
        }
        if(enemyHolder.size() == wave)
        {
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
