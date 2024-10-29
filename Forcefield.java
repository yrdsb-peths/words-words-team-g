import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashSet;
import java.util.Set;
public class Forcefield extends Actor
{
    SimpleTimer animationTimer = new SimpleTimer();
    GreenfootImage forcefieldImage;
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
        animationTimer.mark();
        forcefieldImage = new GreenfootImage("Forcefield.png");
        width += 20;
        height += 20;
        forcefieldImage.scale(width,height);
        setImage(forcefieldImage);
        if(forcefieldImage.getHeight()/2 > getWorld().getHeight())
        {
            getWorld().removeObject(this);
        }
    }
    
    public void deleteEnemy()
    {
        if(isTouching(Enemy.class))
        {
            Enemy touchingEnemy = (Enemy)getOneIntersectingObject(Enemy.class);
            touchingEnemy.removeEnemy();
        }
    }
}
