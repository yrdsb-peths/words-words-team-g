import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Enemy extends Actor
{
    private int changeSpeed = 50; // Higher value to make it slower

    private int toX, toY; // Location of where object should go to

    private int startX;
    private int startY = 0;

    private int changeX, changeY;

    private Game world;

    private Label label;


    public Enemy(int toX, int toY) { //Sets image
        GreenfootImage red = new GreenfootImage(("images/red.png"));
        red.scale(50, 50);
        setImage((GreenfootImage) null);

        this.toX = toX;
        this.toY = toY;
    }

    public void setStartX(int x) {
        startX = x;
        changeX = Math.round((toX - startX) / changeSpeed);
        changeY = Math.round((toY - startY) / changeSpeed);
    }

    public void setLabel(Label label) {
        this.label = label;
        world = (Game) getWorld();
    }

    public void act() // Stuff falls down
    {
        setLocation(getX() + changeX, getY() + changeY);
        label.setLocation(getX() + changeX, getY() + changeY);
        if(getY() >= toY) { // removes object if it is where the ship is
            world.removeObject(this);
            world.removeObject(label);
        }
    }
}
