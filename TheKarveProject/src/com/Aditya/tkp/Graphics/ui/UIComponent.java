package com.Aditya.tkp.Graphics.ui;

import java.awt.Graphics;

import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.util.Vector2i;

public class UIComponent {
	
	public int backgroundColour;
	public Vector2i position,offset;
	public Vector2i size;
	public static boolean isActive;
	public UIComponent(Vector2i position, Vector2i size) {
		this.position=position;
		this.size = size;
		offset=new Vector2i();
	}
	public UIComponent() {
		
	}
	public UIComponent(Vector2i position) {
		this.position=position;
		offset=new Vector2i();
	}
	
	public void update(){
		
	}
	
	public void render(Graphics g) {
		
	}
	public Vector2i getAbsolutePosition(){
		return new Vector2i(position).add(offset);
	}
	public void setOffset(Vector2i position2) {
		// TODO Auto-generated method stub
		this.offset=offset;
	}
	
}
