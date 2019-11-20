package com.Aditya.tkp.Graphics.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import org.w3c.dom.ranges.RangeException;

import com.Aditya.tkp.util.Vector2i;

public class UIProgressBar extends UIComponent {
	public double progress;
	private Vector2i size;
	
	private Color backgroundColor ;
	private Color foregroundColor;
	private int value;
	String text;
	public UIProgressBar(Vector2i position, Vector2i size,int backgroundColor, int value, String text) {
		// TODO Auto-generated constructor stub
		super(position);
		this.backgroundColor = new Color(backgroundColor);
		this.foregroundColor = new Color(0x222222);
		this.size = size;	
		this.value = value;
		this.text = text;
	}
	public void setForegroundColor(int color) {
		this.foregroundColor = new Color(color);
	}
	public void setBackgroundColor(int color) {
		this.backgroundColor = new Color(color);
	}
	public void setProgress(double Progress) {
		if(progress < 0 || progress > value) {
			throw new RangeException(RangeException.BAD_BOUNDARYPOINTS_ERR, "Progress must be between 0 and 100!");
		}
		this.progress = Progress;
	}
	
	public double getProgress() {
		return progress;
	}
	
	public void update() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(foregroundColor);
		g.fillRect(position.x + offset.x, position.y + offset.y, size.x, size.y);
		g.setColor(backgroundColor);
		g.fillRect(position.x + offset.x, position.y + offset.y, (int)(progress*size.x), size.y);
		g.setColor(new Color(0xFFFFFF));
		g.setFont(new Font("Verdana", Font.BOLD, 15));
		g.drawString(text,position.x + 10, position.y + size.y/2 + 5);
	}

}
