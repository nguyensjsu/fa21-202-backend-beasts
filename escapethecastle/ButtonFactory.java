import java.util.HashMap;
import greenfoot.GreenfootSound;
import greenfoot.Greenfoot;

public class ButtonFactory  
{
    private HashMap<String, String> buttonImageMap;
    private HashMap<String, ICommand> buttonCommandMap;
    private ICommand startCommand;
    private ICommand quitCommand;
    private ICommand replayCommand;
    
    private static GreenfootSound clickSound = new GreenfootSound("sounds/button-click.wav");
    
    public ButtonFactory() {
        buttonImageMap = new HashMap<>();
        buttonCommandMap = new HashMap<>();
        
        buttonImageMap.put("START", "images/button_start.png");
        buttonImageMap.put("QUIT", "images/button_quit.png");
        buttonImageMap.put("REPLAY", "images/button_replay.png");
        
        startCommand = new Command();
        startCommand.setReceiver(new IReceiver() {
            public void doAction() {
                 clickSound.play();
                 Greenfoot.setWorld(new GameScreen());
            }
        });
        buttonCommandMap.put("START", startCommand);
        
        quitCommand = new Command();
        quitCommand.setReceiver(new IReceiver() {
            public void doAction() {
                 clickSound.play();
                 StartScreen.bgm.stop();
                 Greenfoot.stop();
            }
        });
        buttonCommandMap.put("QUIT", quitCommand);
        
        replayCommand = new Command();
        replayCommand.setReceiver(new IReceiver() {
           public void doAction() {
               clickSound.play();
               Greenfoot.setWorld(new StartScreen());
               StartScreen.bgm.playLoop();
           }
        });
        buttonCommandMap.put("REPLAY", replayCommand);
    }
    
    public Button getButton(String buttonType) {
        String imagePath = buttonImageMap.get(buttonType);
        ICommand cmd = buttonCommandMap.get(buttonType);
        
        Button button = new Button(imagePath);
        button.setCommand(cmd);
        
        return button;
    }
}
