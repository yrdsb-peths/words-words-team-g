import greenfoot.*;

public class MyWorld extends World {

    public MyWorld() {
        super(300, 1000, 1);
    }

    public void makeWord() { // placeholder for making stuff fall down
        Enemy enemy = new Enemy(50, 50, 5);
        int  randomNum = Greenfoot.getRandomNumber(300);    
        addObject(enemy, randomNum, 0);
    }
    public void act() { // press right key to make stuff fall down
        if(Greenfoot.isKeyDown("Right"))
        {
            makeWord();
        }
    }
}
