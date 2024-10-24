import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MenuScreen extends World
{    
    public MenuScreen()
    {    
        //size of world
        super(500, 700, 1);
        setBackground(new GreenfootImage("Background.jpg")); 
        addObject(new Button(this::goInstructions, "Instructions"), 250, 360);
        addObject(new Button(this::goHighScores, "High Scores"), 250, 310); 
        addObject(new Button(this::goDifficulty, "Start Game"), 250, 260);
        addObject(new Button(this::goCharacterSelection, "Character selection"), 250, 410);
        
    }
    
    //going to intruction screen
    public void goInstructions() {
        Greenfoot.setWorld(new InstructionScreen(this));
    }
    
    //going to the game
    public void goDifficulty() {
        Greenfoot.setWorld(new ModeScreen());
    }
    
    public void goHighScores() {
        Greenfoot.setWorld(new HighScore(this));
    }
    
    public void goCharacterSelection() {
        Greenfoot.setWorld(new CharacterSelection());
    }
}