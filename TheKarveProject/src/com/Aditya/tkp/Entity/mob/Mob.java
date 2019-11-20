package com.Aditya.tkp.Entity.mob;

import java.awt.List;
import java.util.ArrayList;


import com.Aditya.tkp.Entity.Entity;
import com.Aditya.tkp.Entity.Particle.Particle;
import com.Aditya.tkp.Entity.Projectile.Projectile;
import com.Aditya.tkp.Entity.Projectile.WizProjectile;
import com.Aditya.tkp.Entity.Projectile.mobProjectile;
import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.Graphics.Sprite;
import com.Aditya.tkp.level.Level;

public abstract class Mob extends Entity{

	//protected Sprite sprite;
	protected DIRECTION dir;	//0-North 1-East 2-South 3-West
	protected boolean moving=false;
	protected ArrayList<Projectile> projectiles=new ArrayList<Projectile>();
	protected int ticks;
	static public double speed;
	static public double playerSpeed;
	static int healthPotionCount;
	static int attackPotionCount;
	static int heartCount;
	static int brainCount;
	static int eyeCount;
	
	public static void addHealthPotion() {
		
	}
	public static void addAttackPotion() {
		
	}
	public static void addBrain() {
		
	}
	public static void addHeart() {
		
	}
	public static void addEye() {
		
	}
	protected int hurtDuration = 20;
	public void init(Level level)
	{
		this.level=level;
	}
	protected enum DIRECTION{
		UP,DOWN,LEFT,RIGHT
	}
	public void move(double xa, double ya) 
	{
		
		if(xa!=0&&ya!=0)
		{
			move(xa,0);
			move(0,ya);
			return;
		}
		if(xa>0)
			dir=DIRECTION.RIGHT;
		if(xa<0)
			dir=DIRECTION.LEFT;
		if(ya>0)
			dir=DIRECTION.DOWN;
		if(ya<0)
			dir=DIRECTION.UP;
		while(xa!=0)
		{
			if(Math.abs(xa)>1) {
				if(!collision(abs(xa),ya))
				{
					//System.out.println("Debug not call");	
					this.x+=abs(xa);
				}
				xa-=abs(xa);
			}
			else {
				if(!collision(abs(xa),ya))
				{
					//System.out.println("Debug not call");	
					this.x+=xa;
				}
				xa=0; 
			}
		}
		while(ya!=0)
		{
			if(Math.abs(ya)>1) {
				if(!collision(xa,abs(ya)))
				{
					//System.out.println("Debug not call");	
					this.y+=abs(ya);
				}
				ya-=abs(ya);
			}
			else {
				if(!collision(xa,abs(ya)))
				{
					//System.out.println("Debug not call");	
					this.y+=ya;
				}
				ya=0; 
			}
		}
	
		
	}
	
	private int abs(double value) 
	{
		if(value<0)
			return -1;
		return 1;
	}
	public void update()
	{
		
	}

	protected void shoot(double x, double y, double dir)
	{
		//System.out.println("Angle:"+dir);
		Projectile p=new WizProjectile(x,y,dir);
		projectiles.add(p);
		level.add(p);
	}
	protected void mobShoot(double x, double y, double dir)
	{
		//System.out.println("Angle:"+dir);
		Projectile p=new mobProjectile((int)x,(int)y,dir);
		projectiles.add(p);
		level.add(p);
	}
	public boolean collision(double xa,double ya)
	{
		boolean solid=false;
		for(int c=0;c<4;c++)
		{
			double xt=((x+xa)-c%2*16 +10)/16 ;
			double yt=((y+ya)-c/2*16 + 10)/16 ;
			int ix=(int)Math.ceil(xt);
			int iy=(int)Math.ceil(yt);
			if(c%2==0)
				ix=(int)Math.floor(xt);
			if(c/2==0)
				iy=(int)Math.floor(yt);
			if(level.getTile(ix,iy).solid())	//Tile precision
				solid=true;
		}
		return solid;
	}


	public abstract void render(Screen screen);
}
 