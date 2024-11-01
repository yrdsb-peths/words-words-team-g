import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class GameOver extends World {
  public static ArrayList<String> UserNames = new ArrayList<>();
  private World menuScreen;
  private String ending = "GameOver";
  private String userName = "";
  private Label input;


  private boolean inputAccepted = true;

  public GameOver(World menuScreen) {
    super(500, 700, 1);
    setBackground(new GreenfootImage("Background.jpg"));

    this.menuScreen = menuScreen;

    DisplayGameOver();

    addObject(new Button(this::goMenuScreen, "Menu"), 250, 300);
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
      if (key.equals("enter")) {
        UserNames.add(userName);
        Random ran = new Random();
        int testValue = ran.nextInt(10);
        NameScore playerinfo = new NameScore(userName, testValue);

        Label nameLabel = new Label("Player: " + userName, 30);
        Label nameLabel2 = new Label("Playerinfo: " + playerinfo.getName() + playerinfo.getScores(), 30);

        addObject(nameLabel, 250, 250);
        addObject(nameLabel2, 250, 400);

        inputAccepted = false;
      } else if (key.equals("backspace")) {
        if (userName.length() > 0) {
          userName = userName.substring(0, userName.length() - 1);
        }
      } else if (key.length() == 1) {
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
