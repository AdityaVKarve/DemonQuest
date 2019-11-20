package com.Aditya.tkp.Graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int SIZE;
	public final int SPRITE_WIDTH, SPRITE_HEIGHT;
	private int width, height;
	public int[] pixels;

	public static SpriteSheet tiles = new SpriteSheet("/sheets/spritesheet.png", 256);
	public static SpriteSheet spawn_level = new SpriteSheet("/sheets/spawn_level.png", 48);
	public static SpriteSheet projectile_wizard = new SpriteSheet("/sheets/Projectiles/wizard.png", 48);
	public static SpriteSheet bush1 = new SpriteSheet("/sheets/bush2.png",32);
	public static SpriteSheet berry_bush = new SpriteSheet("/sheets/berry_bush.png",32);
	public static SpriteSheet chest = new SpriteSheet("/sheets/chest.png",32);
	public static SpriteSheet chestOpen = new SpriteSheet("/sheets/chest_open.png",32);
	public static SpriteSheet throne = new SpriteSheet("/sheets/throne.png",32);
	public static SpriteSheet table = new SpriteSheet("/sheets/table.png",32);
	public static SpriteSheet chairLeft = new SpriteSheet("/sheets/chair_left.png",32);
	public static SpriteSheet chairRight = new SpriteSheet("/sheets/chair_right.png",32);
	public static SpriteSheet chairFront = new SpriteSheet("/sheets/chair_front.png",32);
	public static SpriteSheet bed = new SpriteSheet("/sheets/bed.png",32);
	public static SpriteSheet web = new SpriteSheet("/sheets/web.png",32);
	public static SpriteSheet spirit = new SpriteSheet("/sheets/spirit.png",32);
	public static SpriteSheet well = new SpriteSheet("/sheets/well.png",32);
	public static SpriteSheet dummy=new SpriteSheet("/sheets/king_cherno.png",128,96);
	public static SpriteSheet dummy_down = new SpriteSheet(dummy, 0, 0, 1, 3, 32);
	public static SpriteSheet dummy_up = new SpriteSheet(dummy, 1, 0, 1, 3, 32);
	public static SpriteSheet dummy_left = new SpriteSheet(dummy, 2, 0, 1, 3, 32);
	public static SpriteSheet dummy_right = new SpriteSheet(dummy, 3, 0, 1, 3, 32);
	private Sprite[] sprites;
	
	public static SpriteSheet mobs = new SpriteSheet("/sheets/RPGCharacterSprites32x32.png",384,672);
	public static SpriteSheet bat = new SpriteSheet("/sheets/bat.png",128,128);
	
	public static SpriteSheet player_down = new SpriteSheet(mobs,0,4,4,1,32);
	public static SpriteSheet player_up = new SpriteSheet(mobs,4,4,4,1,32);
	public static SpriteSheet player_right = new SpriteSheet(mobs,8,4,4,1,32);
	
	public static SpriteSheet rogue_down = new SpriteSheet(mobs,0,1,4,1,32);
	public static SpriteSheet rogue_up = new SpriteSheet(mobs,4,1,4,1,32);
	public static SpriteSheet rogue_right = new SpriteSheet(mobs,8,1,4,1,32);
	
	public static SpriteSheet gunfighter_down = new SpriteSheet(mobs,0,9,4,1,32);
	public static SpriteSheet gunfighter_up = new SpriteSheet(mobs,4,9,4,1,32);
	public static SpriteSheet gunfighter_right = new SpriteSheet(mobs,8,9,4,1,32);
	public static SpriteSheet lucifer_down = new SpriteSheet(mobs,0,11,4,1,32);
	public static SpriteSheet lucifer_up = new SpriteSheet(mobs,4,11,4,1,32);
	public static SpriteSheet lucifer_right = new SpriteSheet(mobs,8,11,4,1,32);
	public static SpriteSheet bat_up = new SpriteSheet(bat, 0,0,4,1,32);
	public static SpriteSheet bat_down = new SpriteSheet(bat, 0,2,4,1,32);
	public static SpriteSheet bat_right = new SpriteSheet(bat, 0,1,4,1,32);
	public static SpriteSheet villager_down = new SpriteSheet(mobs,0,2,4,1,32);
	public static SpriteSheet villager_up = new SpriteSheet(mobs,4,2,4,1,32);
	public static SpriteSheet villager_right = new SpriteSheet(mobs,8,2,4,1,32);
	public static SpriteSheet summoner_down = new SpriteSheet(mobs,0,6,4,1,32);
	public static SpriteSheet summoner_up = new SpriteSheet(mobs,4,6,4,1,32);
	public static SpriteSheet summoner_right = new SpriteSheet(mobs,8,6,4,1,32);
	
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		if (width == height) SIZE = width;
		else SIZE = -1;
		SPRITE_WIDTH = w;
		SPRITE_HEIGHT = h;
		pixels = new int[w * h];
		for (int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.SPRITE_WIDTH];
			}
		}
		int frame = 0;
		sprites = new Sprite[width * height];
		for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize * spriteSize];
				for (int y0 = 0; y0 < spriteSize; y0++) {
					for (int x0 = 0; x0 < spriteSize; x0++) {
						spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * SPRITE_WIDTH];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}
	}

	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		SPRITE_WIDTH = size;
		SPRITE_HEIGHT = size;
		load();
	}

	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		SIZE = -1;
		SPRITE_WIDTH = width;
		SPRITE_HEIGHT = height;
		pixels = new int[SPRITE_WIDTH * SPRITE_HEIGHT];
		load();
	}

	public Sprite[] getSprites() {
		return sprites;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int[] getPixels() {
		return pixels;
	}

	private void load() {
		try {
			System.out.print("Trying to load: " + path + "...");
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			System.out.println(" succeeded!");
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println(" failed!");
		}

	}

}