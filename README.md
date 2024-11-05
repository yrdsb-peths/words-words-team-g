# Game README
Typing Attack

## Group Members
- Charlie
- Henry
- Jacky
- Mason Zhang

## Data Structures Used

1. **Queue<String>**: 
   - **Location**: `Avatar` class
   - **Purpose**: This data structure holds the file paths for avatar images. Using a Queue allows for easy addition and removal of avatar images as the player cycles through them.

2. **HashMap<String, Enemy>**: 
   - **Location**: `Game` class
   - **Purpose**: This map holds the enemies, allowing for quick access and management of enemies based on their identifiers. This is particularly useful for checking and updating enemy states in the game.

3. **List<Enemy>**: 
   - **Location**: `Explosion` and `Forcefield` classes
   - **Purpose**: Used to manage and interact with multiple instances of `Enemy` objects that are currently touching the `Explosion` or `Forcefield`. This allows for effective collision detection and damage processing.

4. **Label**: 
   - **Location**: `Label` class
   - **Purpose**: This class manages the display of scores and other text in the game, using Greenfoot's image manipulation capabilities to create a visually appealing score display.

## Additional Notes
- The game features a visually appealing interface, where users can intuitively navigate through different menus and options without needing extensive instructions.
- Sound effects, animations, and score tracking are implemented to enhance gameplay, providing players with feedback and incentives to continue playing.
- The game includes a menu system for selecting different modes and viewing scores, which is implemented through interactive buttons that respond to user input.
- The game provides a definite ending by allowing players to reach a score goal or complete a series of levels.


