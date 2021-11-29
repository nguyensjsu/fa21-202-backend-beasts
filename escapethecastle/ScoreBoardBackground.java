import greenfoot.*;

/**
 * Display component to show the score background.
 */
public class ScoreBoardBackground extends DisplayComponent {

    public ScoreBoardBackground() {
        GreenfootImage backgroundImage = new GreenfootImage("background.jpeg");
        backgroundImage.scale(1000, 1000);
        setImage(backgroundImage);
    }
}
