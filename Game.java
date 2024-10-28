import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
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
    boolean hasForcefield = false;
    Set<Enemy> enemyHolder = new HashSet<>();
    ArrayList<String> words = new ArrayList<>();
    ArrayList<Enemy> enemiesInWave = new ArrayList<Enemy>();
    SimpleTimer spawnTimer = new SimpleTimer();
    SimpleTimer pauseTimer = new SimpleTimer();
    Label waveLabel;
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
        if(whichShip == 1)
        {
            hasForcefield = false;
        }
        else if(whichShip == 2)
        {
            hasForcefield = false;
        }
        else
        {
            hasForcefield = true;
        }
        MainShip userShip = new MainShip(whichShip);
        addObject(userShip, 250, 600);
        userShip.turnTowards(250, 0);
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
        spawnTimer.mark();
        pauseTimer.mark();
        waveLabel = new Label("Wave " + wave, 60);
        Color OFF_WHITE = new Color(251, 247, 245);
        waveLabel.setFillColor(OFF_WHITE);
    }

    public void act() {
        createEnemies();
        checkCleared();
    }
    
    public void checkCleared()
    {
        if(enemyHolder.isEmpty() && enemiesInWave.size() == 0 && clearedWave == false)
        {
            wave++;
            clearedWave = true;
            pauseTimer.mark();
        }
    }
    
    public void createEnemies()
    {
        if(pauseTimer.millisElapsed() > 3000)
        {
            removeObject(waveLabel);
            if(clearedWave == true)
            {
                for(int i = 0; i < wave; i++)
                {
                    enemiesInWave.add(new Enemy(250, 600));
                }
                clearedWave = false;
            }
            loadEnemies();
        }
        else
        {
            waveLabel.setValue("Wave " + wave);
            addObject(waveLabel, getWidth()/2, getHeight()/2);
        }
    }
    
    public void loadEnemies()
    {
        if(enemiesInWave.size() > 0 && spawnTimer.millisElapsed()>1500 - wave * 25)
        {
            int startX = Greenfoot.getRandomNumber(500);
            Enemy enemy = enemiesInWave.get(0);
            addObject(enemy, startX, 0);
            enemyHolder.add(enemy);
            addObject(enemy.label, startX, 0);
            int randomWordIndex = Greenfoot.getRandomNumber(words.size() - 1);
            enemy.label.setValue(words.get(randomWordIndex));
            enemiesInWave.remove(0);
            spawnTimer.mark();
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
