import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MainShip extends Actor
{
    int whichShip;
    Enemy target = null;
    Label jammed = new Label("Jammed", 20);
    SimpleTimer invincibleFrames = new SimpleTimer();
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
    
    public void checkTouching()
    {
         if (isTouching(Enemy.class)  && invincibleFrames.millisElapsed() > 3000) { // remove if touching ship
            Enemy enemy = (Enemy) getOneIntersectingObject(Enemy.class);
            Game game = (Game) getWorld();
            if (game.hasForcefield) {
                game.jamTimer.mark();
                game.hasForcefield = false;
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
