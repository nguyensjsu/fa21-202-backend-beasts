import greenfoot.*;
/**
 * Write a description of class ScoreBoardTitle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreBoardTitle extends DisplayComponent {

    public ScoreBoardTitle() {
        GreenfootImage titleImage = new GreenfootImage("title.png");
        titleImage.scale(180,60);
        setImage(titleImage);
    }
}
