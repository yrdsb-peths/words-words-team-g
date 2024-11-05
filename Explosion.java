import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

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
    initialEnemy = enemy; // Enemy explosion is from
  }

  public void act() {
    animateimage();
  }

  public void checkTouching() {
    if(isTouching(Enemy.class)) { 
        List<Enemy> a = getIntersectingObjects(Enemy.class);

        for(Enemy enemy : a) { // Get all enemies touching
            if(!enemy.equals(initialEnemy)) {
              subtracting(enemy);
            }
        }
    }
  }

  public void subtracting(Enemy enemy) {
    if(!enemy.equals(initialEnemy)) { // Explosion only affects words around original word
      Game game = (Game) getWorld();
      game.subtractLetter(enemy);
      if(enemy != null) { // subtracts two letters
          game.subtractLetter(enemy);
      }
    }
  }

  // Loads and scales frames of explosion
  public void loadimage() {
    explosionImage = new GreenfootImage[totalframe];
    for (int i = 0; i < explosionImage.length; i++) {
        explosionImage[i] = new GreenfootImage("images/ExplosionAnimation/tile00" + i + ".png");
        explosionImage[i].scale(150, 150);
    }
  }

  // Explosion animation
  public void animateimage() {
    if(animationTimer.millisElapsed() < 40)
    {
        return;
    }
    animationTimer.mark();
    if (currentframe < explosionImage.length) {
        setImage(explosionImage[currentframe]);
        if(currentframe == 0) { // Check for touching words only once
            checkTouching();
        }
        currentframe++;
    }
    else{ // Remove when animation is over
        getWorld().removeObject(this);
    }
  }

}
