import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MainShip extends Actor
{
    int whichShip;
    public MainShip(int spaceShip)
    {
        GreenfootImage spaceShipImage = new GreenfootImage("Spaceship" + spaceShip + ".png");
        if(spaceShip == 1)
        {
            spaceShipImage.scale(70, 140);
            whichShip = 1;
        }
        else if(spaceShip == 2)
        {
            spaceShipImage.scale(95, 110);
            whichShip = 2;
        }
        else
        {
            spaceShipImage.scale(100, 85);
            whichShip = 3;
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
