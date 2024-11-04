import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class GameOver extends World {
    private static final int MAX_SCORES = 5;
    public static ArrayList<NameScore> UserNames = new ArrayList<>();
    private MenuScreen menuScreen;
    private HighScore highScore;
    private String ending = "GameOver";
    private String userName = "";
    private Label input;
    private boolean inputAccepted = true;
    private int finalScore;
    GreenfootSound GameOverMusic;
    GreenfootSound YouLose;

    public GameOver(int finalScore) {
        super(500, 700, 1);
        this.finalScore = finalScore;
        menuScreen = new MenuScreen();
        highScore = new HighScore(menuScreen);
        setBackground(new GreenfootImage("Background.jpg"));
        
        DisplayGameOver();

        addObject(new Button(this::goMenuScreen, "Menu"), 250, 600);
        
        GameOverMusic = new GreenfootSound("GameOverMusic.mp3"); 
        YouLose = new GreenfootSound("YouLose.mp3"); 
        GameOverMusic.playLoop();
        YouLose.play();
    }

    public void act() {
        requestName();
    }

    public void DisplayGameOver() {
        Label end = new Label(ending, 60);
        addObject(end, 250, 70);

        Label scoreDisplay = new Label("Final Score: " + finalScore, 35);
        addObject(scoreDisplay, 250, 120);

        Label name = new Label("Enter your name: ", 40);
        addObject(name, 250, 200);

        input = new Label("", 40);
        addObject(input, 250, 250);
    }

    public void requestName() {
        if (!inputAccepted) return;

        String key = Greenfoot.getKey();
        if (key != null) {
            if (key.equals("enter") && !userName.trim().isEmpty()) {
                NameScore playerInfo = new NameScore(userName, finalScore); // Use final score
                addHighScore(playerInfo);
                Label saved = new Label("Score has been saved", 40);
                addObject(saved, 250, 300);

                inputAccepted = false;
            } else if (key.equals("backspace")) {
                if (userName.length() > 0) {
                    userName = userName.substring(0, userName.length() - 1);
                }
            } else if (key.length() == 1 && userName.length() < 20) {  // Add character limit
                // Check for shift and add character accordingly
                if (Greenfoot.isKeyDown("shift")) {
                    key = key.toUpperCase();
                }
                userName += key;
            }
            input.setValue(userName);
        }
    }

    private void addHighScore(NameScore newScore) {
        UserNames.add(newScore);
        Collections.sort(UserNames);  // Sort in descending order
        
        // Keep only top 10 scores
        if (UserNames.size() > MAX_SCORES) {
            UserNames = new ArrayList<>(UserNames.subList(0, MAX_SCORES));
        }
    }

    public void goMenuScreen() {
        menuScreen.started();
        Greenfoot.setWorld(menuScreen);
    }

    public void goHighScoreScreen() {
        Greenfoot.setWorld(highScore);
    }
    
    public void started() {
        // Ensure the music resumes when the world starts
        GameOverMusic.playLoop();
    }
    
    public void stopped() {
        // Pause the music when the world is stopped
        YouLose.pause();
        GameOverMusic.pause();
    }
}