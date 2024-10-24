import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Game extends World
{
    private GreenfootSound gameMusic;
    private int userShipX, userShipY;

    public Game(int difficulty)
    {    
        //creating new world
        super(500, 700, 1);
        setBackground(new GreenfootImage("Background.jpg"));
        
        //music
        gameMusic = new GreenfootSound("GameMusic.mp3");  
        gameMusic.setVolume(50);
        gameMusic.playLoop();
        
        //mainship
        userShipX = 250;
        userShipY = 600;
        MainShip userShip = new MainShip(2);
        addObject(userShip, userShipX, userShipY);
    }

    public void makeWord() { // placeholder for making stuff fall down
        Enemy enemy = new Enemy(userShipX, userShipY);
        Label label = new Label("test", 20);
        label.setLineColor(Color.RED);
        int  randomNum = Greenfoot.getRandomNumber(500);
        enemy.setStartX(randomNum);
        addObject(enemy, randomNum, 0);
        addObject(label, randomNum, 0);
        enemy.setLabel(label);
    }

    public void act() { // press W key to make stuff fall down
        if(Greenfoot.isKeyDown("W"))
        {
            makeWord();
        }
    }
    
    public void started() {
        // Ensure the music resumes when the world starts
        gameMusic.playLoop();
    }
    
    public void stopped() {
        // Pause the music when the world is stopped
        gameMusic.pause();
    }
}
