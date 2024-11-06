import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class Explosion extends Actor {
  private GreenfootImage[] explosionImage; //Holder for the images
  private int totalframe = 10; //Total images for the animation
  private int currentframe = 0; // Index for the explosionImage array
  Enemy initialEnemy;
  SimpleTimer animationTimer = new SimpleTimer();

  /**
   * This is a constructor that sets up all the variables needed for the explosion class.
   */
  public Explosion(Enemy enemy) {
    loadimage();
    setImage(explosionImage[0]);
    animationTimer.mark();
    initialEnemy = enemy; //Enemy explosion is created at which enemy
  }

  /**
   * This act method animates the object
   */
  public void act() {
    animateimage();
  }

  /**
   * This code checks if the explsion is touching an object of the Enemy class. If it is, it will get all objects of the Enemy class and subtract 2
   * letters from each of them.
   */
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

  /**
   * If the enemy is not the same one as the explosion was created on top of, then it will subtract 2 lettters from it.
   */
  public void subtracting(Enemy enemy) {
    if(!enemy.equals(initialEnemy)) { // Explosion only affects words around original word
      Game game = (Game) getWorld();
      game.explosionSubtract(enemy);
      if(enemy != null) { // subtracts two letters
          game.explosionSubtract(enemy);
      }
    }
  }

  /**
   * Sets all the different explosion images needed for the animations into an array.
   */
  public void loadimage() {
    explosionImage = new GreenfootImage[totalframe];
    for (int i = 0; i < explosionImage.length; i++) {
        explosionImage[i] = new GreenfootImage("images/ExplosionAnimation/tile00" + i + ".png");
        explosionImage[i].scale(150, 150);
    }
  }

  /**
   * Runs through each image of the array until the end, where the object will remove itself from the world.
   */
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
