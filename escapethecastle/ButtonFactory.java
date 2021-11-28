import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;

import java.util.HashMap;

public class ButtonFactory {
    private final HashMap<String, String> buttonImageMap;
    private final HashMap<String, ICommand> buttonCommandMap;

    private static final GreenfootSound clickSound = new GreenfootSound("sounds/button-click.wav");

    public ButtonFactory() {
        buttonImageMap = new HashMap<>();
        buttonCommandMap = new HashMap<>();

        buttonImageMap.put("START", "images/button_start.png");
        buttonImageMap.put("QUIT", "images/button_quit.png");
        buttonImageMap.put("REPLAY", "images/button_replay.png");

        ICommand startCommand = new Command();
        startCommand.setReceiver(new IReceiver() {
            public void doAction() {
                if(Player.getPlayerName().equals("Player 1")) {
                    String name = Greenfoot.ask("Enter Player Name");
                    Player.setPlayerName(name);
                }
                clickSound.play();
                GameController.getInstance().setScreen(GameController.Screen.GAME_SCREEN);
            }
        });
        buttonCommandMap.put("START", startCommand);

        ICommand quitCommand = new Command();
        quitCommand.setReceiver(new IReceiver() {
            public void doAction() {
                Player.setPlayerName("Player 1");
                clickSound.play();
                StartScreen.BACKGROUND_MUSIC.stop();
                Greenfoot.stop();
            }
        });
        buttonCommandMap.put("QUIT", quitCommand);

        ICommand replayCommand = new Command();
        replayCommand.setReceiver(new IReceiver() {
            public void doAction() {
                Player.setPlayerName("Player 1");
                clickSound.play();
                GameController.getInstance().setScreen(GameController.Screen.START_SCREEN);
                StartScreen.BACKGROUND_MUSIC.playLoop();
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
