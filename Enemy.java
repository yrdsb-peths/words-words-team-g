import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Enemy extends Actor
{   
    int toX, toY;
    int speed;
    boolean isRemoved = true;
    SimpleTimer moveTimer = new SimpleTimer();
    SimpleTimer invincibleFrames = new SimpleTimer();
    Label label;
    String originalWord;
    public Enemy(int toX, int toY, int speed) { //Sets image
        GreenfootImage enemyShip = new GreenfootImage("EnemySpaceship.png");
        enemyShip.scale(70, 60);
        setImage(enemyShip);
        this.toX = toX;
        this.toY = toY;
        this.speed = speed;
        moveTimer.mark();
        invincibleFrames.mark();
        label = new Label("test", 30);
    }

    public void act()
    {
        isRemoved = false;
        if (getY() < toY) {
            turnTowards(toX, toY);
        }
        moveEnemy();
    }

    public void moveEnemy()
    {
        if (moveTimer.millisElapsed() < 40) {
            return;
        }
        moveTimer.mark();
        move(speed); // Move enemy by the speed value
        label.setLocation(getX(), getY());
    }
    
    public void removeEnemy()
    {
        Game game = (Game) getWorld();
        game.removeObject(label);
        game.removeFromMap(this);
        game.removeObject(this);
        game.score += game.scoreMultiplier; // Increment score when an enemy is destroyed
        game.scoreLabel.setValue("Score: " + game.score); // Update score label
        isRemoved = true;
    }
    
    public double distanceFrom()
    {
        double distanceX = toX - this.getX();
        double distanceY = toY - this.getY();
        return Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
    }
}
