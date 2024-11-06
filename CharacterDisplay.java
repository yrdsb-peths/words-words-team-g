import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class CharacterDisplay extends Actor
{
    /**
     * Constructor that sets the image of the object.
     */
    public CharacterDisplay(GreenfootImage initialImage) {
        setImage(initialImage);
    }
    
    /**
     * This method updates the object's image to an image that is passed through the parameter.
     */
    public void setImage(GreenfootImage image) {
        super.setImage(image);  // Update the actor's image
    }
}