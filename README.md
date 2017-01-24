This is my version of Breakout. Instead of a typical ball and brick format, we are a Duke basketball team trying to win the National Championship with two obstacles: the ACC championship and the Semi-finals. In each of these games, you are facing off against UNC, Kentucky, and Kansas. The UNC blocks require 3 hits to break, Kentucky requires 2, and Kansas requires 1. Powerups are programmed to be randomly assigned to blocks, and will drop if that block is broken. In terms of the actual programming structure, Breakout.java is the main view manager -- controlling most of the steps. Once the game goes to the animated levels, Breakout.java sends the stage control to Levels.java which runs the animations. BlockManager.java exists to track the current state of the blocks. 


Level 1: Normal ball speed and platform size. Level 2: Normal Ball speed, platform is smaller. Level 3: Faster ball and smaller platform. 

Cheat Codes:
W: Win Instantly
R: Reset
L: Extra Lives
Z: Lost Instantly
M: Get two platforms
T: Sticky platforms
Space: Release stuck ball
1-3: Go to that level