package com.Aditya.tkp.Entity.Projectile;

import java.util.Random;

import com.Aditya.tkp.Entity.Entity;
import com.Aditya.tkp.Graphics.Sprite;

public abstract class Projectile extends Entity {

	protected final int xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double x, y;
	protected double nx, ny;
	protected double distance;
	protected double speed, range;
	protected static double damage;
	protected static double mobDamage;
	protected final Random random = new Random(); 

	public Projectile(int x, int y, double dir) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public int getSpriteSize() {
		return sprite.SIZE;
	}

	protected void move() {
	}

}
