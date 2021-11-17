import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Renders the image at location string passed in constructor and handles onClick of that image.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends DisplayComponent implements IInvoker
{
    ICommand command;
    
    // constructor
    public Button(String imageLocation) {
        // length of text image, unknown
        GreenfootImage img = new GreenfootImage(imageLocation);
        setImage(img);
    }

    public void setCommand(ICommand command) {
        this.command = command;
    }
    
    public void invoke() {
        command.execute();
    }
    
    /**
     * Act - do whatever the ImageFactory wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        if(Greenfoot.mouseClicked(this)) {
            if(command != null)
                invoke();
        }
    }
}
