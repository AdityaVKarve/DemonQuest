package com.Aditya.tkp.Entity.mob;

import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.Graphics.Sprite;
import com.Aditya.tkp.Graphics.ui.NamePlate;
import com.Aditya.tkp.util.Vector2i;

public class throne extends Mob {

	public throne(int x, int y) {
		this.x = x<<4;
		this.y = y<<4;
		sprite = Sprite.throne;
		
	}
	@Override
	public void render(Screen screen) {
		// TODO Auto-generated method stub
		screen.renderMob((int)x, (int)y, this,false,0);
		
	}
	
}