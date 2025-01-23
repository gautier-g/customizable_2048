<h3> 2048 game implemented in Java and tested with gradle using JUnit.</h3>

# How to start the game:
- *Pull the git repository*
- *Type "./gradlew run" in the root folder*

# Rules of the game:
- *The square grid width (size) and the goal number are fixed before the game starts.*
- *While the game runs, at each iteration:*
  - *A new tile of value 2, 4 or 8 spawns on a free square*
  - *The player swipes the tiles in a direction. If no tile moves and the grid is full, he lost.*
  - *Each tile pairs of same value merge when swiping (in case there's no tile between them).*
  - *If a tile reaches the goal, the player wins.*

# Controls
- ***Backspace:** Start a new game.*
- ***Escape:** Exit the game.*
- ***Directional arrows:** Swipe tiles in a direction.*
