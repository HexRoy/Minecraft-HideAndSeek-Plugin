# V1.0

-----------
- /sg start game command
- /eg end game commands
- ### **Role Changes**: 
  - **Hunters**
    - Netherite sword 
    - 4x health
    - Can break blocks
  - **Hiders**
    - 1/2 health
    - Can not break blocks

- ### Bug fixes: 
  - can no longer use /sg while in game
  - can no longer use /eg while not in game
  - Game state now correctly set to not in game after last player killed
  - Deleted repetitive code in onPlayerDeathListener. Now it used the /eg command
  - EndGameCommand setting game state and broadcast message outside loop
  - Updated location data in config
  - Config no longer erased defaults when adding data


- ### Upcoming changes
    - **Roles**
        - Implement new roles with gui selection
        - /role command
        - Hiders:
          - Ender pearls?
          - Classes? (speedy, jumper, invis)
          - Prevent hiders from placing blocks. only allow hunters to place
        - Hunters: 
          - Ender pearls?
          - Prevent hunters from crafting items
  - Turn on mob spawning or change to peaceful
  - Utilize time limit for hiding/ way for hiders to win.
    - Add sounds from hiders as game progresses (fireworks every 1-2 minutes?)
  - Need to figure out how to balance 'cheaty hiding spots' (deep caves, ocean floors, ect.)
  - Player/ or last player? who dies is not teleported to saved location onPlayerRespawnListener??
  - User config timer variables for potion effect duration
