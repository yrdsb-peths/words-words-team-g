import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Laser extends Actor
{
    private GreenfootImage laserImage;
    Enemy target;
    
    /**
     * This is a constructor that sets up the image and the target for the class.
     */
    public Laser(boolean doubleLetters, Enemy target)
    {
        if(doubleLetters == true)
        {
            laserImage = new GreenfootImage("images/LaserShotBuffed.png");
            laserImage.scale(90, 30);
        }
        else
        {
            laserImage = new GreenfootImage("images/LaserShot.png");
            laserImage.scale(60, 20);
        }
        setImage(laserImage);
        this.target = target;
    }
    
    /**
     * This method will make the object continously move towards the target until they are touching. If the target is already destroyed, the laser will
     * disapear.
     */
     public void act()
    {
        if(target.isRemoved == true)
        {
            getWorld().removeObject(this);
        }
        else
        {
            turnTowards(target.getX(), target.getY());
            move(20);
            checkTouching();
        }
    }
    
    /**
     * Checks if the object is touching the target. If it is, then it will create an explosion and remove itself and the target from the world.
     */
    public void checkTouching()
    {
        if(this.getX() < target.getX() + 20 && this.getX() > target.getX() - 20 && this.getY() < target.getY() + 20 && this.getY() > target.getY() - 20)
        {
            makeExplosion();
            getWorld().removeObject(this);
            target.removeEnemy();
        }
    }
    
    /**
     * Makes an explosion on the target.
     */
    public void makeExplosion() {
        getWorld().addObject(new Explosion(target), target.getX(), target.getY());
    }
}
