import greenfoot.*;

public class Button extends Actor {
    private Runnable action;
    private String text;
    private GreenfootSound buttonClickSound;

    public Button(Runnable action, String text) {
        this.action = action;
        this.text = text;
        
        //button background
        GreenfootImage buttonImage = new GreenfootImage("buttonLong_beige.png");
        
        //adding text
        GreenfootImage textOverlay = new GreenfootImage(text, 23, Color.BLACK, new Color(0, 0, 0, 0));
        
        //x and y values of text
        int textX = (buttonImage.getWidth() - textOverlay.getWidth()) / 2;
        int textY = (buttonImage.getHeight() - textOverlay.getHeight()) / 2;
        buttonImage.drawImage(textOverlay, textX, textY);
        
        //setting sound
        buttonClickSound = new GreenfootSound("ButtonClick.mp3");
        
        setImage(buttonImage); // Set the final button image
    }

    //action
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            //play button sound when button is pressed
            buttonClickSound.setVolume(100);
            buttonClickSound.play();
            
            if (action != null) {
                action.run();
            }
        }
    }
}
