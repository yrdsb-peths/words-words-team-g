import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class MenuScreen extends World
{    
    public MenuScreen()
    {    
        //size of world
        super(500, 700, 1); 
        //adding buttons
        addObject(new Button(this::goInstructions, "Instructions"), 250, 360);
        addObject(new Button(this::goHighScores, "High Scores"), 250, 310); 
        addObject(new Button(this::goGame, "Start Game"), 250, 260);
    }
    
    //going to intruction screen
    public void goInstructions() {
        Greenfoot.setWorld(new InstructionScreen());
    }
    
    //going to the game
    public void goGame() {
        Greenfoot.setWorld(new Game());


    public void goInstructions() {
        Greenfoot.setWorld(new InstructionScreen(this));
    }
}