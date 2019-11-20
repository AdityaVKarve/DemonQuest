package com.Aditya.tkp.Entity.mob;

import com.Aditya.tkp.Graphics.Screen;

public class Quest1 extends Mob {
	String description;
	int xp;
	boolean isActive = false;
	boolean isComplete = false;
	public Quest1() {
		description = "Kill 5 rogues";
		xp = 100;
	}
	public void update() {
		if(level.getClientPlayer().getRogue() == 5) {
			isComplete = true;
			isActive = false;
		}
	}
	@Override
	public void render(Screen screen) {
		// TODO Auto-generated method stub
		
	}
}
