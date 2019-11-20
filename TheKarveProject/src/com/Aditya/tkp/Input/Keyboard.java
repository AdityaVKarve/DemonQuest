package com.Aditya.tkp.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
	
	public boolean[] keys=new boolean[120];		//all keys in a keyboard
	public boolean up,down,left,right, enter, e, shift;
	public void update()
	{
		up=keys[KeyEvent.VK_UP]||keys[KeyEvent.VK_W];
		down=keys[KeyEvent.VK_DOWN]||keys[KeyEvent.VK_S];
		left=keys[KeyEvent.VK_LEFT]||keys[KeyEvent.VK_A];
		right=keys[KeyEvent.VK_RIGHT]||keys[KeyEvent.VK_D];
		shift = keys[KeyEvent.VK_SHIFT];
		enter = keys[KeyEvent.VK_ENTER];
		e = keys[KeyEvent.VK_E];
	//	System.out.println(up);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		keys[e.getKeyCode()]=true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		keys[e.getKeyCode()]=false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
