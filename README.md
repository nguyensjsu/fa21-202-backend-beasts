# Backend Beasts

### Team Members
- [Poonam Yadav](https://github.com/poonamyadav12) (015359305)
- [Alan Kuriakose](https://github.com/aln0071) (013854802)
- [Het Jagani](https://github.com/hetjagani) (015261415)
- [Mayank Garg](https://github.com/mayankgarg23) (015209948)

### Escape The Castle

**Start Screen**

![Start Screen](escapethecastle/images/StartScreen.png)

**Game Screen**

![Start Screen](escapethecastle/images/GameScreen.png)

**ScoreBoard Winning Page**

![Start Screen](escapethecastle/images/ScoreBoard-WinningPage.png)

**ScoreBoard Loosing Page**

![Start Screen](escapethecastle/images/ScoreBoard-LoosingPage.png)

## Key Design Features:

### Strategy Pattern

Strategy Pattern is used to configure the game to start at different levels - Easy, Medium and Hard level. EasyGameStrategy, MediumGameStrategy and HardGameStrategy implements respective algorithm for the game levels. GameStrategyButton helps to add different buttons for Easy, Medium and Hard level. Strategy Pattern helps us to keep code structure clear and flexible for adding any more game levels.

![Strategy Pattern](escapethecastle/uml_diagrams/StrategyPattern.png)

### Decorator Pattern

Decorator Pattern allows us to add responsibilites to Player object dynamically and transparently for characters among Mario, Wario and Luigi.

![Decorator Pattern](escapethecastle/uml_diagrams/DecoratorPattern.png)

### State Pattern

State pattern allows an object to alter its behavior when its internal state changes.

#### Screen state machine
![State Pattern](escapethecastle/uml_diagrams/Screen_Statemachine.png)

### Factory Pattern

#### Player Factory
The factory pattern is used to create the `Player` objects for the three characters - `Mario`,  `Wario`, and `Lugi`.

![Player factory](escapethecastle/uml_diagrams/FactoryPlayer.png)

#### Button Factory
The factory pattern is used to create different `Button` objects for the game including the `start` button, `quit` button, and `replay` button.

![Button factory](escapethecastle/uml_diagrams/FactoryButton.png)

### Singleton Pattern

#### Game Controller
The singleton pattern is used for the `GameController`. It has a static `gameController` instance which is returned on calling the `getInstance()` method. The `StartScreen`, `ButtonFactory`, and `GameScreen` uses it to change the current game screen.

![Singleton Game Controller](escapethecastle/uml_diagrams/SingletonGameController.png)

#### Game Strategy Provider
The `GameStrategyProvider` uses the singleton pattern and stores the current strategy being used. It is used by other classes like `GameController`, `GameOverScreen`, `GameScreen`, `GameStrategyButton`, `ScoreCalculator`, and `ScoreRepository` for getting and setting the game strategy.

![Singleton Game Strategy Provider](escapethecastle/uml_diagrams/SingletonGameStrategyProvider.png)

#### Player Selector
The `PlayerSelector` uses the singleton pattern. It allow setting and getting the playable character that is to be used for the current game. It is used by classes like the `GameController` and `StartScreen`.

![Singleton Player Selector](escapethecastle/uml_diagrams/SingletonPlayerSelector.png)
