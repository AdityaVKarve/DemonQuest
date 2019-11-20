package com.Aditya.tkp.Entity.mob;

import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.Graphics.Sprite;

public class berryBush extends Mob{
	
	public berryBush(int x, int y)
	{
		sprite = Sprite.fruitTree;
		this.x=x<<4;
		this.y=y<<4;
		health = 300;
		currentHealth = health;
	}
	@Override
	public void render(Screen screen) {
		// TODO Auto-generated method stub
		//System.out.println("RUN");
		screen.renderMob((int)x, (int)y, this,false,0);
	}
}
