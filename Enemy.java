import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Enemy extends Actor
{   
    int toX, toY;
    SimpleTimer moveTimer = new SimpleTimer();
    private Label label;
    
    public Enemy(int toX, int toY) { //Sets image
        GreenfootImage enemyShip = new GreenfootImage("EnemySpaceship.png");
        enemyShip.scale(60, 50);
        setImage(enemyShip);
        this.toX = toX;
        this.toY = toY;
        label = new Label("test", 50);
        moveTimer.mark();
    }

    public void act() // Stuff falls down
    {
        if(getY()<toY)
        {
            turnTowards(toX, toY);
        }
        moveEnemy();
        if(getY() > 700)
        {
            getWorld().removeObject(label);
            getWorld().removeObject(this);
        }
    }

    public void moveEnemy()
    {
        if(moveTimer.millisElapsed() < 40)
        {
            return;
        }
        moveTimer.mark();
        move(2);
        label.setLocation(getX(), getY());
    }
}
