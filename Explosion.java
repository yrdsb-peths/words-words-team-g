import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Explosion extends Actor {
  private GreenfootImage[] explosionImage;
  private int totalframe = 10;
  private int currentframe = 0;
  Enemy initialEnemy;
  SimpleTimer animationTimer = new SimpleTimer();

  public Explosion(Enemy enemy) {
    loadimage();
    setImage(explosionImage[0]);
    animationTimer.mark();
    initialEnemy = enemy;
  }

  public void act() {
    animateimage();
  }

  public void checkTouching() {
    if(getIntersectingObjects(Enemy.class).size() > 1) {
      for(Enemy enemy : getIntersectingObjects(Enemy.class)) {
        if(!enemy.equals(initialEnemy)) {
          subtracting(enemy);
        }
      }
    }
  }

  public void subtracting(Enemy enemy) {
    if(!enemy.equals(initialEnemy)) {
      Game game = (Game) getWorld();
      game.subtractLetter(enemy);
      if(enemy != null) { // subtracts two letters
        game.subtractLetter(enemy);
      }
    }
  }

  public void loadimage() {
    explosionImage = new GreenfootImage[totalframe];
    for (int i = 0; i < explosionImage.length; i++) {
      explosionImage[i] = new GreenfootImage("images/ExplosionAnimation/tile00" + i + ".png");
      explosionImage[i].scale(150, 150);
    }
  }

  public void animateimage() {
    if(animationTimer.millisElapsed() < 40)
    {
        return;
    }
    animationTimer.mark();
    if (currentframe < explosionImage.length) {
      setImage(explosionImage[currentframe]);
      if(currentframe == 0) {
        checkTouching();
      }
      currentframe++;
    }
    else{
      getWorld().removeObject(this);
    }
  }

}
