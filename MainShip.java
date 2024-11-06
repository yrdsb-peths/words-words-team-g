import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MainShip extends Actor
{
    int whichShip;
    Enemy target = null;
    Label jammed = new Label("Jammed", 20);
    SimpleTimer invincibleFrames = new SimpleTimer();
    
    /**
     * Constructor that sets up the ship type, and the variables that are needed for the class.
     */
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
        invincibleFrames.mark();
    }
    
    /**
     * Continously turns towards its target. If object does not have a target, then it will turn upwards. It will also constantly check if
     * the object is touching anything of the Enemy class.
     */
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
        checkTouching();
    }
    
    /**
     * Turns towards the enemy passed in the parameters.
     */
    public void turnToEnemy(Enemy enemy)
    {
        turnTowards(enemy.getX(), enemy.getY());
    }
    
    /**
     * Adds the jammed label right on top of the ship.
     */
    public void showJammed()
    {
        getWorld().addObject(jammed, this.getX(), this.getY());
    }
    
    /**
     * Adds the jammed label that is right on top of the ship.
     */
    public void removeJammed()
    {
        getWorld().removeObject(jammed);
    }
    
    /**
     * If object is touching the Enemy class and it does not have any invincible frames, the game will end unless the user still has a forcefield in 
     * which it will create a force field on top of the ship and give the ship invincible frames.
     */
    public void checkTouching()
    {
         if (isTouching(Enemy.class)  && invincibleFrames.millisElapsed() > 3000) { // remove if touching ship
            Game game = (Game) getWorld();
            if (game.hasForcefield) {
                game.jamTimer.mark();
                game.hasForcefield = false;
                //Have to set targets to null, otherwise game will error.
                target = null;
                game.currentWord = null;
                Forcefield forcefield = new Forcefield();
                getWorld().addObject(forcefield, this.getX(), this.getY());
                invincibleFrames.mark();
            } else {
                // Pass the score to GameOver constructor
                game.gameMusic.stop();
                GameOver gameover = new GameOver(game.getScore());
                Greenfoot.setWorld(gameover);
            }
        }
    }
}
