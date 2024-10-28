import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MainShip extends Actor
{
    public MainShip(int spaceShip)
    {
        GreenfootImage spaceShipImage = new GreenfootImage("Spaceship" + spaceShip + ".png");
        if(spaceShip == 1)
        {
            spaceShipImage.scale(70, 140);
        }
        else if(spaceShip == 2)
        {
            spaceShipImage.scale(95, 110);
        }
        else
        {
            spaceShipImage.scale(100, 85);
        }
        setImage(spaceShipImage); 
    }
    
    public void act()
    {
        
    }
    
    public void turnToEnemy(Enemy enemy)
    {
        turnTowards(enemy.getX(), enemy.getY());
    }
}
