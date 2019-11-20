package com.Aditya.tkp.Entity;

import java.util.Random;

import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.Graphics.Sprite;
import com.Aditya.tkp.Graphics.ui.EnvironmentUI;
import com.Aditya.tkp.Graphics.ui.NamePlate;
import com.Aditya.tkp.level.Level;

public class Entity
{
	protected  double x;
	protected  double y;
	protected Sprite sprite;
	private boolean removed = false;
	protected Level level;
	protected int health;
	protected int currentHealth;
	protected boolean hurt = false;
	public NamePlate name;
	protected final Random random = new Random();
	public static boolean isAlive;
	protected EnvironmentUI eui = new EnvironmentUI();;
	public Entity() {
	}
	public Entity(int x, int y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}

	public void update() {
	} 
	
	public void loseHealth(int damage) {
		currentHealth -=damage;
	}
	
	public void gainHealth(int heal) {
		currentHealth += heal;
	}
	
	public int getHealth() {
		return currentHealth;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	public void render(Screen screen) {
		if (sprite != null) screen.renderSprite((int)x, (int)y, sprite, true);
	}
	public void isHurt() {
		hurt = true;
	}
	public void remove() {
		//Remove from level
		removed = true;
	}

	public int getX() {
		return (int)x;
	}
	
	public int getY() {
		return (int)y;
	}
	
	public Sprite getSprite() {
		return sprite;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void init(Level level) {
		this.level = level;
	}
}