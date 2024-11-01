import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class HighScore extends World
{
    private MenuScreen menuScreen;

    public HighScore(MenuScreen menuScreen)
    {    
        super(500, 700, 1);
        setBackground(new GreenfootImage("Background.jpg"));
        
        this.menuScreen = menuScreen;
        
        addObject(new Button(this::goMenuScreen, "Menu"), 250,  + 600);

        displayHighScores();
    }

    public void displayHighScores() {
        int yint = 150;
        Label title = new Label("High Score:", 50);
        addObject(title, 250, 90);
    
        for (NameScore playerinfo : GameOver.UserNames) {
          
    
          Label scoreLabel = new Label(playerinfo.getName() + ": " + playerinfo.getScores(), 40);
          addObject(scoreLabel, getWidth() / 2, yint);
    
          yint += 50;
        }
      }

    public void goMenuScreen() {
        Greenfoot.setWorld(menuScreen);
    }
}
