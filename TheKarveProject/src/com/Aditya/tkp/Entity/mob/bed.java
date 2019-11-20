package com.Aditya.tkp.Entity.mob;

import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.Graphics.Sprite;

public class bed extends Mob {

	public bed(int x, int y) {
		this.x = x<<4;
		this.y = y<<4;
		sprite = Sprite.bed;
		
	}
	@Override
	public void render(Screen screen) {
		// TODO Auto-generated method stub
		screen.renderMob((int)x, (int)y, this,false,0);
		
	}
	
}