import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.*;

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
    HashMap<String, Enemy> enemyHolder = new HashMap<>();
    SimpleTimer timer = new SimpleTimer();
    ArrayList<String> words = new ArrayList<>();

    public Game(int difficulty,int whichShip)
    {    
        //creating new world
        super(500, 700, 1, false);
        setBackground(new GreenfootImage("Background.jpg"));
        
        //music
        gameMusic = new GreenfootSound("GameMusic.mp3");  
        gameMusic.setVolume(50);
        gameMusic.playLoop();
        
        //mainship
        MainShip userShip = new MainShip(whichShip);
        addObject(userShip, 250, 600);
        userShip.turnTowards(250, 0);
        timer.mark();

        try { // Adds all the words from text file to an arraylist
            BufferedReader bufferedReader = new BufferedReader(new FileReader("words.txt"));
            String currentLine;
            do {
                currentLine = bufferedReader.readLine();
                words.add(currentLine);
                currentLine = bufferedReader.readLine();
            } while(currentLine != null);
            bufferedReader.close();
        } 
        catch (IOException e) {
            System.out.println("Error reading textfile");
            System.out.println("Error: " + e.toString());
        }
    }

    public void act() {
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
            enemyHolder.put(enemy.toString(), enemy);
            addObject(enemy.label, startX, 0);
            timer.mark();

            int randomWordIndex = Greenfoot.getRandomNumber(words.size() - 1);

            enemy.label.setValue(words.get(randomWordIndex));
        }

        if(enemyHolder.size() == wave)
        {
            clearedWave = false;
        }
    }
    
    public boolean inEnemySet() {
        
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
