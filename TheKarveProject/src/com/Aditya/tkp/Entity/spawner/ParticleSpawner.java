package com.Aditya.tkp.Entity.spawner;

import com.Aditya.tkp.Entity.Particle.Particle;
import com.Aditya.tkp.level.Level;

public class ParticleSpawner extends Spawner {
	private int life;
	public ParticleSpawner(double x, double y,int life, int amount, Level level) {
		super(x, y, Type.PARTICLE, amount, level);
		this.life=life;
		for(int i=0;i<amount;i++)
				level.add(new Particle(x,y,life));
	}

}
