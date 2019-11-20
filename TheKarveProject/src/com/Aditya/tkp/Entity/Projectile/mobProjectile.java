package com.Aditya.tkp.Entity.Projectile;

import com.Aditya.tkp.Entity.spawner.ParticleSpawner;
import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.Graphics.Sprite;

public class mobProjectile extends Projectile {
	public mobProjectile(int x, int y, double dir) {
		super((int)x,(int)y,dir);
		range=150;
		damage=100;
		speed=10;	
		sprite=Sprite.rotate(Sprite.projectile_arrow, angle);
		nx=speed*Math.cos(angle);
		ny=speed*Math.sin(angle);
		// TODO Auto-generated constructor stub
	}
	public static final int FIRERATE=10;	//RoF=60/Firerate
	public void update() {
		double moveX = x+nx;
		double moveY = y+ny;
		if(level.tileCollision(x+nx, y+ny, 7,5,4))
		{
			remove();
			level.add(new ParticleSpawner((int)x,(int)y,44,50,level));
		}
		if(level.PlayerCollision(x+nx, y+ny, (int)mobDamage))
		{
			//System.out.println("nx:"+nx);
			remove();
			level.add(new ParticleSpawner((int)x,(int)y,44,50,level));
		}
		
		move();
	}
	protected void move()
	{
		if(!level.tileCollision(x+nx, y+ny,7,5,4))
		{
			x+=nx;
			y+=ny;
			if(Distance()>range)
			{
				remove();
			}
		}
	}
	public void render(Screen screen)
	{
		screen.renderProjectile(x-4, y-12, this);
	}
	public int getSpriteSize() {
		return sprite.SIZE;
}
	private double Distance()
	{
		double distance=0;
		distance=Math.sqrt(Math.pow(xOrigin-x,2)+Math.pow(yOrigin-y, 2));
		return distance;
	}

}