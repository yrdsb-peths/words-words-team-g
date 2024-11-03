import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.*;

public class Forcefield extends Actor
{
    SimpleTimer animationTimer = new SimpleTimer();
    GreenfootImage forcefieldImage;
    private GreenfootSound ShieldBlast;
    int width = 20;
    int height = 20;
    public Forcefield()
    {
        forcefieldImage = new GreenfootImage("Forcefield.png");
        animationTimer.mark();
        forcefieldImage.scale(width,height);
        setImage(forcefieldImage);
    }
    
    public void act()
    {
        deleteEnemy();
        animate();
    }
    
    public void animate()
    {
        if(animationTimer.millisElapsed() < 5)
        {
            return;
        }
        ShieldBlast = new GreenfootSound("ShieldBlast.mp3");
        animationTimer.mark();
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
