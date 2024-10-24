import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Explosion extends Actor {
  private GreenfootImage[] explosionImage;
  private int totalframe = 10;
  private int currentframe = 0;
  SimpleTimer animationTimer = new SimpleTimer();
  

  public Explosion() {
    loadimage();
    setImage(explosionImage[0]);
    animationTimer.mark();
  }

  public void act() {
    animateimage();
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
      currentframe++;
    }
    else{
      getWorld().removeObject(this);
    }
  }

}
