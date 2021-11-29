import greenfoot.*;

/**
 * Display component to show the score board title.
 */
public class ScoreBoardTitle extends DisplayComponent {

    public ScoreBoardTitle() {
        GreenfootImage titleImage = new GreenfootImage("title.png");
        titleImage.scale(180, 60);
        setImage(titleImage);
    }
}
