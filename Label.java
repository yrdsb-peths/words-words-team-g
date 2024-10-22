import greenfoot.*;

public class Label extends Actor {
    private GreenfootImage image;
    private String text;
    private int fontSize;
    
    //setting text size
    public Label(String text, int fontSize) {
        this.text = text;
        this.fontSize = fontSize;
        updateImage();
    }

    //text
    public void setText(String text) {
        this.text = text;
        updateImage();
    }

    //changing image
    private void updateImage() {
        image = new GreenfootImage(text, fontSize, Color.BLACK, Color.WHITE);
        setImage(image);
    }
}
