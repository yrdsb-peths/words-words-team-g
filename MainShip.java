import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random; 

public class MainShip extends Actor
{
    int wave = 1;
    boolean clearedWave = true;
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
        createEnemies();
    }
    
    public void createEnemies()
    {
        if(clearedWave == true)
        {
            turnTowards(250, 0);
            for(int i = 0; i < wave; i++)
            {
                Random rand = new Random();
                int startX = rand.nextInt(501);
                Enemy enemy = new Enemy(250, 600, startX);
                Game gameWorld = (Game)getWorld();
                gameWorld.addObject(enemy, startX, 0);
            }
            clearedWave = false;
        }
    }
}
