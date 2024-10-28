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
            MainShip ship = (MainShip)getOneIntersectingObject(MainShip.class);
            Game game = (Game) getWorld();
            if(game.hasForcefield == true)
            {
                Forcefield forcefield = new Forcefield();
                getWorld().addObject(forcefield, ship.getX(), ship.getY());
            }
            removeEnemy();
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
    
    public void removeEnemy()
    {
        getWorld().removeObject(label);
        Game game = (Game) getWorld();
        game.enemyHolder.remove(this);
        game.hasForcefield = false;
        getWorld().removeObject(this);
    }
}
