import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Enemy extends Actor
{   
    int toX, toY;
    SimpleTimer moveTimer = new SimpleTimer();
    Label label;
    
    public Enemy(int toX, int toY) { //Sets image
        GreenfootImage enemyShip = new GreenfootImage("EnemySpaceship.png");
        enemyShip.scale(70, 60);
        setImage(enemyShip);
        this.toX = toX;
        this.toY = toY;
        label = new Label("test", 30);
        moveTimer.mark();
    }

    public void act() // Stuff falls down
    {
        if(getY()<toY)
        {
            turnTowards(toX, toY);
        }
        moveEnemy();
        if(isTouching(MainShip.class))
        {
            getWorld().removeObject(label);
            Game game = (Game) getWorld();
            game.enemyHolder.remove(this);
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
