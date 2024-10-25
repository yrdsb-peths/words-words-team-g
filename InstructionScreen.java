import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class InstructionScreen extends World
{
    private World menuScreen;
    
    //instruction list
    private String[] instructions = {
        "Welcome to the game!",
        "Type the words of the \n attacking ships to destroy them!",
        "When user types the \n wrong letter, gun will get jammed",
        "User can only attack \n one ship until it is destroyed",
        "One new enemy will be \n spawned each round" 

    };
    
    //used variables 
    private Label instructionLabel;
    private int index = 0;
    int button_Xint = 260;

    public InstructionScreen(World menuScreen)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(500, 700, 1);
        setBackground(new GreenfootImage("Background.jpg"));
        this.menuScreen = menuScreen;

        // Create a label with the first instruction
        instructionLabel = new Label(instructions[index], 30);
        // Add the instruction label to the screen
        addObject(instructionLabel, 250, 200);

        // Add Next and Previous buttons
        addObject(new Button(this::nextInstruction, "Next"), 250, button_Xint);
        addObject(new Button(this::previousInstruction, "Previous"), 250, button_Xint + 50);

        addObject(new Button(this::goMenuScreen, "Menu"), 250, button_Xint + 100);
    }

    //go to the next instruction lines by changing index
    public void nextInstruction() {
        if (index < instructions.length - 1) {
            index++;
            instructionLabel.setValue(instructions[index]); // Update the label with the next instruction
        }
    }

    //go to previous instruction line by changing index
    public void previousInstruction() {
        if (index > 0) {
            index--;
            instructionLabel.setValue(instructions[index]); // Update the label with the previous instruction
        }
    }

    //going back to the menu screen
    public void goMenuScreen() {
        Greenfoot.setWorld(new MenuScreen());
    }
}
