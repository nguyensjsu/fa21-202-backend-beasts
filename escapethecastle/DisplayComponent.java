import greenfoot.Actor;

import java.util.ArrayList;

/**
 * Common DisplayComponent class that can wrap multiple DisplayComponents using the Composite pattern.
 */
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

    public <O> O getLeftObject(Class<O> oClass) {
        return oClass.cast(getOneObjectAtOffset(
                getWidth() / -2 - 1,
                0,
                oClass
        ));
    }

    public <O> O getRightObject(Class<O> oClass) {
        return oClass.cast(getOneObjectAtOffset(
                getWidth() / 2 + 1,
                0,
                oClass
        ));
    }

    public <O> O getTopObject(Class<O> oClass) {
        return oClass.cast(getOneObjectAtOffset(
                0,
                getHeight() / -2 - 1,
                oClass
        ));
    }

    public <O> O getBelowObject(Class<O> oClass) {
        return oClass.cast(getOneObjectAtOffset(
                0,
                getHeight() / 2 + 1,
                oClass
        ));
    }

    public <O> O getObject(int x, int y, Class<O> oClass) {
        return oClass.cast(getOneObjectAtOffset(
                x,
                y,
                oClass
        ));
    }

    public <O> O getBottomSideObject(Class<O> oClass) {
        for (int i = getWidth() / -2; i <= getWidth() / 2; i++) {
            O obj = oClass.cast(getOneObjectAtOffset(i, getHeight() / 2 + 1, oClass));
            if (obj != null) return obj;
        }
        return null;
    }

    public <O> O getBottomLeftObject(Class<O> oClass) {
        return oClass.cast(getOneObjectAtOffset(
                getWidth() / -2 - 1,
                getHeight() / 2 + 1,
                oClass
        ));
    }

    public <O> O getBottomRightObject(Class<O> oClass) {
        return oClass.cast(getOneObjectAtOffset(
                getWidth() / 2 + 1,
                getHeight() / 2 + 1,
                oClass
        ));
    }

    public boolean isOnWorldGround() {
        return getY() >= getWorld().getHeight() - getHeight() / 2;
    }

    public boolean hasHitRightWall() {
        return getX() + (getWidth() / 2) >= getWorld().getWidth();
    }

    public boolean hasHitLeftWall() {
        return getX() - getWidth() / 2 <= 0;
    }

    public void resetLocationOnWorldGround() {
        setLocation(getX(), getWorld().getHeight() - (getHeight() / 2));
    }

    public void resetLocationOnRightWall() {
        setLocation(getWorld().getWidth() - (getWidth() / 2), getY());
    }

    public void resetLocationOnLeftWall() {
        setLocation(getWidth() / 2, getY());
    }

    public void resetIfHitWall() {
        if (hasHitLeftWall()) resetLocationOnLeftWall();
        if (hasHitRightWall()) resetLocationOnRightWall();
    }
}
