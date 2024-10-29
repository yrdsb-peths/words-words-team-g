import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Enemy extends Actor
{   
    int toX, toY;
    SimpleTimer moveTimer = new SimpleTimer();
    Label label;
    String originalWord;
    
    public Enemy(int toX, int toY) { //Sets image
        GreenfootImage enemyShip = new GreenfootImage("EnemySpaceship.png");
        enemyShip.scale(70, 60);
        setImage(enemyShip);
        this.toX = toX;
        this.toY = toY;
        moveTimer.mark();
        label = new Label("test", 30);
    }

    public void act()
    {
        if(getY()<toY)
        {
            turnTowards(toX, toY);
        }
        moveEnemy();
        if(isTouching(MainShip.class)) // remove if touching ship
        {
            getWorld().removeObject(label);
            Game game = (Game) getWorld();
            game.currentWord = null;
            game.removeFromMap(this);
            game.removeObject(label);
            game.removeObject(this);
        }
    }

    public void moveEnemy()
    {
        if(moveTimer.millisElapsed() < 40)
        {
            return;
        }
        moveTimer.mark(); // move enemy and label
        move(2);
        label.setLocation(getX(), getY());
    }
}
