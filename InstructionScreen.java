import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class InstructionScreen extends World
{
    private MenuScreen menuScreen;
    
    // Array of instruction strings
    private String[] instructions = {
        "Welcome to the game!",
        "Type the words of the \n attacking ships to destroy them!",
        "When you type the wrong letter,\n  the gun will be jammed for 0.8s",
        "Press \"backspace\" to \n switch your target.",
        "One new enemy will be \n spawned each round.", 
        "Keep the enemy ships away from yours.",
        "Try to get the \n highest score possible!"
    };
    
    // Variables used in the class
    private Label instructionLabel;
    private int index = 0;
    private int buttonXPosition = 260;

    public InstructionScreen(MenuScreen menuScreen)
    {    
        // Create a new world with 500x700 cells with a cell size of 1x1 pixels
        super(500, 700, 1);
        setBackground(new GreenfootImage("Background.jpg"));
        this.menuScreen = menuScreen;

        // Create a label with the first instruction
        instructionLabel = new Label(instructions[index], 30);
        Color offWhite = new Color(251, 247, 245);
        instructionLabel.setLineColor(offWhite);
        instructionLabel.setFillColor(offWhite);
        addObject(instructionLabel, 250, 200);

        // Add "Next", "Previous", and "Menu" buttons
        addObject(new Button(this::nextInstruction, "Next"), 250, buttonXPosition);
        addObject(new Button(this::previousInstruction, "Previous"), 250, buttonXPosition + 50);
        addObject(new Button(this::goMenuScreen, "Menu"), 250, buttonXPosition + 100);
    }

    // Method to display the next instruction
    public void nextInstruction() {
        if (index < instructions.length - 1) {
            index++;
            instructionLabel.setValue(instructions[index]); // Update the label with the next instruction
        }
    }

    // Method to display the previous instruction
    public void previousInstruction() {
        if (index > 0) {
            index--;
            instructionLabel.setValue(instructions[index]); // Update the label with the previous instruction
        }
    }

    /**
     * Transitions to the menu screen and handles music cleanup.
     */
    public void goMenuScreen() {
        Greenfoot.setWorld(menuScreen);
    }
}