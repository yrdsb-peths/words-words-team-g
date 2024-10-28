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
    String currentWord;

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
        String lastPressed = Greenfoot.getKey();
        if(lastPressed != null) {
            if(currentWord == null) { // If word has not been selected
                selectWord(lastPressed);
                return;
            }
            else {
                if(lastPressed.equals("backspace")) { // allows user to select another word
                    currentWord = null;
                }
                else {
                    if(lastPressed.equals(currentWord.substring(0,1))) { // if input matches letter to be typed, remove it
                        subtractLetter();
                    }
                }
            }
        }
    }
    
    public void selectWord(String lastPressed) {
        if(lastPressed != null) {
            for(String word : enemyHolder.keySet()) {
                if(word.substring(0,1).equals(lastPressed)) { // finds first word that starts with the letter the user inputed
                    currentWord = word;
                    subtractLetter();
                    break;
                }
            }
        }
    }

    public void subtractLetter() {
        Enemy enemy = enemyHolder.get(currentWord); // specific enemy

        if(currentWord.length() <= 1) { // remove everything if word is compeleted
            removeFromMap(enemy);
            removeObject(enemy.label);
            removeObject(enemy);
            currentWord = null;
        }
        else {
            String newWord = currentWord.substring(1); //remove first letter from label
            enemy.label.setValue(newWord);

            enemyHolder.remove(currentWord);
            enemyHolder.put(newWord, enemy); // re-add to map, so the remains of the word matches what user sees

            currentWord = newWord;
        }
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
        wave = 5;
        if(enemyHolder.size() < wave && timer.millisElapsed()>1500 && clearedWave == true)
        {
            int startX = Greenfoot.getRandomNumber(500);
            Enemy enemy = new Enemy(250, 600);
            addObject(enemy, startX, 0);
            addObject(enemy.label, startX, 0);
            timer.mark();

            int randomWordIndex = Greenfoot.getRandomNumber(words.size() - 1);
            String randomWord = words.get(randomWordIndex);
            enemy.label.setValue(randomWord);
            enemy.originalWord = randomWord;
            enemyHolder.put(randomWord, enemy);
        }


        if(enemyHolder.size() == wave)
        {
            clearedWave = false;
        }
    }

    public void removeFromMap(Enemy enemy) {
        String mapKey = "";
        for(String key : enemyHolder.keySet()) {
            if(enemyHolder.get(key).equals(enemy)) { // finds string that corresponds to the specific enemy
                mapKey = key;
            }
        }
        if(mapKey != "") {
            enemyHolder.remove(mapKey); // removes the enemy from the map
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
