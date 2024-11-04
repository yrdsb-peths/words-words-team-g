import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Laser extends Actor {
    private GreenfootImage laserImage;
    Enemy target;

    public Laser(boolean doubleLetters, Enemy target) {
        if (doubleLetters == true) {
            laserImage = new GreenfootImage("images/LaserShotBuffed.png");
            laserImage.scale(90, 30);
        } else {
            laserImage = new GreenfootImage("images/LaserShot.png");
            laserImage.scale(60, 20);
        }
        setImage(laserImage);
        this.target = target;
    }

    public void act() {
        turnTowards(target.getX(), target.getY());
        move(20);
        checkTouching();
    }

    public void checkTouching() {
        if (this.getX() < target.getX() + 20 && this.getX() > target.getX() - 20 && this.getY() < target.getY() + 20
                && this.getY() > target.getY() - 20) {
            makeExplosion();
            getWorld().removeObject(this);
            target.removeEnemy();
        }
    }

    public void makeExplosion() {
        getWorld().addObject(new Explosion(target), target.getX(), target.getY());
    }
}
