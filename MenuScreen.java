import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MenuScreen extends World
{    
    //Menu music
    private GreenfootSound menuMusic;
    
    //menu of buttons
    public MenuScreen()
    {    
        //size of world
        super(500, 700, 1);
        setBackground(new GreenfootImage("Background.jpg")); 
        menuMusic = new GreenfootSound("MenuScreenMusic.mp3");  
        addObject(new Button(this::goInstructions, "Instructions"), 250, 360);
        addObject(new Button(this::goHighScores, "High Scores"), 250, 310); 
        addObject(new Button(this::goDifficulty, "Start Game"), 250, 260);
        addObject(new Button(this::goCharacterSelection, "Character selection"), 250, 410);
        menuMusic.setVolume(0);
        menuMusic.playLoop();
        menuMusic.pause();
    }
    
    //going to intruction screen
    public void goInstructions() {
        Greenfoot.setWorld(new InstructionScreen(this));
    }
    
    //going to the game
    public void goGame() {
        Greenfoot.setWorld(new Game());
        menuMusic.pause();
    }

    //going to set difficulty
    public void goDifficulty() {
        Greenfoot.setWorld(new ModeScreen());
    }
    
    public void goHighScores() {
        Greenfoot.setWorld(new HighScore(this));
    }
    
    public void goCharacterSelection() {
        Greenfoot.setWorld(new CharacterSelection());
    }
    
    public void started() {
        // Ensure the music resumes when the world starts
        menuMusic.playLoop();
    }
    
    public void stopped() {
        // Pause the music when the world is stopped
        menuMusic.pause();
    }
}