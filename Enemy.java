import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Enemy extends Actor
{   
    int toX, toY;
    int speed;
    SimpleTimer moveTimer = new SimpleTimer();
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
        label = new Label("test", 30);
    }

    public void act()
    {
        if (getY() < toY) {
            turnTowards(toX, toY);
        }
        moveEnemy();
        checkTouching();
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
        game.score++; // Increment score when an enemy is destroyed
        game.scoreLabel.setValue("Score: " + game.score); // Update score label
    }
    
    public void checkTouching() {
        if (isTouching(MainShip.class)) { // remove if touching ship
            MainShip ship = (MainShip) getOneIntersectingObject(MainShip.class);
            Game game = (Game) getWorld();
            if (game.hasForcefield) {
                Forcefield forcefield = new Forcefield();
                getWorld().addObject(forcefield, ship.getX(), ship.getY());
                game.hasForcefield = false;
                game.currentWord = null;
            } else {
                // Pass the score to GameOver constructor
                GameOver gameover = new GameOver(game.getScore());
                Greenfoot.setWorld(gameover);
            }
            removeEnemy();
        }
    }
    
    public double distanceFrom()
    {
        double distanceX = toX - this.getX();
        double distanceY = toY - this.getY();
        return Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
    }
}
