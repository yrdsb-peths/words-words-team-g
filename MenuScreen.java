import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MenuScreen extends World
{    
    //Menu music
    GreenfootSound menuMusic;
    private Label instructionLabel;
    public static int whichShip = 1;
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
        addObject(new Button(this::goGameOver, "GameOver"), 250, 460);

        //changing volume and allowing a loop play
        menuMusic.setVolume(50);
        menuMusic.playLoop();
        menuMusic.pause();
        
        //adding instructions
        instructionLabel = new Label("Please select a character to begin!", 30);
        Color OFF_WHITE = new Color(251, 247, 245);
        instructionLabel.setLineColor(OFF_WHITE);
        instructionLabel.setFillColor(OFF_WHITE);
        // Add the instruction label to the screen
        addObject(instructionLabel, 250, 200);
        
        GreenfootImage spaceShipImage = new GreenfootImage("Spaceship" + whichShip + ".png");
        if(whichShip == 1)
        {
            spaceShipImage.scale(40, 80);
        }
        else if(whichShip == 2)
        {
            spaceShipImage.scale(40, 50);
        }
        else
        {
            spaceShipImage.scale(70, 55);
        }
        CharacterDisplay display = new CharacterDisplay(spaceShipImage);
        addObject(display, getWidth()-50, getHeight()-50);
        display.turnTowards(display.getX(), 0);
    }
    
    //going to intruction screen
    public void goInstructions() {
        Greenfoot.setWorld(new InstructionScreen(this));
    }

    //going to set difficulty
    public void goDifficulty() {
        Greenfoot.setWorld(new ModeScreen(this));
    }
    
    public void goHighScores() {
        Greenfoot.setWorld(new HighScore(this));
    }
    
    public void goCharacterSelection() {
        Greenfoot.setWorld(new CharacterSelection(this));
    }

    public void goGameOver() {
        Greenfoot.setWorld(new GameOver(this));
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