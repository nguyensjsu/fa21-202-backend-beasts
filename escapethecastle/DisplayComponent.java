import greenfoot.Actor;

import java.util.ArrayList;

public class DisplayComponent extends Actor implements IDisplayComponent {
    private final ArrayList<DisplayComponent> components;
    private int x;
    private int y;

    public DisplayComponent() {
        components = new ArrayList<>();
    }

    public void addChild(DisplayComponent c) {
        components.add(c);
    }

    public void removeChild(DisplayComponent c) {
        components.remove(c);
    }

    public void setX(int x) {
        this.x = x;
        super.setLocation(x, this.y);
    }

    public void setY(int y) {
        this.y = y;
        super.setLocation(this.x, y);
    }

    @Override
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        super.setLocation(x, y);
    }

    public void render(IScreen s) {
        for (DisplayComponent dc : components) {
            s.addComponent(dc, dc.getX(), dc.getY());
        }
    }

    public int getWidth() {
        return getImage().getWidth();
    }

    public int getHeight() {
        return getImage().getHeight();
    }
}
