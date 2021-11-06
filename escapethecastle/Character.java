import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Character extends Actor
{
    private final int GRAVITY = 1;
    private int velocity;
    public Character(){
        GreenfootImage img = new GreenfootImage(getImage());
        img.scale(img.getWidth()/5, img.getHeight()/5);
        setImage(img);
        velocity = 0;
    }
    
    /**
     * Act - do whatever the Character wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        fall();
        if(Greenfoot.isKeyDown("up") && getY()>getWorld().getHeight()-50) jump();
        move();
    }
    
    public void fall(){
        setLocation(getX(),getY()+velocity);
        if(getY()>getWorld().getHeight()-50) velocity = 0;
        else velocity += GRAVITY;
    }
    public void jump(){
        velocity = -20;
    }

    
    public void move(){
        int x = getX();
        int y = getY();
        
        if (Greenfoot.isKeyDown("up")) y--;
        if (Greenfoot.isKeyDown("left")) x-=2;
        if (Greenfoot.isKeyDown("down")) y++;
        if (Greenfoot.isKeyDown("right")) x+=2;
        setLocation(x,y);
    }
}
