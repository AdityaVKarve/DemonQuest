package com.Aditya.tkp.Graphics.ui;

import java.awt.Graphics;
import java.util.ArrayList;

import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.util.Debug;

public class UIManager {

	private ArrayList<UIPanel> panels=new ArrayList<UIPanel>();
	
	public UIManager(){
		
	}
	
	public void addPanel(UIPanel panel) {
		panels.add(panel);
	}
	
	
	public void update() {
		for(UIPanel panel : panels) {
			panel.update();
		}
	}
	public void render(Graphics g) {
		for(UIPanel panel : panels) {
			panel.render(g);
		}
	}
}
