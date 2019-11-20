package com.Aditya.tkp.Entity.mob;


import java.util.ArrayList;

import com.Aditya.tkp.Graphics.AnimatedSprite;
import com.Aditya.tkp.Graphics.RenderFont;
import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.Graphics.SpriteSheet;
import com.Aditya.tkp.Graphics.ui.EnvironmentUI;
import com.Aditya.tkp.Graphics.ui.NamePlate;
import com.Aditya.tkp.Graphics.ui.UIDialogueBox;
import com.Aditya.tkp.level.PlayerPosition;
import com.Aditya.tkp.util.Node;
import com.Aditya.tkp.util.Vector2i;

public class Minion extends Mob{
	private AnimatedSprite up=new AnimatedSprite(SpriteSheet.bat_up,32,32,3);
	private AnimatedSprite down=new AnimatedSprite(SpriteSheet.bat_down,32,32,3);
	private AnimatedSprite right=new AnimatedSprite(SpriteSheet.bat_right,32,32,3);
	//private AnimatedSprite right=new AnimatedSprite(SpriteSheet.gunfighter_right,32,32,3);
	private AnimatedSprite animSprite=up;	 
	private int time=0;
	private double xa=0,ya=0;
	private boolean walking=false;
	private ArrayList<Node> path;
	protected int health;
	protected static int currentHealth;
	private double speed=0.4 +Math.random();
	int flip;
	boolean inRange;
	long clock;
	private UIDialogueBox db;
	public Minion(int x, int y)
	{
		clock = System.currentTimeMillis();
		this.x=x<<4;
		this.y=y<<4;
		sprite=animSprite.getSprite();
		health = 200;
		currentHealth = health;
		name = new NamePlate("Minion",false,new Vector2i(x,y));
		name.isActive = true;
		eui.addItem(name);
		inRange = false;
		isAlive = true;
	}
	@Override
	public void render(Screen screen) {
		// TODO Auto-generated method stub
		sprite=animSprite.getSprite();
		screen.renderMob((int)x, (int)y, this,hurt,flip);
		if(hurt == true) {
			ticks++;
			if(ticks >= hurtDuration) {
				ticks = 0;
				hurt = false;
			}
		}
		if(inRange) {
			//System.out.println("INRANGE");
			RenderFont e = new RenderFont();
			e.render(100, 0, 0, "e", 0xffffff, screen);
		}
	}
	private void move()
	{
		
		time++;
		xa=0;
		ya=0;	
		int px=(int) level.getPlayerAt(0).getX();
		int py=(int)level.getPlayerAt(0).getY();
		Vector2i start=new Vector2i((int)getX()>>4,(int)getY()>>4);
		Vector2i destination=new Vector2i(px>>4,py>>4);
		if(time%40==1)
			path=level.findPath(start, destination);
		if(path!=null)
		{
			if(path.size()>1)
			{
				Vector2i vec=path.get(path.size()-1).tile;
				//System.out.println("X coordinate-"+vec.getX());
				if(x<vec.x<<4) {
					xa++;
					flip =0;
				}
				if(x>vec.x<<4) {
					xa--;
					flip = 1;
				}
				if(y<vec.y<<4)
					ya++;
				if(y>vec.y<<4)
					ya--;
			}
		}
		if(xa!=0||ya!=0)
		{	
			move(xa,ya);
			walking=true;
		}
		else
		{
			walking=false;
		}
	}
	public void loseHealth(int damage) {
		System.out.println("Damage dealt");
		currentHealth -= damage;
	}
	
	public void gainHealth(int heal) {
		health += heal;
	}
	
	public int getHealth() {
		return currentHealth;
	}
	public void kamikaze() {
		PlayerPosition pp = new PlayerPosition();
		
		if(Math.sqrt(Math.pow((pp.x-x),2))+Math.pow((pp.y-y), 2)<50) {
			inRange = true;
			//System.out.println(System.currentTimeMillis() - clock );
			if(System.currentTimeMillis() - clock >= 2000) {
				level.getClientPlayer().loseHealth(100);
				clock = System.currentTimeMillis();
				System.out.println("Bite");
			}
			
		}
		else
			inRange = false;
	}
	public void update()
	{
		move();
		kamikaze();
		time++;
		if(time%(random.nextInt(50)+30)==0)
		{
			
			xa=random.nextInt(3)-1;
			ya=random.nextInt(3)-1;
			if(random.nextInt(4)==0)
			{
				//xa=0;
				//ya=0;
			}
		}
		
		if(walking)	animSprite.update();
		else animSprite.getFirstFrame();
		if(ya<0) 
		{
			//animSprite.update();
			animSprite=up;
		}
		if(ya>0)
		{
			//animSprite.update();
			animSprite=down;
		}
		if(xa<0)
		{
			//animSprite.update()
			animSprite=right;
			flip = 1;
		}
		if(xa>0)
		{
			//animSprite.update();
			animSprite=right;
			flip = 0;
		}
		name.setPosition((int)x, (int)y);
		//getDialogue();	
	}
	


}