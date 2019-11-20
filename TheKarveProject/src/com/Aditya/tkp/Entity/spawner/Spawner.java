package com.Aditya.tkp.Entity.spawner;

import com.Aditya.tkp.Entity.Entity;
import com.Aditya.tkp.Entity.Particle.Particle;
import com.Aditya.tkp.Entity.spawner.Spawner.Type;
import com.Aditya.tkp.level.Level;

public class Spawner extends Entity
{
	public enum Type
	{
		PARTICLE,MOB
	}
	private Type type;
	public Spawner(double x, double y, Type type, int amount,Level level)
	{
		init(level);
		this.x=x;
		this.y=y;
		this.type=type;
		
	}
}
