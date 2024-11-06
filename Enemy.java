import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Enemy extends Actor
{   
    int toX, toY; //Coordinates for where the enemy is going to go towards.
    int speed;
    boolean isRemoved = true; 
    SimpleTimer moveTimer = new SimpleTimer();
    Label label;
    String originalWord;
    
    /**
     * This is a constructor for the enemy class that sets up the variables needed for the class and assigns an image and label to the object.
     */
    public Enemy(int toX, int toY, int speed) { //Sets image
        GreenfootImage enemyShip = new GreenfootImage("EnemySpaceship.png");
        enemyShip.scale(70, 60);
        setImage(enemyShip);
        this.toX = toX;
        this.toY = toY;
        this.speed = speed;
        moveTimer.mark();
        label = new Label("test", 30);
        label.setLineColor(Color.WHITE);
    }

    /**
     * Act method to continously run code.
     */
    public void act()
    {
        isRemoved = false;
        if (getY() < toY) {
            turnTowards(toX, toY);
        }
        moveEnemy();
    }

    /**
     * This method makes it so every 40 milliseconds enemy and assigned label will move the given speed.
     */
    public void moveEnemy()
    {
        if (moveTimer.millisElapsed() < 40) {
            return;
        }
        moveTimer.mark();
        move(speed); // Move enemy by the speed value
        label.setLocation(getX(), getY());
    }
    
    /**
     * This method will remove the enemy from the world and change the Game world variables so that the game recognizes that the enemy no longer exists.
     * It also increases the score.
     */
    public void removeEnemy()
    {
        Game game = (Game) getWorld();
        /*
         * Because there is multiple ways for this method to be called, need to check if the object still exists in the world so code isn't run when
         * object is no longer present in the world.
         */
        if(isRemoved == false)
        {
            game.removeObject(label);
            game.removeFromMap(this);
            game.removeObject(this);
            game.score += game.scoreMultiplier; // Increment score when an enemy is destroyed
            game.scoreLabel.setValue("Score: " + game.score); // Update score label
            isRemoved = true;
        }
    }
    
    /**
     * Returns the distance the enemy is from the given coordinates.
     */
    public double distanceFrom()
    {
        double distanceX = toX - this.getX();
        double distanceY = toY - this.getY();
        return Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
    }
}
