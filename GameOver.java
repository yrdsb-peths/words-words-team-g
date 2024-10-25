import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GameOver extends World {
  private World menuScreen;
  private String ending = "GameOver";
  private String userName = "";
  private Label input;

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
    String key = Greenfoot.getKey();
    if (key != null) {
      if (key.equals("enter")) {
        // Display the final username
        Label nameLabel = new Label("Player: " + userName, 30);
        addObject(nameLabel, 250, 250); // Position to avoid overlap with other labels
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

  public String getUserName() {
    return userName;
  }

  public void goMenuScreen() {
    Greenfoot.setWorld(menuScreen);
  }
}
