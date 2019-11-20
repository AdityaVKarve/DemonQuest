package com.Aditya.tkp.Entity.mob;

import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.Graphics.Sprite;
import com.Aditya.tkp.level.PlayerPosition;

public class web extends Mob {

	public web(int x, int y) {
		this.x = x<<4;
		this.y = y<<4;
		sprite = Sprite.web;
		
	}
	@Override
	public void render(Screen screen) {
		// TODO Auto-generated method stub
		screen.renderMob((int)x, (int)y, this,false,0);
		
	}
	public void isInside() {
		if(Math.abs(PlayerPosition.x - x) < 16 && Math.abs(PlayerPosition.y-y)<16) {
			level.getClientPlayer().playerSpeed = speed * 0.2;
		}
		else level.getClientPlayer().playerSpeed = speed;
	}
	public void update() {
		isInside();
	
	}
}