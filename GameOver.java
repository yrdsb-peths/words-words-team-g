import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class GameOver extends World {
    public static ArrayList<NameScore> UserNames = new ArrayList<>();
    private MenuScreen menuScreen;
    private String ending = "GameOver";
    private String userName = "";
    private Label input;
    private boolean inputAccepted = true;
    private int finalScore;

    public GameOver(int finalScore) {
        super(500, 700, 1);
        this.finalScore = finalScore;
        menuScreen = new MenuScreen();
        setBackground(new GreenfootImage("Background.jpg"));
        DisplayGameOver();

        addObject(new Button(this::goMenuScreen, "Menu"), 250, 600);
        
        Label scoreDisplay = new Label("Final Score: " + finalScore, 40); // Display final score
        addObject(scoreDisplay, 250, 290);
    }

    public void act() {
        requestName();
    }

    public void DisplayGameOver() {
        Label end = new Label(ending, 40);
        addObject(end, 250, 100);

        Label name = new Label("Enter your name: ", 40);
        addObject(name, 250, 150);

        input = new Label("", 40);
        addObject(input, 250, 200);
    }

    public void requestName() {
        if (!inputAccepted) return;

        String key = Greenfoot.getKey();
        if (key != null) {
            if (key.equals("enter") && !userName.trim().isEmpty()) {
                NameScore playerInfo = new NameScore(userName, finalScore); // Use final score
                UserNames.add(playerInfo);

                // Displaying player and score labels in the GameOver screen
                Label nameLabel = new Label("Player: " + userName, 40);

                addObject(nameLabel, 250, 250);
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

    public void goMenuScreen() {
        Greenfoot.setWorld(menuScreen);
    }
}