import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class CharacterSelection extends World
{
    private World menuScreen;
    private int index = 0;
    private GreenfootImage[] characters;
    private CharacterDisplay characterDisplay; 
    public CharacterSelection()
    {    
        
        super(500, 700, 1);
        setBackground(new GreenfootImage("Background.jpg"));
        
        GreenfootImage spaceShip1 = new GreenfootImage("Spaceship1.png");
        spaceShip1.scale(180, 60);
        
        GreenfootImage spaceShip2 = new GreenfootImage("Spaceship2.png");
        spaceShip2.scale(200, 150);
        
        GreenfootImage spaceShip3 = new GreenfootImage("Spaceship3.png");
        spaceShip3.scale(100, 120);
        
        characters = new GreenfootImage[] { spaceShip1, spaceShip2, spaceShip3 };
        characterDisplay = new CharacterDisplay(characters[index]);
        addObject(characterDisplay, 250, 500);
        // Add Next and Previous buttons
        addObject(new Button(this::nextCharacter, "Next"), 250, 300);
        addObject(new Button(this::previousCharacter, "Previous"), 250, 350);
        // Set the initial character image
        updateCharacterImage();
    }
    
    
     public void nextCharacter() {
        if (index < characters.length - 1) {
            index++;
            updateCharacterImage();
        }
    }

    public void previousCharacter() {
        if (index > 0) {
            index--;
            updateCharacterImage();
        }
    }
    private void updateCharacterImage() {
        characterDisplay.setImage(characters[index]);  // Set the image of the actor to the current character image
    }
}
