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
    if(isTouching(Enemy.class)) {
      Enemy enemy = (Enemy) getOneIntersectingObject(Enemy.class);
      if(!enemy.equals(initialEnemy)) {
        Game game = (Game) getWorld();
        game.subtractLetter(enemy);
        if(enemy != null) {
          game.subtractLetter(enemy);
        }
      }
    }
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
      if(currentframe == 0) { // prints twice
        checkTouching();
        System.out.println(1);
      }
      currentframe++;
    }
    else{
      getWorld().removeObject(this);
    }
  }

}
