import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MainShip extends Actor
{
    public MainShip(int spaceShip)
    {
        GreenfootImage spaceShipImage = new GreenfootImage("Spaceship" + spaceShip + ".png");
        if(spaceShip == 1)
        {
            spaceShipImage.scale(50, 150);
        }
        else if(spaceShip == 2)
        {
            spaceShipImage.scale(130, 180);
        }
        else
        {
            spaceShipImage.scale(100, 80);
        }
        setImage(spaceShipImage); 
    }
    
    public void act()
    {
        
    }
    
    public void turnTowards(Enemy enemy)
    {
        
    }
}
