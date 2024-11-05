import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MenuScreen extends World
{    
    //Menu music
    GreenfootSound menuMusic;
    private Label instructionLabel;
    public static int whichShip = 1;
    CharacterDisplay characterDisplay;
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
        
        // Title label
        Label titleLabel = new Label("Word Fighters", 80);
        titleLabel.setFillColor(Color.ORANGE);
        titleLabel.setLineColor(Color.RED);
        addObject(titleLabel, getWidth()/2, 100);
        
        // Resizing and adding mini spaceship in corner of screen
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
        characterDisplay = new CharacterDisplay(spaceShipImage);
        addObject(characterDisplay, getWidth()-50, getHeight()-50);
        characterDisplay.turnTowards(characterDisplay.getX(), 0);
    }
    
    //Updates the character image
    public void updateImage()
    {
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
        characterDisplay.setImage(spaceShipImage);
    }
    
    //going to intruction screen
    public void goInstructions() {
        Greenfoot.setWorld(new InstructionScreen(this));
    }

    //going to set difficulty
    public void goDifficulty() {
        Greenfoot.setWorld(new ModeScreen(this));
    }
    
    // going to high scores
    public void goHighScores() {
        Greenfoot.setWorld(new HighScore(this));
    }
    
    // going to character selection
    public void goCharacterSelection() {
        Greenfoot.setWorld(new CharacterSelection(this));
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