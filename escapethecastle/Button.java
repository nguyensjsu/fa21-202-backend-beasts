import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Renders a button with the provided image.
 */
public class Button extends DisplayComponent implements IInvoker {
    ICommand command;

    // constructor
    public Button(String imageLocation) {
        // length of text image, unknown
        GreenfootImage img = new GreenfootImage(imageLocation);
        setImage(img);
    }

    /**
     * Sets the command object for this button.
     */
    public void setCommand(ICommand command) {
        this.command = command;
    }

    /**
     * Invokes the command object.
     */
    public void invoke() {
        command.execute();
    }

    /**
     * Act - do whatever the ImageFactory wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    @Override
    public void act() {
        // Add your action code here.
        if (Greenfoot.mouseClicked(this) && (command != null)) {
            invoke();
        }
    }
}
