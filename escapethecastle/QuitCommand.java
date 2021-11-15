import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class QuitCommand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class QuitCommand implements ICommand
{
    public void execute() {
        Greenfoot.stop();
    }
}
