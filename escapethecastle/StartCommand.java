import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Command to be executed when user click start button.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartCommand implements ICommand
{
    public void execute() {
        Greenfoot.setWorld(new MyWorld());
    }
}
