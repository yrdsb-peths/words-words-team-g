import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class InstructionScreen extends World
{
    private World menuScreen;
    private String[] instructions = {
        "Welcome to the game!",
        "Use arrow keys to move.",
        "Press space to jump."
    };

    private Label instructionLabel;
    private int index = 0;
    int button_Xint = 300;

    public InstructionScreen(World menuScreen)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(500, 700, 1); 
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

    public void nextInstruction() {
        if (index < instructions.length - 1) {
            index++;
            instructionLabel.setValue(instructions[index]); // Update the label with the next instruction
        }
    }

    public void previousInstruction() {
        if (index > 0) {
            index--;
            instructionLabel.setValue(instructions[index]); // Update the label with the previous instruction
        }
    }

    public void goMenuScreen() {
        Greenfoot.setWorld(menuScreen);
    }
}
