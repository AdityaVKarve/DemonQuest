package com.Aditya.tkp.Tile;

import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.Graphics.Sprite;
import com.Aditya.tkp.Tile.spawn_level.*;

public class Tile {
	public int x,y;
	public Sprite sprite;
	public static Tile grass=new GrassTile(Sprite.grass);
	public static Tile dirt=new DirtTile(Sprite.spawn_grass);
	public static Tile voidTile=new voidTile(Sprite.voidSprite);
	public static Tile rock=new RockTile(Sprite.rock);
	public static Tile carpet = new carpet(Sprite.carpet);
	public static Tile dungeon_bricks = new dungeon_bricks(Sprite.dungeon_bricks);
	public static Tile grassy_rock = new grassy_rock(Sprite.grassy_rock);
	public static Tile light_bricks = new light_bricks(Sprite.light_bricks);
	public static Tile light_grass = new light_grass(Sprite.light_grass);
	public static Tile rock_floor = new rock_floor(Sprite.rock_floor);
	public static Tile sandstone_bricks = new sandstone_bricks(Sprite.sandstone_bricks);
	public static Tile sandy_rock = new sandy_rock(Sprite.sandy_rock);
	public static Tile sand = new sand(Sprite.sand);
	
	public static Tile spawn_floor_tile=new spawnFloorTile(Sprite.spawn_floor);
	public static Tile spawn_grass_tile=new spawnGrassTile(Sprite.spawn_grass);
	public static Tile spawn_hedge_tile=new spawnHedgeTile(Sprite.spawn_hedge);
	public static Tile spawn_wall_1_tile=new spawnWall1Tile(Sprite.spawn_wall1);
	public static Tile spawn_wall_2_tile=new spawnWall2Tile(Sprite.spawn_wall2);
	public static Tile spawn_water_tile=new spawnWaterTile(Sprite.spawn_water);
	
	
	public static Tile tree = new tree(Sprite.tree);
	
	public static final int col_spawn_grass=0xff00ff00;
	public static final int col_spawn_hedge=0xff006600;
	public static final int col_spawn_water=0xff036ffc;
	public static final int col_spawn_wall1=0xff808080;
	public static final int col_spawn_wall2=0xff303030;
	public static final int col_spawn_floor=0xff724715;
	public static final int col_dungeon_bricks = 0xff444444;
	public static final int col_grassy_rock = 0xff6699ff;
	public static final int col_light_bricks = 0xffffcc99;
	public static final int col_light_grass = 0xff00cc33;
	public static final int col_rock_floor = 0xffdddddd;
	public static final int col_sandstone_bricks = 0xffffff99;
	public static final int col_carpet = 0xffffaaaa;
	public static final int col_sandy_rock = 0xffcccc99;
	public static final int col_sand = 0xffFFFF2D;
	public static final int col_tree = 0xff9CFF2B;
	public static final int col_cactus = 0xff9CDD2C;
	public Tile(Sprite sprite)
	{
		this.sprite=sprite;
	}
	
	public void render(int x, int y, Screen screen)
	{
		
	}
	public boolean solid()
	{
		return false;
	}
	
}
