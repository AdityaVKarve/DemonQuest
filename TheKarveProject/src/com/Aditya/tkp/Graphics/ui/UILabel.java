package com.Aditya.tkp.Graphics.ui;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.Aditya.tkp.Graphics.RenderFont;
import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.util.Vector2i;

public class UILabel extends UIComponent{
	private Color color;
	public String text; 
	private Font font;
	Graphics g;
	public UILabel(Vector2i position, String text, int size) {
		super(position);		
		font = new Font("Helvetica", Font.BOLD, size);
		this.text = text;
		color = new Color(0xBBBBBB);
		// TODO Auto-generated constructor stub
	}
	public UILabel setColor(int color) {
		this.color=new Color(color);
		return this;
	}
	public UILabel setFont(Font font) {
		this.font=font;
		return this;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void render(Graphics g) {
		this.g = g;
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, position.x + offset.x, position.y + offset.y);
		
	}
	public void update() {
		
	}
	
	
}
