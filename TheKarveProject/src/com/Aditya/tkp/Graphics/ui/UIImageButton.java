package com.Aditya.tkp.Graphics.ui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.Aditya.tkp.Input.Mouse;
import com.Aditya.tkp.util.Vector2i;

public class UIImageButton extends UIComponent {
	private UIButtonListener buttonListener;
	private UIActionListener actionListener;
	private boolean inside = false;
	private boolean pressed = false;
	private boolean ignorePressed = false;
	private boolean ignoreAction = false;
	boolean mouseDown = false;
	BufferedImage baseImage = null;
	BufferedImage hoverImage = null;
	BufferedImage currentImage = null;
	public UIImageButton(Vector2i position, BufferedImage baseImage, BufferedImage hoverImage, UIActionListener listener) {
		super(position, new Vector2i(baseImage.getWidth(), baseImage.getHeight()));
		this.actionListener = listener;
		this.baseImage = baseImage;
		this.hoverImage = hoverImage;
		this.size = new Vector2i(baseImage.getWidth(),baseImage.getHeight());
		setImage(baseImage);
		init();
	}
	private void init() {
		buttonListener = new UIButtonListener();
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
			buttonListener.entered(this,hoverImage);
			if(Mouse.getB() == 1) {
				buttonListener.pressed(this,baseImage);
				actionListener.perform();
				ignorePressed = true;
			}
		}
		if(!contains) {
			buttonListener.exited(this,baseImage);
		}
		
	}

	public void setImage(BufferedImage image) {
		currentImage = image;
	}
	
	public void render(Graphics g) {
		int x =position.x + offset.x;
		int y =position.y + offset.y;
		g.drawImage(currentImage,x,y,null);
	}

}
