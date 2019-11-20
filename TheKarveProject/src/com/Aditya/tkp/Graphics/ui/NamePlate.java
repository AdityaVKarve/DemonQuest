package com.Aditya.tkp.Graphics.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import com.Aditya.tkp.Game;
import com.Aditya.tkp.Entity.mob.Player;
import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.level.PlayerPosition;
import com.Aditya.tkp.util.Vector2i;

public class NamePlate extends UIComponent{
	private boolean isUnique;
	public boolean isActive;
	private Color normalMob = new Color(0xffffff);
	private Color uniqueMob = new Color(0xff00ff);
	private String name;
	private static String str;
	private Game game;
	private Vector2i currentPosn;
	NamePlate np;
	PlayerPosition pp;
	public NamePlate(String name, boolean isUnique, Vector2i posn){
		super(posn);
		this.isUnique=isUnique;
		this.name = name;
		currentPosn = posn;
	}
	
	public void render(Graphics g) {
		g.setFont(new Font("Helvetica",Font.BOLD,15));
		if(isUnique)
			g.setColor(uniqueMob);
		else
			g.setColor(normalMob);
		int var = currentPosn.x;
		//str = Integer.toString(currentPosn.x);
		renderString(g,name);
	}
	public void setPosition(int x, int y) {
		currentPosn.x = x;
		currentPosn.y = y;
		//np = new NamePlate(name,isUnique,posn);
	}
	public void renderString(Graphics g, String str) {
		
		if(Math.abs(pp.x-currentPosn.x) <110 && Math.abs(pp.y-currentPosn.y)<102&&isActive)
			g.drawString(str, (Screen.width/2+380)+(currentPosn.x - pp.x)*4,Screen.height/2+160+(currentPosn.y-pp.y)*4);
		//System.out.println(isActive);
	}
	
}
