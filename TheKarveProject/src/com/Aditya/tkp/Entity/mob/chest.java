package com.Aditya.tkp.Entity.mob;

import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.Graphics.Sprite;
import com.Aditya.tkp.Graphics.ui.NamePlate;
import com.Aditya.tkp.level.PlayerPosition;
import com.Aditya.tkp.util.Vector2i;

public class chest extends Mob {
	boolean isOpened = false;
	int healthPotion, attackPotion, eye, heart, brain;
	public chest(int x, int y, int contents[]) {
		this.x = x<<4;
		this.y = y<<4;
		sprite = Sprite.chest;
		healthPotion = contents[0];
		attackPotion = contents[1];
		eye = contents[2];
		heart = contents[3];
		brain = contents[4];
		name = new NamePlate("Chest",false,new Vector2i(x,y));
		name.isActive = true;
		eui.addItem(name);
	}
	@Override
	public void render(Screen screen) {
		// TODO Auto-generated method stub
		screen.renderMob((int)x, (int)y, this,false,0);
		
	}
	public void update() {
		name.setPosition((int)x, (int)y);
		if(Math.abs(PlayerPosition.x-x)<=16&&Math.abs(PlayerPosition.y-y)<=16&&Player.input.enter&&!isOpened) {
			healthPotionCount+=healthPotion;
			attackPotionCount+=attackPotion;
			eyeCount+=eye;
			heartCount+=heart;
			brainCount+=brain;
			sprite = Sprite.chest_open;
			isOpened = true;
		}
	}

}
