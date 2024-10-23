import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Enemy extends Actor
{
    int changeSpeed = 50; // Higher value to make it slower

    int toX = 150; // Location of where it should go to
    int toY = 800;

    int startX;
    int startY = 0;

    int changeX, changeY;

    public Enemy() { //Sets image
        GreenfootImage red = new GreenfootImage("images/red.png");
        red.scale(50, 50);
        setImage(red);
    }

    public void setStartX(int x) {
        startX = x;
        changeX = (int) (toX - startX) / changeSpeed;
        changeY = (int) (toY - startY) / changeSpeed;
    }

    public void act() // Stuff falls down
    {
        setLocation(getX() + changeX, getY() + changeY);
    }
}
