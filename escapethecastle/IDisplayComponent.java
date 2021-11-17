public interface IDisplayComponent
{
    void addChild(DisplayComponent c);
    void removeChild(DisplayComponent c);
    void setX(int x);
    void setY(int y);
    void setLocation(int x, int y);
    int getX();
    int getY();
    void render(IScreen s);
}
