## Upcoming changes
- **Roles**
  - Implement new class with gui selection
  - /class command
  - Hiders:
      - Ender pearls?
      - Classes? (speedy, jumper, invis)
      - Prevent hiders from placing blocks. only allow hunters to place
  - Hunters:
      - Ender pearls?
      - Prevent hunters from crafting items
          

**Bugs**
- N/A

**Other**  
- Turn on mob spawning or change to peaceful
- Utilize time limit for hiding/ way for hiders to win.
  - After timer runs out end game, hiders win on screen. hunter loses on screen
  - Add sounds from hiders as game progresses (fireworks every 1-2 minutes?)
- Need to figure out how to balance 'cheaty hiding spots' (deep caves, ocean floors, ect.)
  - Use this to make sure players are not put inside blocks
      //blockY = world.getHighestBlockYAt(loc) + 1
- Player/ or last player? who dies is not teleported to saved location onPlayerRespawnListener??
- Collapsing zone 
- Make it more clear in what to do Hunter/Hider 
- /clear_roles
  - change all roles to none so you are able to delete blocks if needed

-----------

# V1.2

-----------


-----------

# V1.1

-----------

### **Commands**
- NEW: /c /class : No functionality yet. EST: V2

### **Bug Fixes**
- Timer:
  - Moved stopTimer() outside of the loop for players to fix server crash issue
  - Timer stopped after using /eg command
    - After timer ends and /eg is used looping "Time is up!" prints in chat. Fixed from fixing issue above

- Block Breaking: Only hunter is able to break blocks

### **Changes**
- Basic on screen timer implemented appearing every 30 seconds
  - After timer expires automatically ends the game
  - When less than 10 seconds remaining the timer counts down by the second in red
- Changed all instances of player.getName() to player.getUniqueID() to avoid name conflicts
- Remove all hunter 'waiting period' potion effects after using /eg command
- Deleted EndGameCommand.java class --> Merged with StartGameCommand.java class. Created: StartGameEndGameCommands.java

### **Config**: 
  - Seeker_Delay implemented correctly 
  - NEW: Timer_Denominator: How often the time remaining will appear on screen in seconds
  - Resets the in_game variable to false on plugin start

  

-----------

# V1.0

-----------

### **Commands**
- /sg start game command
- /eg end game commands

### **Role Changes**: 
- **Hunters**
  - Netherite sword 
  - 4x health
  - Can break blocks
- **Hiders**
  - 1/2 health
  - Can not break blocks

### Bug fixes: 
  - can no longer use /sg while in game
  - can no longer use /eg while not in game
  - Game state now correctly set to not in game after last player killed
  - Deleted repetitive code in onPlayerDeathListener. Now it used the /eg command
  - EndGameCommand setting game state and broadcast message outside loop
  - Updated location data in config
  - Config no longer erased defaults when adding data