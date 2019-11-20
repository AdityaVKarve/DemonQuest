package com.Aditya.tkp.Entity.mob;

import java.util.ArrayList;

import com.Aditya.tkp.Graphics.AnimatedSprite;
import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.Graphics.SpriteSheet;

public class Chaser extends Mob {
	private AnimatedSprite up=new AnimatedSprite(SpriteSheet.dummy_up,32,32,3);
	private AnimatedSprite down=new AnimatedSprite(SpriteSheet.dummy_down,32,32,3);
	private AnimatedSprite left=new AnimatedSprite(SpriteSheet.dummy_left,32,32,3);
	private AnimatedSprite right=new AnimatedSprite(SpriteSheet.dummy_right,32,32,3);
	private AnimatedSprite animSprite=up;	 
	private int time;
	private double xa=0,ya=0;
	private boolean walking=false;
	private double speed=1;
	protected int health;
	static int flip;
	
	protected static int currentHealth;
	public Chaser(int x, int y)
	{
		
		this.x=x<<4;
		this.y=y<<4;
		sprite=animSprite.getSprite();
		health = 300;
		currentHealth = health;
	}
	@Override
	public void render(Screen screen) {
		// TODO Auto-generated method stubf
		sprite=animSprite.getSprite();
		screen.renderMob((int)x, (int)y, this,hurt,flip);
		if(hurt == true) {
			ticks++;
			if(ticks >= hurtDuration) {
				ticks = 0;
				hurt = false;
			}
		}
	}
	private void move()
	{
		xa=0;
		ya=0;	
		ArrayList<Player> players=level.getPlayers(this,100);
		
		if(players.size()>0)
		{
			Player player=level.getPlayerAt(0);
			if(player.getX()>x+10) {
				xa+=speed;
				flip = 0;
			}
			if(player.getX()<x+10) {
				xa-=speed;
				flip = 1;
			}
			if(player.getY()>y+10)
				ya+=speed;
			if(player.getY()<y+10)
				ya-=speed;
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
	public void update()
	{
		move();
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
			animSprite=left;
		}
		if(xa>0)
		{
			//animSprite.update();
			animSprite=right;
		}
		
			
	}
	

}
