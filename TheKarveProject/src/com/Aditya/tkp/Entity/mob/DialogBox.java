package com.Aditya.tkp.Entity.mob;

import com.Aditya.tkp.Graphics.RenderFont;
import com.Aditya.tkp.Graphics.Screen;

public class DialogBox extends Mob{
	private String dialog;
	private RenderFont renderfont;
	public DialogBox(String dialog) {
		this.dialog = dialog;
		renderfont = new RenderFont();
	}
	@Override
	public void render(Screen screen) {
		// TODO Auto-generated method stub
		screen.drawRect(screen.width/2 - 90, screen.height/2 + 34, 180, 30, 0xffffff, false);
		renderfont.render(10, screen.height/2 + 36, -5, dialog, 0xffffff, screen);
	}
	public void update() {
		
	}

}
