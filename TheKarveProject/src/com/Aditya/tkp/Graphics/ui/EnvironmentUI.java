package com.Aditya.tkp.Graphics.ui;

import java.awt.Graphics;
import java.util.ArrayList;

public class EnvironmentUI {
private static ArrayList<UIComponent> items=new ArrayList<UIComponent>();
	
	public EnvironmentUI(){
		
	}
	
	public void addItem(UIComponent component) {
		items.add(component);
	}
	
	
	public void update() {
		for(UIComponent item : items) {
			item.update();
			//System.out.println(items.size());
		}
	}
	public void render(Graphics g) {
		
		for(UIComponent item : items) {
			item.render(g);
			//System.out.println(items);
		}
	}
}
