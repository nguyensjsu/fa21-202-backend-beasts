import greenfoot.*;

/**
 * Class to represent a screen.
 */
public class Screen extends World implements IScreen {
    public Screen(int w, int h, int s) {
        super(w, h, s);
    }

    public void addComponent(DisplayComponent c, int x, int y) {
        addObject(c, x, y);
        c.render(this);
    }
}
