package com.Aditya.tkp.Entity.Projectile;

import com.Aditya.tkp.Entity.Particle.Particle;
import com.Aditya.tkp.Entity.spawner.ParticleSpawner;
import com.Aditya.tkp.Entity.spawner.Spawner;
import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.Graphics.Sprite;
import com.Aditya.tkp.Tile.Tile;

public class WizProjectile extends Projectile {
	public static final int FIRERATE=25;	//RoF=60/Firerate
	public WizProjectile(double x,double y, double dir)
	{
		super((int)x,(int)y,dir);
		range=150;
		damage=50;
		mobDamage = 50;
		speed=10;	
		sprite=Sprite.rotate(Sprite.projectile_arrow, angle);
		nx=speed*Math.cos(angle);
		ny=speed*Math.sin(angle);
	}
	
	public void update() {
		double moveX = x+nx;
		double moveY = y+ny;
		if(level.tileCollision(x+nx, y+ny, 7,5,4))
		{
			remove();
			level.add(new ParticleSpawner((int)x,(int)y,44,50,level));
		}
		if(level.EntityCollision(x+nx, y+ny, (int)damage))
		{
			System.out.println("nx:"+nx);
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
	public static void setDamage(int dam) {
		damage = dam;
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
