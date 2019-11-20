package com.Aditya.tkp.Graphics.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class UIQuestDisplay extends UIComponent {
	String text;
	
	public UIQuestDisplay(String text) {
		this.text = text;
	}
	public void render(Graphics g) {
		g.setColor(new Color(0xFFFFFF));
		g.setFont(new Font("Verdana", Font.BOLD, 15));
		g.drawString("Active quest-", 890, 390);
		g.drawRect(890, 400, 300, 100);
		g.drawString(text, 895, 420);
	}
}
