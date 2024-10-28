import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class CharacterSelection extends World
{
    private World menuScreen;
    private int indexShips = 0;
    private GreenfootImage[] characters;
    private CharacterDisplay characterDisplay; 
    private Label powerLabel;
    
    //powers of each character
    private String[] powers = {
        "Reload avatar: the jammed time\ngoes down to 0.5s",
        "Explosion avatar: the explosion now\nknocks down two letters off any\nenemy caught in the explosion",
        "Forcefield avatar: User has another\nlife, whenthey lose their\nfirst life an energy\nshield/blast will destroy \nevery enemy on the map\n(Only happens once per game)"
    };
    
    public CharacterSelection()
    {    
        //new world with given sizes and background
        super(500, 700, 1);
        setBackground(new GreenfootImage("Background.jpg"));
        
        //ship sizing 
        GreenfootImage spaceShip1 = new GreenfootImage("Spaceship1.png");
        spaceShip1.scale(120, 240);
        
        GreenfootImage spaceShip2 = new GreenfootImage("Spaceship2.png");
        spaceShip2.scale(140, 175);
        
        GreenfootImage spaceShip3 = new GreenfootImage("Spaceship3.png");
        spaceShip3.scale(180, 140);
        
        //spaceships to cycle through when user presses next
        characters = new GreenfootImage[] { spaceShip1, spaceShip2, spaceShip3 };
        characterDisplay = new CharacterDisplay(characters[indexShips]);
        addObject(characterDisplay, 250, 150);
        characterDisplay.setRotation(-90);
        
        powerLabel = new Label(powers[indexShips], 30);
        
        // Add the instruction label to the screen
        addObject(powerLabel, 250, 550);
        
        // Add Next and Previous buttons
        addObject(new Button(this::nextCharacter, "Next"), 250, 300);
        addObject(new Button(this::previousCharacter, "Previous"), 250, 350);
        addObject(new Button(this::goMenuScreen, "Select"), 250, 400);
        
        // Set the initial character image
        updateCharacterImage();
        
        
    }
    
    //going to the next character and updaring the info about ship too
     public void nextCharacter() {
        if (indexShips < characters.length - 1) {
            indexShips++;
            updateCharacterImage();
            powerLabel.setValue(powers[indexShips]);
        }
    }

    //going to the previous character and updating the info about ship too
    public void previousCharacter() {
        if (indexShips > 0) {
            indexShips--;
            updateCharacterImage();
            powerLabel.setValue(powers[indexShips]);
        }
    }
    
    private void updateCharacterImage() {
        characterDisplay.setImage(characters[indexShips]);  // Set the image of the actor to the current character image
    }
    
    //back to the menu screen
    public void goMenuScreen() {
        MenuScreen menu = new MenuScreen();
        menu.whichShip = indexShips + 1;
        Greenfoot.setWorld(new MenuScreen());
    }
}
