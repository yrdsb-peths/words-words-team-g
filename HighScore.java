import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Map;
import java.util.HashMap;

public class HighScore extends World
{
    private Map<String, Integer> highScores;
    private World menuScreen;

    public HighScore(World menuScreen)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(500, 700, 1);
        this.menuScreen = menuScreen;

        highScores = new HashMap<>();
        
        addObject(new Button(this::goMenuScreen, "Menu"), 250,  + 300);
    }

    public void displayHighScores() {
        int yint = 150;
        Label title = new Label("High Score:", 40);
        addObject(title, 300, 100);
    
        for (Map.Entry<String, Integer> entry : highScores.entrySet()) {
          String key = entry.getKey();
          int value = entry.getValue();
    
          Label scoreLabel = new Label(key + ": " + value, 40);
          addObject(scoreLabel, getWidth() / 2, yint);
    
          yint += 50;
        }
      }

    public void goMenuScreen() {
        Greenfoot.setWorld(menuScreen);
    }
}
