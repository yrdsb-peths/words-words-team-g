import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.*;

public class Game extends World
{
    GreenfootSound gameMusic;
    private GreenfootSound destroyShip;
    
    String currentWord;
    Label scoreLabel; 
    Label waveLabel;
    MainShip userShip;
    
    HashMap<String, Enemy> enemyHolder = new HashMap<>();
    HashMap<String, Enemy> sameLetterEnemy = new HashMap<>();
    
    ArrayList<String> words = new ArrayList<>();
    ArrayList<Enemy> enemiesInWave = new ArrayList<Enemy>();
    
    SimpleTimer timer = new SimpleTimer();
    SimpleTimer spawnTimer = new SimpleTimer();
    SimpleTimer pauseTimer = new SimpleTimer();
    SimpleTimer jamTimer = new SimpleTimer();

    
    int score = 0; 
    int wave = 1;
    int enemySpeed;
    int scoreMultiplier;
    int jamTime = 800;
    boolean hasForcefield = false;
    boolean clearedWave = true;
    boolean doubleLetters = false;
    
    Color OFF_WHITE = new Color(251, 247, 245);
    public Game(int difficulty,int whichShip, int enemySpeed)
    {    
        //creating new world
        super(500, 700, 1, false);
        this.enemySpeed = enemySpeed;
        setBackground(new GreenfootImage("Background.jpg"));
        
        //music
        gameMusic = new GreenfootSound("GameMusic.mp3");  
        gameMusic.setVolume(50);
        gameMusic.playLoop();
        
        //mainship abilities
        if(whichShip == 1)
        {
            hasForcefield = false;
            doubleLetters = false;
            jamTime /= 5;
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
        
        loadWords();
        spawnTimer.mark();
        pauseTimer.mark();
        jamTimer.mark();
        
        userShip = new MainShip(whichShip);
        addObject(userShip, 250, 600);
        userShip.turnTowards(250, 0);
        
        waveLabel = new Label("Wave " + wave, 60);
        
        scoreLabel = new Label("Score: " + score, 40); // Initialize score label
        addObject(scoreLabel, getWidth() - 100, 20); // Position top right
        waveLabel.setFillColor(OFF_WHITE);
        scoreMultiplier = difficulty;
    }

    /**
     * creates enemies and checks if screen is cleared then checks for user inputs. 
     */
    public void act() {
        createEnemies();
        checkCleared();
        userInput();
    }
 
    /**
     * loading words from file
     */
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
        String lastPressed = Greenfoot.getKey(); // Key user last inputted
        if(jamTimer.millisElapsed() < jamTime)
        {
            lastPressed = null;
            userShip.showJammed();
        }
        else
        {
            userShip.removeJammed();
        }
        if(lastPressed != null) { // Only runs if user pressed a key
            if(currentWord == null) { // If word has not been selected
                selectWord(lastPressed);
                return;
            }
            else {
                if(lastPressed.equals("backspace")) { // allows user to select another word
                    Enemy enemy = enemyHolder.get(currentWord); // specific enemy
                    enemy.label.setFillColor(Color.WHITE);
                    enemy.label.setLineColor(Color.WHITE);
                    currentWord = null;
                    userShip.target = null;
                }
                else{
                    if(lastPressed.equals(currentWord.substring(0,1))) { // if input matches letter to be typed, remove it
                        subtractLetter();
                        //sound
                    }
                    else
                    {
                        jamTimer.mark();
                    }
                }
            }
        }
    }
    
    public void selectWord(String lastPressed) {
        double lowestDistance = Integer.MAX_VALUE;
        for(String word : enemyHolder.keySet()) {
            // finds the word that starts with the letter the user inputed and is the shortest distance from the main ship
            if(word.substring(0,1).equals(lastPressed) && enemyHolder.get(word).distanceFrom() < lowestDistance) {
                lowestDistance = enemyHolder.get(word).distanceFrom();
                currentWord = word;
            }
        }
        if(currentWord == null) // If letter inputted does not correspond to any word, jam gun
        {
            jamTimer.mark();
        }
        else
        {
            Enemy enemy = enemyHolder.get(currentWord); // specific enemy
            
            enemy.label.setFillColor(Color.ORANGE);
            enemy.label.setLineColor(Color.ORANGE);
            userShip.target = enemy;
            subtractLetter();
        }
    }

