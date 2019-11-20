package com.Aditya.tkp;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.Aditya.tkp.Entity.mob.Player;
import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.Graphics.Sprite;
import com.Aditya.tkp.Graphics.ui.EnvironmentUI;
import com.Aditya.tkp.Graphics.ui.UIManager;
import com.Aditya.tkp.Input.Keyboard;
import com.Aditya.tkp.Input.Mouse;
import com.Aditya.tkp.level.Level;
import com.Aditya.tkp.level.RandomLevel;
import com.Aditya.tkp.level.SpawnLevel;
import com.Aditya.tkp.util.DialogueLoader;
import com.Aditya.tkp.Graphics.RenderFont;
public class Game extends Canvas implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3073721992315820541L;

	
	public static int scale=4;
	public static int width=300-80;
	public static int height=width/16*10;
	private final int RED=0xff0000;
	private final int GREEN=0x00ff00;
	private final int BLUE=0x0000ff;
	private final int BLACK=0x000000;
	private final int WHITE=0xffffff;
	private Thread thread;
	Mouse mouse=new Mouse();
	private JFrame frame;
	private boolean isRunning=false;
	private Screen screen;
	private BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);	//We want to convert an image object into an array of integers to modify it easily
	private int[] pixels=((DataBufferInt)image.getRaster().getDataBuffer()).getData();		//A raster is an array of pixels in an image
	private Player player;
	private Level level=new SpawnLevel("/levels/carpet_level.png");;
	private Keyboard key;
	private static UIManager ui;
	private static EnvironmentUI eui;
	private RenderFont font;
	private DialogueLoader dl;
	//static Game game=new Game();
	public Game(String name)
	{
		Dimension size=new Dimension(width*scale + 80*scale,height*scale);
		ui=new UIManager();
		eui = new EnvironmentUI();
		setPreferredSize(size);
		screen=new Screen(width, height);
		frame=new JFrame();
		level=Level.carpet;
		key=new Keyboard();
		player=new Player(name,365*16,407*16,key);	
		level.add(player);
		font=new RenderFont();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		addKeyListener(key);	//Adds a key listener to Canvas
		dl = new DialogueLoader("/dialog/dialogue.dlg");
		
	}
	public synchronized void start()
	{
		isRunning=true;
		thread=new Thread(this,"Display");
		thread.start();
	}
	public synchronized void stop()
	{
		isRunning=false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		dl.getDialogue(2);
		// TODO Auto-generated method stub
		long lastTime=System.nanoTime();
		final double ns=1000000000.0/60.0;
		long timer1s=System.currentTimeMillis();
		double delta=0;
		int frames=0;
		int updates=0;
		requestFocus();
		while(isRunning)
		{
			long now=System.nanoTime();
			delta=(now-lastTime)/ns;
			//System.out.println("Running...");
			if(delta>=1)
			{
				lastTime=System.nanoTime();
				delta--;
				updates++;
				update();	//Updates game mathematics like player position, 60 times a second
			}
			render();	//Draws things to the screen, this happens infinite times
			frames++;	
			if(System.currentTimeMillis()-timer1s>=1000)
			{
				//System.out.println("FPS-"+frames+"\nups-"+updates);
				frames=0;
				updates=0;
				timer1s+=1000;
			}
		}
		stop();
	}
	public void update()
	{
		key.update();
		ui.update();
		eui.update();
		level.update();	
	}
	public static UIManager getUIManager()
	{
		return ui;
	}
	public void render()
	{
		BufferStrategy bs=getBufferStrategy();	//This allocates a location in the RAM for the storage of frames before display, to smoothen performance
		if(bs==null)	//We only want to create a buffer strategy if there isn't one, else it's pointless
		{
			createBufferStrategy(3);	//We choose 3 as that means 2 back buffers, where we can work on next frame while the current frame is yet to be displayed
			return;
		}
		Graphics g=bs.getDrawGraphics();	//Creates a link b/w graphics & Buffer
		{
			g.setColor(Color.WHITE);	
			g.fillRect(0, 0, getWidth(), getHeight());
			screen.clear();
			int xScroll=(int) (player.getX()-screen.width/2);
			int yScroll=(int) (player.getY()-screen.height/2);
			level.render(xScroll, yScroll, screen);
			
			for(int i=0;i<pixels.length;i++)
				pixels[i]=screen.pixels[i];
			g.drawImage(image, 0, 0, width*scale, height*scale,null);
			//g.drawImage(image, 0, 0, getWidth(), getHeight(),null);
			g.drawLine(mouse.getX()+10, mouse.getY(), mouse.getX()-10, mouse.getY());
			g.drawLine(mouse.getX(), mouse.getY()+10, mouse.getX(), mouse.getY()-10);
			ui.render(g);
			eui.render(g);
			//System.out.println(mouse.getY());
		}	
		g.dispose();	//We use this to dispose of wasteful memory allocated to old graphics
		bs.show();	//Will move on to the next developed buffer
	}	
	public static int getWindowWidth()
	{
		return width*scale;
	}
	public static int getWindowHeight()
	{
		return height*scale;
	}
	
	public static void main(Game game)
	{
		
		game.frame.setResizable(false);
		game.frame.setTitle("TKP");
		game.frame.add(game);	//We can add game to window as Game extends canvas
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Doing this closes the program with the window
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}
}
