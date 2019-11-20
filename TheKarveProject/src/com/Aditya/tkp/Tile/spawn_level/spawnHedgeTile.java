package com.Aditya.tkp.Tile.spawn_level;

import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.Graphics.Sprite;
import com.Aditya.tkp.Tile.Tile;

public class spawnHedgeTile extends Tile {

	public spawnHedgeTile(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}
	public void render(int x, int y, Screen screen)
	{
		screen.renderTile(x<<4, y<<4, this);
	}
}
