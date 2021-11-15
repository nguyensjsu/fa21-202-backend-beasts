import java.util.HashMap;
/**
 * Write a description of class ButtonFactory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ButtonFactory  
{
    HashMap<String, Button> buttonMap;
    
    public ButtonFactory() {
        buttonMap = new HashMap<>();
        // define start button
        Button startButton = new Button("images/button_start.png");
        startButton.onClick(new StartCommand());
        buttonMap.put("START", startButton);
        // define quit button
        Button quitButton = new Button("images/button_quit.png");
        quitButton.onClick(new QuitCommand());
        buttonMap.put("QUIT", quitButton);
    }
    
    public Button getButton(String buttonType) {
        return buttonMap.getOrDefault(buttonType, null);
    }
}
