package com.Aditya.tkp.Entity.mob;

import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.Graphics.Sprite;

public class chair extends Mob {
	int orientation;
	//0-front,1-left,2-right 
	public chair(int x, int y,int orientation) {
		this.x = x<<4;
		this.y = y<<4;
		this.orientation = orientation;
		sprite = Sprite.chairFront;
	}
	
	@Override
	public void render(Screen screen) {
		if(orientation == 1){
			sprite = Sprite.chairLeft;
		}
		if(orientation == 2)sprite = Sprite.chairRight;
		// TODO Auto-generated method stub
		screen.renderMob((int)x, (int)y, this,false,0);
		
	}
	
}