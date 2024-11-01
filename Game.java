import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.*;

public class Game extends World
{
    private GreenfootSound gameMusic;
    HashMap<String, Enemy> enemyHolder = new HashMap<>();
    SimpleTimer timer = new SimpleTimer();
    ArrayList<String> words = new ArrayList<>();
    ArrayList<Enemy> enemiesInWave = new ArrayList<Enemy>();
    SimpleTimer spawnTimer = new SimpleTimer();
    SimpleTimer pauseTimer = new SimpleTimer();
    Label waveLabel;
    String currentWord;
    boolean hasForcefield = false;
    int wave = 1;
    boolean clearedWave = true;
    boolean doubleLetters = false;
    
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
            doubleLetters = false;
        }
        else if(whichShip == 2)
        {
            hasForcefield = false;
            doubleLetters = true;
        }
        else
        {
            hasForcefield = true;
            doubleLetters = false;
        }
        MainShip userShip = new MainShip(whichShip);
        addObject(userShip, 250, 600);
        userShip.turnTowards(250, 0);
        loadWords();
        spawnTimer.mark();
        pauseTimer.mark();
        waveLabel = new Label("Wave " + wave, 60);
        Color OFF_WHITE = new Color(251, 247, 245);
        waveLabel.setFillColor(OFF_WHITE);
    }

    public void act() {
        createEnemies();
        checkCleared();
        userInput();
    }
 
    public void loadWords() {
        try { // Adds all the words from text file to an arraylist
            Scanner scanner = new Scanner(new File("words.txt"));
            while(scanner.hasNextLine()) {
                String word = scanner.nextLine();
                words.add(word);
            }
            scanner.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void userInput() {
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
        for(String word : enemyHolder.keySet()) {
            if(word.substring(0,1).equals(lastPressed)) { // finds first word that starts with the letter the user inputed
                Enemy enemy = enemyHolder.get(word);
                makeExplosion(enemy);
                currentWord = word;
                subtractLetter();
                break;
            }
        }
    }

    public void subtractLetter() { // Initial enemy
        Enemy enemy = enemyHolder.get(currentWord); // specific enemy

        if(currentWord != null) {
            if(currentWord.length() <= 1) { // remove everything if word is compeleted
                removeFromMap(enemy);
                removeObject(enemy.label);
                removeObject(enemy);
                currentWord = null;
            }
            else {
                makeExplosion(enemy);
                String newWord = currentWord.substring(1); //remove first letter from label
                enemy.label.setValue(newWord);
    
                enemyHolder.remove(currentWord);
                enemyHolder.put(newWord, enemy); // re-add to map, so the remains of the word matches what user sees
    
                currentWord = newWord;
            }
        }
    }

    public void subtractLetter(Enemy currentEnemy) { // Surrounding enemies
        if(doubleLetters) {
            Enemy enemy = currentEnemy;
            String surroundingWord = null;
    
            for(String key : enemyHolder.keySet()) { // find string of enemy
                if(enemyHolder.get(key).equals(enemy)) {
                    surroundingWord = key;
                }
            }
    
            if(surroundingWord != null) {
                if(surroundingWord.length() <= 1) { // remove everything if word is compeleted
                    removeFromMap(enemy);
                    removeObject(enemy.label);
                    removeObject(enemy);
                    surroundingWord = null;
                }
                else {
                    String newWord = surroundingWord.substring(1); //remove first letter from label
                    enemy.label.setValue(newWord);
        
                    enemyHolder.remove(surroundingWord);
                    enemyHolder.put(newWord, enemy); // re-add to map, so the remains of the word matches what user sees
                }
            }
        }    
    }

    public void makeExplosion(Enemy enemy) {
        addObject(new Explosion(enemy), enemy.getX(), enemy.getY());
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
            addObject(enemy.label, startX, 0);
            int randomWordIndex;
            boolean wordExists = false;
            do { // No duplicate words in 
                randomWordIndex = Greenfoot.getRandomNumber(words.size() - 1);
                for(String key : enemyHolder.keySet()) {
                    if(enemyHolder.get(key).originalWord.equals(words.get(randomWordIndex))) {
                        wordExists = true;
                    }
                    else {
                        wordExists = false;
                    }
                }
            } while(wordExists == true);
            String randomWord = words.get(randomWordIndex);
            enemy.label.setValue(randomWord);
            enemy.originalWord = randomWord;
            enemyHolder.put(randomWord, enemy);
            enemy.label.setValue(words.get(randomWordIndex));
            enemiesInWave.remove(0);
            spawnTimer.mark();
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
