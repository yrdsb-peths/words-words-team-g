import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.*;

public class Game extends World
{
    private GreenfootSound gameMusic;
    private GreenfootSound destroyShip;
    private int score = 0; 
    private Label scoreLabel; 
    
    HashMap<String, Enemy> enemyHolder = new HashMap<>();
    HashMap<String, Enemy> sameLetterEnemy = new HashMap<>();
    SimpleTimer timer = new SimpleTimer();
    ArrayList<String> words = new ArrayList<>();
    ArrayList<Enemy> enemiesInWave = new ArrayList<Enemy>();
    SimpleTimer spawnTimer = new SimpleTimer();
    SimpleTimer pauseTimer = new SimpleTimer();
    Label waveLabel;
    String currentWord;
    
    boolean hasForcefield = false;
    int wave = 1;
    int enemySpeed;
    boolean clearedWave = true;
    boolean doubleLetters = false;
    int jamTime = 1000;
    SimpleTimer jamTimer = new SimpleTimer();
    MainShip userShip;
    
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
        
        //mainship
        if(whichShip == 1)
        {
            hasForcefield = false;
            doubleLetters = false;
            jamTime = 500;
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
        userShip = new MainShip(whichShip);
        addObject(userShip, 250, 600);
        userShip.turnTowards(250, 0);
        loadWords();
        spawnTimer.mark();
        pauseTimer.mark();
        jamTimer.mark();
        waveLabel = new Label("Wave " + wave, 60);
        Color OFF_WHITE = new Color(251, 247, 245);
        waveLabel.setFillColor(OFF_WHITE);
        scoreLabel = new Label("Score: " + score, 40); // Initialize score label
        addObject(scoreLabel, getWidth() - 80, 20); // Position top right
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
        if(jamTimer.millisElapsed() < jamTime)
        {
            lastPressed = null;
            userShip.showJammed();
        }
        else
        {
            userShip.removeJammed();
        }
        if(lastPressed != null) {
            if(currentWord == null) { // If word has not been selected
                selectWord(lastPressed);
                return;
            }
            else {
                if(lastPressed.equals("backspace")) { // allows user to select another word
                    Enemy enemy = enemyHolder.get(currentWord); // specific enemy
                    enemy.label.setFillColor(Color.WHITE);
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
                subtractLetter();
                return;
            }
        }
        if(currentWord == null)
        {
            jamTimer.mark();
        }
        else
        {
            Enemy enemy = enemyHolder.get(currentWord); // specific enemy
            
            enemy.label.setFillColor(Color.ORANGE);
            subtractLetter();
            userShip.target = enemy;
        }
    }

    public void subtractLetter() { // Initial enemy
        Enemy enemy = enemyHolder.get(currentWord); // specific enemy
        if(currentWord == null) {
            return;
        }
        if(currentWord.length() <= 1) { // remove everything if word is compeleted
            removeFromMap(enemy);
            destroyShip = new GreenfootSound("destroyShip.mp3");
            destroyShip.setVolume(75);
            destroyShip.play();
            removeObject(enemy.label);
            userShip.target = null;
            removeObject(enemy);
            currentWord = null;
            score++; // Increment score when an enemy is destroyed
            scoreLabel.setValue("Score: " + score); // Update score label
        }
        else {
            String newWord = currentWord.substring(1); //remove first letter from label
            enemy.label.setValue(newWord);
        if(currentWord != null) {
            if(currentWord.length() <= 1) { // remove everything if word is compeleted
                makeExplosion(enemy);

                destroyShip = new GreenfootSound("destroyShip.mp3");
                destroyShip.setVolume(75);
                destroyShip.play();

                userShip.target = null;

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
                    enemiesInWave.add(new Enemy(250, 600,enemySpeed));
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
    
    public int getScore() {
        return score; // Provide score for GameOver screen
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
