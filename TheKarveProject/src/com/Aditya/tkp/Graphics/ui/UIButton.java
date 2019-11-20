package com.Aditya.tkp.Graphics.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.Aditya.tkp.Input.Mouse;
import com.Aditya.tkp.util.Vector2i;

public class UIButton extends UIComponent {
	//private UIButtonListener buttonListener;
	private Color foregroundColor;
	private Color textColor;
	private UIButtonListener buttonListener;
	private UIActionListener actionListener;
	public UILabel label;
	private Vector2i size;
	int defaultColour;
	private boolean inside = false;
	private boolean pressed = false;
	private boolean ignorePressed = false;
	private boolean ignoreAction = false;
	boolean mouseDown = false;
	private Image image;	//button icon
	public UIButton(Vector2i position, Vector2i size, UIActionListener actionListener, int defaultColour) {
		super(position ,size);
		this.size = size;
		this.actionListener = actionListener;
		this.defaultColour = defaultColour;
		init();
	}
	@Deprecated
	public UIButton(Vector2i position, BufferedImage image, UIActionListener listener) {
		super(position, new Vector2i(image.getWidth(), image.getHeight()));
		this.actionListener = listener;
		this.size = new Vector2i(image.getWidth(),image.getHeight());
		setImage(image);
		init();
		
	}
	public void setButtonListener(UIButtonListener buttonListener) {
		this.buttonListener = buttonListener;
	}
	private void init() {
		setForegroundColor(defaultColour);
		buttonListener = new UIButtonListener();
	}
	
	public void setForegroundColor(int color) {
		this.foregroundColor = new Color(color);
		
	}
	public void setText(String text,int color) {
		this.textColor = new Color(color);
		label= new UILabel(new Vector2i(position.x + offset.x +20, position.y + offset.y+size.y/2+10),text,16);
		label.setColor(color);
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public void update() {
		
		Rectangle rect = new Rectangle(getAbsolutePosition().x,getAbsolutePosition().y,size.x,size.y);
		boolean contains = rect.contains(new Point(Mouse.getX(), Mouse.getY())); 
		if(Mouse.getB() == 1 && !contains) {
			ignorePressed = true;
		}
		if(Mouse.getB() != 1) {
			ignorePressed = false;
		}
		if(contains && !ignorePressed) {
			buttonListener.entered(this);
			if(Mouse.getB() == 1) {
				buttonListener.pressed(this);
				actionListener.perform();
				ignorePressed = true;
			}
		}
		if(!contains) {
			buttonListener.exited(this);
		}
		
	}
		
	
	public void render(Graphics g) {
		int x =position.x + offset.x;
		int y =position.y + offset.y;
		g.setColor(foregroundColor);
		g.fillRect(x, y, size.x, size.y);
		if(label.text != null)
			label.render(g);
		
	}
	
	
	
}
