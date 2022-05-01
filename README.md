# DemonQuest
An RPG on top of a game engine made using java and it's native libraries. The game is inspired by Diablo 2, although I could never nail the isometric views.
Quite a lot of help from a youtuber named Yan Chernikov! (YT: https://www.youtube.com/playlist?list=PLlrATfBNZ98eOOCk2fOFg7Qg5yoQfFAdf)

<h3>Technical features:</h3>
<ol>
  <li>Basic particle physics</li>
  <li>Selective rendering of the player area only</li>
  <li>Sprite rotation and scaling</li>
  <li>Loading game assets via sprite sheets</li>
  <li>A* for pathfinding and obstacle avoidance</li>
  <li>Propreitary text engine for rendering enemy/NPC names, dialogues etc</li>
  <li>Sprite animations</li>
  <li>Map loading via a map png file. Each pixel color corresponds to a different tile (a tile is a texture loaded from a file, which may or may not be solid)</li>
</ol>
And more!


<h3>Screengrabs:</h3>

![gif_speech](https://user-images.githubusercontent.com/29706261/165937911-ce0451aa-46c6-4218-83b6-1ca701a60f35.gif)
<h6>Engaging in dialogues with NPCs. A friendly NPC attacks off screen enemies, displaying sprite rotation (the projectile) and particle physics.</h6>



![gif_astar](https://user-images.githubusercontent.com/29706261/165939341-6237fe3d-38da-4dbc-b5ee-c36049cdc127.gif)
<h6>Obstacle avoidance using A* (The follower uses A*)</h6>



![gif_combat](https://user-images.githubusercontent.com/29706261/165939521-7080a5c1-b311-4257-8989-e5bcbe9803e3.gif)
<h6>Combat, with health potions being consumed.</h6>


<h3>Objective:</h3>

Collect 3 relics (The eyes, mind and heart) of an ancient hero, scattered in chests across the map, and summon and defeat the final boss, the devil himself!

<h3>Enemies:</h3>

<h4>Rogue:</h4>
A common enemy that fires projectiles. Can be killed in a single hit.

<h4>Summoner:</h4>
A mini-boss that summons evil spirits which can pass through solid blocks and drain health. 2 present on the island on the western part of the map.


<h4>Lucifer:</h4>
The final boss. Moves slowly, can take serious punishment. Fires powerful projectiles if you get close, and summons bats that navigate to you and drain health. He is spawned on collecting all 3 relics, and appears on the eastern desert of the map.


<h3>Potions:</h3>
Potions are scattered across the map in chests (open with enter). Potions are of two kinds;

<h4>Health potion:</h4>
Restores a bit of health. Is green in color.

<h4>Attack potion:</h4>
Increases damage dealt by a factor of 10 for a short duration. It's the red potion.

A friendly NPC (the follower) follows you and attacks enemies. His pathfinding is based on A*, and he can navigate tricky obstacles to get to you.
