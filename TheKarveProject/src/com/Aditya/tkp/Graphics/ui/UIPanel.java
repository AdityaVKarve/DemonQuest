package com.Aditya.tkp.Graphics.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.Graphics.Sprite;
import com.Aditya.tkp.util.Vector2i;

public class UIPanel extends UIComponent{

	private ArrayList<UIComponent> components=new ArrayList<UIComponent>();
	private Vector2i position,size;
	private Color color;
	public UIPanel(Vector2i position, Vector2i size) {
		super(position);
		this.position=position;
		this.size=size;
		color=new Color(0xFF333333, true);
	}
	public void addComponent(UIComponent component) {
		components.add(component);
	}
	  
	public void update() {
		for(UIComponent component : components) {
			component.setOffset(position);
			component.update();
		}
	}
	public UIPanel setColor(int color) {
		this.color=new Color(color,true);
		return this;
	}
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(position.x, position.y, size.x, size.y);
		for(UIComponent component : components) {
			component.render(g);
		}
	}
}
