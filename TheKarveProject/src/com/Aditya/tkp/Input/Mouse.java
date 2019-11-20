package com.Aditya.tkp.Input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener,MouseMotionListener {

	private static int mouseX=-1;	//Mouse X coordinate
	private static int mouseY=-1;	//Mouse Y coordinate 
	private static int mouseB=-1;	//Mouse button
	
	public static int getX()
	{
		//System.out.println("X:"+mouseX);
		return mouseX;
	}
	public static int getY()
	{
		//System.out.println("Y:"+mouseY);
		return mouseY;
	}
	public static int getB()
	{
		return mouseB;
	}
	@Override	
	public void mouseClicked(MouseEvent e) {
			
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		 	
	}
	@Override
	public void mouseExited(MouseEvent e) {
		 
	}

	@Override
	public void mousePressed(MouseEvent e) {
		 mouseB=e.getButton();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		 mouseB=-1;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		 	mouseX=e.getX();
		 	mouseY=e.getY();
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		 mouseX=e.getX();
		 mouseY=e.getY();	
	}
}
