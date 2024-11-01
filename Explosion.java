import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Explosion extends Actor {
  private GreenfootImage[] explosionImage;
  private int totalframe = 10;
  private int currentframe = 0;
  Enemy initialEnemy;
  SimpleTimer animationTimer = new SimpleTimer();
  Enemy[] surroundingEnemies;

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
    if(isTouching(Enemy.class)) {
      int numTouching = getIntersectingObjects(Enemy.class).size() - 1; // not including self
      if(numTouching == 0) {
        return;
      }
      surroundingEnemies = new Enemy[numTouching];
      for(int i = 0; i < numTouching * 3; i++) {
        subtracting();
      }
      surroundingEnemies = null;
    }
  }

  public void subtracting() {
    Enemy enemy = (Enemy) getOneIntersectingObject(Enemy.class);
    int elements = 0;
    for(int i = 0; i < surroundingEnemies.length; i++) {
      if(surroundingEnemies[i] != null) {
        elements += 1;
        if(surroundingEnemies[i].equals(enemy)) {
          return;
        }
      }
    }

    if(!enemy.equals(initialEnemy)) {
      Game game = (Game) getWorld();
      game.subtractLetter(enemy);
      if(enemy != null) { // subtracts two letters
        game.subtractLetter(enemy);
      }
    }
    surroundingEnemies[elements] = enemy;
  }

  public void loadimage() {
    explosionImage = new GreenfootImage[totalframe];
    for (int i = 0; i < explosionImage.length; i++) {
      explosionImage[i] = new GreenfootImage("images/ExplosionAnimation/tile00" + i + ".png");
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
      if(currentframe == 4) { // prints twice
        checkTouching();
      }
      currentframe++;
    }
    else{
      getWorld().removeObject(this);
    }
  }

}
