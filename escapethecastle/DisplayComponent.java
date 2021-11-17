import java.util.ArrayList;
import greenfoot.Actor;

public class DisplayComponent extends Actor implements IDisplayComponent
{
   private ArrayList<DisplayComponent> components;
   private int x,y;
   
   public DisplayComponent() {
       components = new ArrayList<DisplayComponent>();
   }
   
    public void addChild(DisplayComponent c){
        components.add(c);
    }
    
    public void removeChild(DisplayComponent c){
        components.remove(c);
    }
    
    public void setX(int x){
        this.x = x;
        super.setLocation(x, this.y);
    }
    
    public void setY(int y){
        this.y = y;
        super.setLocation(this.x, y);
    }
    
    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
        super.setLocation(x, y);
    }
    
    public int getX(){
        return super.getX();
    }
    
    public int getY(){
        return super.getY();
    }
    
    public void render(IScreen s) {
        for(DisplayComponent dc: components) {
            s.addComponent(dc, dc.getX(), dc.getY());
        }
    }
}
