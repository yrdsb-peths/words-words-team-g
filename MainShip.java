import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MainShip extends Actor
{
    int whichShip;
    Enemy target = null;
    Label jammed = new Label("Jammed", 20);
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
        jammed.setLineColor(Color.YELLOW);
    }
    
    public void act()
    {
        if(target != null)
        {
            turnToEnemy(target);
        }
        else
        {
            turnTowards(this.getX(), 0);
        }
    }
    
    public void turnToEnemy(Enemy enemy)
    {
        turnTowards(enemy.getX(), enemy.getY());
    }
    
    public void showJammed()
    {
        getWorld().addObject(jammed, this.getX(), this.getY());
    }
    
    public void removeJammed()
    {
        getWorld().removeObject(jammed);
    }
}
