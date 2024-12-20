import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ModeScreen extends World
{
    MenuScreen menuScreen;

    public ModeScreen(MenuScreen menuScreen)
    {    
        super(500, 700, 1);
        setBackground(new GreenfootImage("Background.jpg"));

        // Choices for user to pick difficulty
        addObject(new Button(this::hardMode, "Hard"), 250, 360);
        addObject(new Button(this::mediumMode, "Medium"), 250, 310); 
        addObject(new Button(this::easyMode, "Easy"), 250, 260);
        addObject(new Button(this::extremeMode, "Extreme"), 250, 410);
        addObject(new Button(this::goMenu, "Menu"), 250, 460);

        // Mode selection label
        Label instructionLabel = new Label("Select a mode", 30);
        Color offWhite = new Color(251, 247, 245);
        instructionLabel.setLineColor(offWhite);
        instructionLabel.setFillColor(offWhite);
        addObject(instructionLabel, 250, 200);
        this.menuScreen = menuScreen;
    }
    
    // Selects difficulty
    public void easyMode()
    {
        goGame(1);
    }
    
    public void mediumMode()
    {
        goGame(2);
    }
    
    public void hardMode()
    {
        goGame(3);
    }
    
    public void extremeMode()
    {
        goGame(4);
    }
    
    // Sets game speed based on difficulty
    public void goGame(int difficulty)
    {
        int enemySpeed;
        switch(difficulty) {
            case 1:  // Easy
                enemySpeed = 3;
                break;
            case 2:  // Medium
                enemySpeed = 5;
                break;
            case 3:  // Hard
                enemySpeed = 9;
                break;
            case 4:  // Extreme
                enemySpeed = 13;
                break;
            default:
                enemySpeed = 5;
        }
        menuScreen.menuMusic.stop();

        // Changes to game screen
        Greenfoot.setWorld(new Game(difficulty, menuScreen.whichShip, enemySpeed));
    }
    
    public void goMenu()
    {
        Greenfoot.setWorld(menuScreen);
    }
}
