package com.Aditya.tkp.Graphics.ui;

import java.awt.image.BufferedImage;

public class UIButtonListener {
	public void entered(UIButton button) {
		button.setForegroundColor(0x22dd22);
	}
	public void entered(UIImageButton button, BufferedImage image) {
		button.setImage(image);
	}
	public void exited(UIButton button) {
		button.setForegroundColor(0x11aa11);
	}
	public void exited(UIImageButton button, BufferedImage image) {
		button.setImage(image);
	}
	
	public void pressed(UIButton button) {
		button.setForegroundColor(0xaaaaaa);
	}
	public void pressed(UIImageButton button, BufferedImage image) {
		button.setImage(image);
	}
	public void released(UIButton button) {
		
	}
	
}
