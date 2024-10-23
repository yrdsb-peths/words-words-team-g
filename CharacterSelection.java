import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class CharacterSelection extends World
{
    private World menuScreen;
    private int index = 0;
    private GreenfootImage[] characters;
    
    public CharacterSelection()
    {    
        super(500, 700, 1);
        setBackground(new GreenfootImage("Background.jpg"));
        
        // Load character images into an array
        characters = new GreenfootImage[] {
            new GreenfootImage("Spaceship1.png"),
            new GreenfootImage("Spaceship2.png"),
            new GreenfootImage("Spaceship3.png")
        };
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
        setBackground(characters[index]); // Update the background with the current character image
    }
}
