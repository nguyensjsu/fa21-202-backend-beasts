# Backend Beasts

### Team Members
- [Poonam Yadav](https://github.com/poonamyadav12) (015359305)
- [Alan Kuriakose](https://github.com/aln0071) (013854802)
- [Het Jagani](https://github.com/hetjagani) (015261415)
- [Mayank Garg](https://github.com/mayankgarg23) (015209948)

### Escape The Castle

**Start Screen**

![Start Screen](https://github.com/nguyensjsu/fa21-202-backend-beasts/blob/main/escapethecastle/images/StartScreen.png)

**Game Screen**

![Start Screen](https://github.com/nguyensjsu/fa21-202-backend-beasts/blob/main/escapethecastle/images/GameScreen.png)

**ScoreBoard Winning Page**

![Start Screen](https://github.com/nguyensjsu/fa21-202-backend-beasts/blob/main/escapethecastle/images/ScoreBoard-WinningPage.png)

**ScoreBoard Loosing Page**

![Start Screen](https://github.com/nguyensjsu/fa21-202-backend-beasts/blob/main/escapethecastle/images/ScoreBoard-LoosingPage.png)

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
![Player factory](images/FactoryPlayer.png)

#### Button Factory
The factory pattern is used to create different `Button` objects for the game including the `start` button, `quit` button, and `replay` button.
![Button factory](images/FactoryButton.png)