    public void subtractLetter() { // Initial enemy
        Enemy enemy = enemyHolder.get(currentWord); // specific enemy
        if(currentWord == null) {
            return;
        }
        else 
        {
            if(currentWord.length() <= 1) { // remove everything if word is compeleted
                String newWord = currentWord.substring(1); //remove first letter from label
                enemy.label.setValue(newWord);
                Laser laser = new Laser(doubleLetters, enemy); // Laser animation if last letter
                addObject(laser,userShip.getX(),userShip.getY());
                
                destroyShip = new GreenfootSound("destroyShip.mp3");
                destroyShip.setVolume(75);
                destroyShip.play();
                currentWord = null;
    
                removeFromMap(enemy);
                userShip.target = null;
            }
            else {
                String newWord = currentWord.substring(1); //remove first letter from label
                enemy.label.setValue(newWord);
    
                enemyHolder.remove(currentWord);
                enemyHolder.put(newWord, enemy); // re-add to map, so the remains of the word matches what user sees
    
                currentWord = newWord;
            }
        }
    }

    /**
     * removing letters from enemies when the correct letter is typed in, removing the whole thing when enemy is defeated. 
     */
    public void explosionSubtract(Enemy currentEnemy) { // Surrounding enemies
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
                    if(enemy == userShip.target)
                    {
                        currentWord = null;
                        userShip.target = null;
                    }
                    enemy.removeEnemy();
                    surroundingWord = null;
                }
                else {
                    String newWord = surroundingWord.substring(1); //remove first letter from label
                    enemy.label.setValue(newWord);
                    
                    enemyHolder.remove(surroundingWord);
                    enemyHolder.put(newWord, enemy); // re-add to map, so the remains of the word matches what user sees
                    if(enemy == userShip.target)
                    {
                        currentWord = newWord;
                    }
                }
            }
        }    
    }

    /**
     * Checks if the wave is cleared and prepares for the next wave
     */
    public void checkCleared()
    {
        if(enemyHolder.isEmpty() && enemiesInWave.size() == 0 && clearedWave == false) // If all enemies are spawened and removed, new wave
        {
            wave++;
            clearedWave = true;
            pauseTimer.mark();
        }
    }
    
    /**
     * Creates enemies at the start of each wave
     */
    public void createEnemies()
    {
        if(pauseTimer.millisElapsed() > 3000)
        {
            removeObject(waveLabel);
            if(clearedWave == true)
            {
                for(int i = 0; i < wave; i++) // Spawns enemies based on wave number
                {
                    enemiesInWave.add(new Enemy(250, 600, enemySpeed));
                }
                clearedWave = false;
            }
            loadEnemies();
        }
        else
        {
            waveLabel.setValue("Wave " + wave); // Start of wave label
            addObject(waveLabel, getWidth()/2, getHeight()/2);
        }
    }
    
    /**
     * Loads enemies with unique words from the words list
     */
    public void loadEnemies()
    {
        if(enemiesInWave.size() > 0 && spawnTimer.millisElapsed()>1500 - wave * 25) // Spawns enemies over time
        {
            int startX = Greenfoot.getRandomNumber(500); // Random spawn at the top of screen
            Enemy enemy = enemiesInWave.get(0);
            addObject(enemy, startX, 0);
            addObject(enemy.label, startX, 0); // Label containing the word
            int randomWordIndex;
            boolean wordExists = false;
            do { // No duplicate words for enemies
                randomWordIndex = Greenfoot.getRandomNumber(words.size());
                wordExists = false;
                for(String key : enemyHolder.keySet()) {
                    if(enemyHolder.get(key).originalWord.equals(words.get(randomWordIndex))) {
                        wordExists = true;
                    }
                }
            } while(wordExists == true);
            String randomWord = words.get(randomWordIndex); // Set the random word to the label
            enemy.label.setValue(randomWord);
            enemy.originalWord = randomWord;
            enemyHolder.put(randomWord, enemy);
            enemy.label.setValue(words.get(randomWordIndex));
            enemiesInWave.remove(0);
            spawnTimer.mark();
        }
    }

    /**
     * Removes an enemy from the map by its corresponding string key
     */
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
    
    /**
     * Returns the current score of the player
     */
    public int getScore() {
        return score; // Provide score for GameOver screen
    }

    /**
     * Starts the background music when the game starts
     */
    public void started() {
        // Ensure the music resumes when the world starts
        gameMusic.playLoop();
    }
    
    /**
     * stops the background music
     */
    public void stopped() {
        // Pause the music when the world is stopped
        gameMusic.pause();
    }
}
