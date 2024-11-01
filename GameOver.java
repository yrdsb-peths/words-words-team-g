import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class GameOver extends World {
    public static ArrayList<NameScore> UserNames = new ArrayList<>();
    private MenuScreen menuScreen;
    private String ending = "GameOver";
    private String userName = "";
    private Label input;
    private boolean inputAccepted = true;

    public GameOver() {
        super(500, 700, 1);
        // Initialize menuScreen to prevent NullPointerException
        menuScreen = new MenuScreen();
        setBackground(new GreenfootImage("Background.jpg"));
        DisplayGameOver();
        addObject(new Button(this::goMenuScreen, "Menu"), 250, 600);
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
            if (key.equals("enter") && !userName.trim().isEmpty()) {  // Check for empty name
                Random ran = new Random();
                int testValue = ran.nextInt(10);
                NameScore playerinfo = new NameScore(userName, testValue);
                UserNames.add(playerinfo);

                Label nameLabel = new Label("Player: " + userName, 40);
                Label nameLabel2 = new Label("Score: " + playerinfo.getScores(), 40);

                addObject(nameLabel, 250, 250);
                addObject(nameLabel2, 250, 300);

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