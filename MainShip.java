import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MainShip extends Actor
{
    public MainShip(int spaceShip)
    {
        GreenfootImage spaceShipImage = new GreenfootImage("Spaceship" + spaceShip + ".png");
        if(spaceShip == 1)
        {
            spaceShipImage.scale(150, 50);
        }
        else if(spaceShip == 2)
        {
            spaceShipImage.scale(180, 130);
        }
        else
        {
            spaceShipImage.scale(80, 100);
        }
        
        setImage(spaceShipImage); 
    }
    
    public void act()
    {
        
    }
}
