import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MenuScreen extends World
{    
    //Menu music
    private GreenfootSound menuMusic;
    private Label instructionLabel;
    
    //menu of buttons
    public MenuScreen()
    {    
        
        //size of world
        super(500, 700, 1);
        setBackground(new GreenfootImage("Background.jpg"));
        
        //setting music
        menuMusic = new GreenfootSound("MenuScreenMusic.mp3");  
        
        //adding buttons
        addObject(new Button(this::goInstructions, "Instructions"), 250, 360);
        addObject(new Button(this::goHighScores, "High Scores"), 250, 310); 
        addObject(new Button(this::goDifficulty, "Start Game"), 250, 260);
        addObject(new Button(this::goCharacterSelection, "Character selection"), 250, 410);
        
        //changing volume and allowing a loop play
        menuMusic.setVolume(10);
        menuMusic.playLoop();
        menuMusic.pause();
        
        //adding instructions
        instructionLabel = new Label("Please select a character to begin!", 30);
        
        // Add the instruction label to the screen
        addObject(instructionLabel, 250, 200);

    }
    
    //going to intruction screen
    public void goInstructions() {
        Greenfoot.setWorld(new InstructionScreen(this));
    }

    //going to set difficulty
    public void goDifficulty() {
        menuMusic.pause();
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