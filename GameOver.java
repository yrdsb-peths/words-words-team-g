import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GameOver extends World {
  private World menuScreen;
  String ending = "GameOver";

  public GameOver(World menuScreen) {
    super(500, 700, 1);
    setBackground(new GreenfootImage("Background.jpg"));

    this.menuScreen = menuScreen;

    DisplayGameOver();

    addObject(new Button(this::goMenuScreen, "Menu"), 250, 300);
  }

  public void DisplayGameOver() {
    Label end = new Label(ending, 40);
    addObject(end, 250, 100);
  }

  public void goMenuScreen() {
    Greenfoot.setWorld(menuScreen);
  }
}
