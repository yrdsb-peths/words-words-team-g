import greenfoot.*;

public class MyWorld extends World {

    public MyWorld() {
        super(300, 1000, 1);
    }

    public void makeWord() { // placeholder for making stuff fall down
        Enemy enemy = new Enemy();
        int  randomNum = Greenfoot.getRandomNumber(300);
        enemy.setStartX(randomNum);
        addObject(enemy, randomNum, 0);
    }
    public void act() { // press right key to make stuff fall down
        if(Greenfoot.isKeyDown("Right"))
        {
            makeWord();
        }
    }
}
