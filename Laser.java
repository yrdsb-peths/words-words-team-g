import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Laser extends Actor
{
    private GreenfootImage laserImage;
    Enemy target;
    
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
    
    public void act()
    {
        // Add your action code here.
        turnTowards(target.getX(), target.getY());
        move(10);
        checkTouching();
    }
    
    public void checkTouching()
    {
        if(this.getX() < target.getX() + 10 && this.getX() > target.getX() - 10 && this.getY() < target.getY() + 10 && this.getY() > target.getY() - 10)
        {
            getWorld().removeObject(this);
        }
    }
}
