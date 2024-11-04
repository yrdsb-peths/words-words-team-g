import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Avatar extends Actor {
    Queue<String> avatarQueue = new Queue<>();
    String[] avatars = { "images/download.jpeg", "HuGGeENt6kGyixe3hT9tnY.jpg" }; // Add the greenfoot image file here

    public Avatar() { // Adds all the avatars to a queue
        for (String avatar : avatars) {
            avatarQueue.add(avatar);
        }
    }

    public void nextAvatar() { // Changes the avatar to the next one
        String current = avatarQueue.pop();
        this.setImage(current);
        avatarQueue.add(current);
    }
}