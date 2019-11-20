package com.Aditya.tkp.Graphics.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.Aditya.tkp.Input.Keyboard;
import com.Aditya.tkp.util.DialogueLoader;
import com.Aditya.tkp.util.Vector2i;

public class UIDialogueBox extends UIComponent {
	//64
	private Color color = new Color(0xdddddd);
	public String text; 
	private Font font = new Font("Helvetica", Font.BOLD, 20);
	private DialogueLoader dl;
	private Keyboard input;
	private int dialogNumber;
	private boolean isActive = false;
	private static boolean isFinished;
	static int ctr;
	String displayString = "";
	static long currentTime = 0;
	static boolean isComplete;
	public UIDialogueBox(int dialogNumber, Vector2i position, Keyboard input) {
		super(position);
		this.dialogNumber = dialogNumber;
		dl = new DialogueLoader("/dialog/dialogue.dlg");
		text = dl.getDialogue(dialogNumber);
		isComplete = false;
		this.input = input;
		activate();
		isFinished = false;
		ctr = 0;
	}
	public void activate() {
		isActive = true;
	}
	public boolean isActive() {
		return isActive;
	}
	public void render(Graphics g) {
		System.out.println(ctr);
		if(isActive) {
			
			g.setColor(new Color(0xffffff));
			g.drawRect(130, 410, 700, 60);
			int renderSize = 0;
			if(displayString.length() == text.length())
				isComplete = true;
			if(!isComplete) {
				
				displayString = "";
				for(int i =64*ctr; i < 64 +ctr*64; i ++) {
					if(i<text.length()) {
						displayString +=  text.charAt(i);
						
					}
					else {
						isFinished = true;
						break;
					}
				}
				//System.out.println("RUN "+displayString+" "+text);
			}
			renderString(g,displayString);
			
			if(input.enter && !isFinished) {
				if(System.nanoTime() - currentTime > 1000000000) {
					currentTime = System.nanoTime();
					ctr++;
					isComplete = false;
					
				}
				
			}
			else if(isFinished && input.enter) {
				if(System.nanoTime() - currentTime > 1000000000)
					isActive = false;
			}
			
		}
		
		
	}
	public void renderString(Graphics g, String s) {
		g.setColor(color);
		g.setFont(font);
		g.drawString(s,170,450);
	}
}
