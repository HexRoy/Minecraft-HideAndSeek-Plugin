## TODO
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
- Player/ or last player? who dies is not teleported to saved location onPlayerRespawnListener??


**Other**
- Need to figure out how to balance 'cheaty hiding spots' (deep caves, ocean floors, ect.)
- /clear_roles
  - change all roles to none so you are able to delete blocks if needed
- Give permission to use /spectate to all players
- Add title for when the world border will collapse "World will shrink in 10s"

-----------

# V1.4

-----------

-----------

# V1.3

-----------
### Changes
- Added titles for hunter/hider for instructions. Time is based on Seeker_Delay in config
- Implemented collapsing world border option to slowly push the hiders toward the middle of the map

### Role Changes
- Hunters
  - Added full netherite armor to the hunter

### Config
- Added new fields for Collapsing_World_Border customizability:
  - Collapsing_World_Border
  - Collapsing_World_Border_Amount
  - Collapsing_World_Border_Denominator
  - Collapsing_World_Border_Delay


-----------

# V1.2

-----------
### Bug Fixes
- checkPosition method to check and adjust player position when using /sg:
  - Position lowered if flying in air until ground level
  - Position raised if in the ground until ground level

### Changes
- Disables mob spawns when /sg is used
- Enables mob spawns after game is over
- Fireworks are launched from all role:hiders based on the Timer_Denominator field



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