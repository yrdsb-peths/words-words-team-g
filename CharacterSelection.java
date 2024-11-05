import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The CharacterSelection class allows the user to select a character avatar with different powers.
 * Users can navigate through different characters and view their unique abilities.
 */
public class CharacterSelection extends World {
    private MenuScreen menuScreen;
    private int indexShips = 0;
    private GreenfootImage[] characters;
    private CharacterDisplay characterDisplay; 
    private Label powerLabel;
    
    // Powers of each character
    private String[] powers = {
        "Reload avatar: the jammed time\ngoes down to 0.16s",
        "Explosion avatar: the explosion now\nknocks down two letters off any\nenemy caught in the explosion",
        "Forcefield avatar: User has another\nlife, when they lose their\nfirst life an energy\nshield/blast will destroy \nevery enemy on the map\n(Only happens once per game)"
    };
    
    /**
     * Constructor for CharacterSelection.
     * Initializes the world, sets the background, scales the spaceship images,
     * and adds objects for character selection navigation and power display.
     * 
     * @param menuScreen The main menu screen that the user will return to after selection.
     */
    public CharacterSelection(MenuScreen menuScreen) {    
        // Create new world with given dimensions and background
        super(500, 700, 1);
        setBackground(new GreenfootImage("Background.jpg"));
        
        this.menuScreen = menuScreen;
        
        // Scale spaceship images
        GreenfootImage spaceShip1 = new GreenfootImage("Spaceship1.png");
        spaceShip1.scale(120, 240);
        
        GreenfootImage spaceShip2 = new GreenfootImage("Spaceship2.png");
        spaceShip2.scale(140, 175);
        
        GreenfootImage spaceShip3 = new GreenfootImage("Spaceship3.png");
        spaceShip3.scale(180, 140);
        
        // Array of spaceship images for cycling when user presses next
        characters = new GreenfootImage[] { spaceShip1, spaceShip2, spaceShip3 };
        characterDisplay = new CharacterDisplay(characters[indexShips]);
        addObject(characterDisplay, 250, 150);
        characterDisplay.setRotation(-90);
        
        // Initialize and configure power label
        powerLabel = new Label(powers[indexShips], 30);
        Color offWhite = new Color(251, 247, 245);
        powerLabel.setLineColor(offWhite);
        powerLabel.setFillColor(offWhite);
        
        // Add the power label to the screen
        addObject(powerLabel, 250, 550);
        
        // Add Next and Previous buttons for character navigation and Select button for selection
        addObject(new Button(this::nextCharacter, "Next"), 250, 300);
        addObject(new Button(this::previousCharacter, "Previous"), 250, 350);
        addObject(new Button(this::goMenuScreen, "Select"), 250, 400);
        
        // Set the initial character image
        updateCharacterImage();
    }
    
    /**
     * Move to the next character in the selection array, update the displayed image and power information.
     */
    public void nextCharacter() {
        if (indexShips < characters.length - 1) {
            indexShips++;
            updateCharacterImage();
            powerLabel.setValue(powers[indexShips]);
        }
    }

    /**
     * Move to the previous character in the selection array, update the displayed image and power information.
     */
    public void previousCharacter() {
        if (indexShips > 0) {
            indexShips--;
            updateCharacterImage();
            powerLabel.setValue(powers[indexShips]);
        }
    }
    
    /**
     * Update the character display image to the current character in the selection array.
     */
    private void updateCharacterImage() {
        characterDisplay.setImage(characters[indexShips]);  
    }
    
    /**
     * Return to the main menu screen and set the selected character in the MenuScreen.
     */
    public void goMenuScreen() {
        menuScreen.whichShip = indexShips + 1;
        menuScreen.updateImage();
        Greenfoot.setWorld(menuScreen);
    }
}
