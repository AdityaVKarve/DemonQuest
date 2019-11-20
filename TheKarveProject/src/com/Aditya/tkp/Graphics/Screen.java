package com.Aditya.tkp.Graphics;

import java.util.Random;

import com.Aditya.tkp.Entity.Projectile.Projectile;
import com.Aditya.tkp.Entity.mob.Chaser;
import com.Aditya.tkp.Entity.mob.Mob;
import com.Aditya.tkp.Entity.mob.Player;
import com.Aditya.tkp.Entity.mob.Star;
import com.Aditya.tkp.Tile.Tile;
import com.Aditya.tkp.Tile.tree;
	
public class Screen {

	private final int MAP_SIZE=64;
	private final int MAP_SIZE_MASK=MAP_SIZE-1;
	public static int xOffset;
	public static int yOffset;
	public int[] tiles=new int[MAP_SIZE*MAP_SIZE];	//Set array of tiles
	private Random random=new Random();
	public static int width;
	public static int height;
	public static int[] pixels;
	private Mob mob;
	private final static int ALPHA_COL = 0xffff00ff;
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height]; // 0 - 50,399 = 50,400

		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
			tiles[0] = 0;
		}

	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public static void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
		int xpp = xp;
		int ypp = yp;
		if (fixed) {
			xpp -= xOffset;
			ypp -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + ypp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xpp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				int col = sprite.pixels[x + y * sprite.getWidth()];
				if (col != ALPHA_COL && col != 0xff7f007f) pixels[xa + ya * width] = col;
			}
		}
	}
	public static void renderTextCharacter(int xp, int yp, Sprite sprite,int color,boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				int col = sprite.pixels[x + y * sprite.getWidth()];
				if (col != ALPHA_COL && col != 0xff7f007f) pixels[xa + ya * width] = color;
			}
		}
	}

	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		if(tile instanceof tree) {
			System.out.println("RUN");
			for (int y = 0; y < tile.grass.sprite.SIZE; y++) {
				int ya = y + yp;
				for (int x = 0; x < tile.sprite.SIZE; x++) {
					int xa = x + xp;
					if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
					if (xa < 0) xa = 0;
					pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
				}
			}
			for (int y = 0; y < tile.sprite.SIZE; y++) {
				int ya = y + yp;
				for (int x = 0; x < tile.sprite.SIZE; x++) {
					int xa = x + xp;
					if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
					if (xa < 0) xa = 0;
					pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
				}
			}
		}
		else
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yp;
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}
	}

	public void renderProjectile(double d, double e, Projectile p) {
		d -= xOffset;
		e -= yOffset;
		for (int y = 0; y < p.getSpriteSize(); y++) {
			int ya = (int) (y + e);
			for (int x = 0; x < p.getSpriteSize(); x++) {
				int xa = (int) (x + d);
				if (xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = p.getSprite().pixels[x + y * p.getSprite().SIZE];
				if (col != ALPHA_COL) pixels[xa + ya * width] = col;
			}
		}
	}

	public void renderMob(int xp, int yp, Mob mob,boolean hurt, int flip)
	{
		//System.out.println(sprite);
		xp-=xOffset;
		yp-=yOffset;
		for(int y=0;y<32;y++)
		{
			int ya=y+yp;
			int ys=y;
			for(int x=0;x<32;x++)
			{
				int xa=x+xp;
				int xs;
				if (flip == 1)
					xs = 31-x;
				else 
					xs = x;
				if(xa<-32||xa>=width||ya<0||ya>=height)
					break;
				if(xa<0)
					xa=0;
				int col;
				//System.out.println(mob.getSprite());
				col=mob.getSprite().pixels[xs+ys*32];
				if(hurt == false) {
					if(mob instanceof Chaser && col==0xff472BBF)
						col=0xffAA0000;
					if(mob instanceof Star && col==0xff472BBF)
						col=0xff00AA00;
					if(col!=0xFFFF00FF)	//If player colour!=pink
						pixels[xa+ya*width]=col;
				}
				else
				{
					//System.out.println("Hurt");
					if(col!=0xFFFF00FF)	//If player colour!=pink
						pixels[xa+ya*width]=0xff0000;
				}
					
				
			}
		}
	}
	

	

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void drawRect(int xp, int yp, int width, int height, int col, boolean fixed)
	{
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for(int x=xp;x<xp+width;x++)
		{
			if(x<0|x>=this.width|yp>=this.height)
				continue;
			if(yp>0)pixels[x+yp*this.width]=col;
			if(yp+height>=this.height)
				continue;
			if(yp+height>0)pixels[x+(yp+height)*this.width]=col;
		}
		for(int y=yp;y<=yp+height;y++)
		{
			if(xp>=this.width||y<0||y>=this.height)
				continue;
			if(xp>0)pixels[xp+y*this.width]=col;
			if(xp+width>=this.width)
				continue;
			if(xp+width>0)pixels[(xp+width)+y*this.width]=col;
		}
	}

}