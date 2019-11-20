package com.Aditya.tkp.Entity.mob;

import com.Aditya.tkp.Entity.Entity;
import com.Aditya.tkp.Graphics.AnimatedSprite;
import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.Graphics.Sprite;
import com.Aditya.tkp.Graphics.SpriteSheet;

	public class Dummy extends Mob {

		private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
		private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
		private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
		private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);
		private boolean walking=false;
		private AnimatedSprite animSprite = down;

		private int time = 0;
		private int xa = 0;
		private int ya = 0;
		private int finalX;
		int flip;
		
		public Dummy(int x, int y) {
			ticks = 0;
			this.x = x << 4;
			this.y = y << 4;
			finalX = x;
			sprite = Sprite.dummy;
			health = 500;
			currentHealth = health;
		}

		public void update() {
			time++;
			/*if(time%(random.nextInt(50)+30)==0)
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
				animSprite=left;
			}
			if(xa>0)
			{
				//animSprite.update();
				animSprite=right;
			}
			
			if(xa!=0||ya!=0)
			{	
				move(xa,ya);
				walking=true;
			}
			else
			{
				walking=false;
				
			}	*/
		}
		public int getX() {
			return (int)x;
		}
		public int getY() {
			return (int)y;
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
		
		
		public void render(Screen screen) {
			sprite = animSprite.getSprite();
			screen.renderMob((int)x, (int)y, this,hurt,0);
			if(hurt == true) {
				ticks++;
				if(ticks >= hurtDuration) {
					ticks = 0;
					hurt = false;
				}
			}
			
		}
	}

