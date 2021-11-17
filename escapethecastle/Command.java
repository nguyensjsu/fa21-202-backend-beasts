import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Command implements ICommand
{
    private IReceiver r;
    
    public void setReceiver(IReceiver r) {
        this.r = r;
    }
    
    public void execute() {
        r.doAction();
    }
}
