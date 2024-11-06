import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class Forcefield extends Actor
{
    SimpleTimer animationTimer = new SimpleTimer();
    GreenfootImage forcefieldImage;
    private GreenfootSound ShieldBlast;
    int width = 20;
    int height = 20;
    /**
     * Constructor that sets up all the variables for the class.
     */
    public Forcefield()
    {
        forcefieldImage = new GreenfootImage("Forcefield.png");
        animationTimer.mark();
        forcefieldImage.scale(width,height);
        setImage(forcefieldImage);
    }
    
    /**
     * Will continously delete any enemy it is touching and will get larger.
     */
    public void act()
    {
        deleteEnemy();
        animate();
    }
    
    /**
     * Every 5 milliseconds, the object will constantly get bigger, until it is twice the size of the height of the world. Then it will remove itself
     * from the world. Also plays a sound when triggered.
     */
    public void animate()
    {
        if(animationTimer.millisElapsed() < 5)
        {
            return;
        }
        ShieldBlast = new GreenfootSound("ShieldBlast.mp3");
        animationTimer.mark();
        /*
         * Need to create a new image each time it grows bigger because if you start small and scale image bigger, it will lose pixels and the image
         * will look werid.
         */
        forcefieldImage = new GreenfootImage("Forcefield.png");
        width += 20;
        height += 20;
        forcefieldImage.scale(width,height);
        setImage(forcefieldImage);
        ShieldBlast.setVolume(20);
        ShieldBlast.play();
        if(forcefieldImage.getHeight()/2 > getWorld().getHeight())
        {
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Gets a list of every enemy that the forcefield is touching and deletes them.
     */
    public void deleteEnemy()
    {
        if(isTouching(Enemy.class))
        {
            List<Enemy> a = getIntersectingObjects(Enemy.class);

            for(Enemy enemy : a) { // Get all enemies touching 
                enemy.removeEnemy();
            }
        }
    }
}
