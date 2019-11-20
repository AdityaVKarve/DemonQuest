package com.Aditya.tkp.Entity.Particle;

import java.util.ArrayList;

import com.Aditya.tkp.Entity.Entity;
import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.Graphics.Sprite;

public class Particle extends Entity
{
	private static ArrayList<Particle> particles=new ArrayList<Particle>();
	private Sprite sprite;
	private int life;
	protected double xa;	//Amount of pixels moved on X axis
	protected double ya;	//Y axis
	protected double xx;
	protected double yy;
	protected double zz;
	protected double za;
	private int health;
	private int currentHealth;
	private int time;
	public Particle(double x, double y, int life)		//One particle
	{
	//	System.out.println("Debug constructor");
		this.xx=x;
		this.yy=y;
		this.life=life+(random.nextInt(100)-20);
		sprite=Sprite.particle_normal;		
		this.xa=random.nextGaussian();
		this.ya=random.nextGaussian();	
		this.zz=random.nextFloat()+2.0;
	}
	public void render(Screen screen)
	{
	//	System.out.println("Debug render");
		screen.renderSprite((int)xx, (int)yy-(int)zz, sprite, true);
	}
	public void update()
	{
		time++;
		za-=0.1;
		if(zz<=0)
		{
			zz=0;
			za*=-0.55;
			xa*=0.5;
			ya*=0.5;
		}
		move((xx+xa),(yy+ya+zz+za));
		if(time>=7400)
			time=0;
		if(time>life)
			remove();		
	}
	private void move(double x, double y) {
		if(collision(x,y))
		{
			this.xa*=-0.5;
			this.ya*=-0.5;
			this.za*=-0.5;
		}
		this.xx+=xa;
		this.yy+=ya;
		this.zz+=za;
		
	}
	public boolean collision(double x,double y)
	{
		boolean solid=false;
		for(int c=0;c<4;c++)
		{
			double xt=((x)-c%2*16)/16;
			double yt=((y)-c/2*16)/16;
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
	
	
}
