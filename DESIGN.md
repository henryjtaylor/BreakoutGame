DESIGN:

This is my version of Breakout. Instead of a typical ball and brick format, we are a Duke basketball team trying to win the National Championship with two obstacles: the ACC championship and the Semi-finals. In each of these games, you are facing off against UNC, Kentucky, and Kansas. The UNC blocks require 3 hits to break, Kentucky requires 2, and Kansas requires 1. Powerups are programmed to be randomly assigned to blocks, and will drop if that block is broken. In terms of the actual programming structure, Breakout.java is the main view manager -- controlling most of the steps. Once the game goes to the animated levels, Breakout.java sends the stage control to Levels.java which runs the animations. BlockManager.java exists to track the current state of the blocks. 


Level 1: Normal ball speed and platform size. Level 2: Normal Ball speed, platform is smaller. Level 3: Faster ball and smaller platform.


If you want to add more features, you would most likely add them two Levels.java or Breakdown.java. If you want to add functionality to actual gameplay, then you would be adding to Levels.java. If you want to add additional intro pages, you would be adding to Breakdown.java. 

One of the major tradeoffs I made was not doing a wood basketball court as the background. That seemed a little complex and overkill for this project. In addition, I removed the end feature of the basketball hoop that you would have to "make." It would have taken more time than I thought was necessary. 