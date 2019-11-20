package com.Aditya.tkp.Entity.mob;

import java.util.ArrayList;

import com.Aditya.tkp.Game;
import com.Aditya.tkp.Entity.Entity;
import com.Aditya.tkp.Graphics.AnimatedSprite;
import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.Graphics.Sprite;
import com.Aditya.tkp.Graphics.SpriteSheet;
import com.Aditya.tkp.Graphics.ui.EnvironmentUI;
import com.Aditya.tkp.Graphics.ui.NamePlate;
import com.Aditya.tkp.Graphics.ui.UIManager;
import com.Aditya.tkp.util.Debug;
import com.Aditya.tkp.util.Vector2i;

public class Turret extends Mob{

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.rogue_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.rogue_up, 32, 32, 3);
	//private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.rogue_up, 32, 32, 3);
	private boolean walking=false;
	private AnimatedSprite animSprite = down;
	
	static int flip;
	private int time = 0;
	private int xa = 0;
	private int ya = 0;
	private UIManager ui;
	public Turret(int x, int y) {
		this.x=x<<4;
		this.y=y<<4;
		currentHealth = 100;
		sprite=Sprite.dummy;
		name = new NamePlate("Rogue",false,new Vector2i(x,y));
		eui.addItem(name);
		isAlive = true;
		name.isActive = true;
	}
	public void update()
	{
		
		Player p=level.getClientPlayer();
		
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
			flip = 0;
		}
		else if(ya>0)
		{
			//animSprite.update();
			animSprite=down;
			flip = 0;
		}
		else if(xa<0)
		{
			//animSprite.update()
			animSprite=right;
			flip = 1;
		}
		else if(xa>0)
		{
			//animSprite.update();
			animSprite=right;
			flip = 0;
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
		name.setPosition((int)x, (int)y);
		shootRandom();
		
	}
	private void shootRandom()
	{
		if(time%60==0)
		{
			ArrayList<Player> players=level.getPlayers(this, 100);
			double min=50;
			Entity rand=null;
			int index=-1;
			if(players.size()>0)
			{
				 index=random.nextInt(players.size());
				 rand=players.get(index);
			}
			if(index!=-1)
			{
				double px=rand.getX();
				double py=rand.getY();
				double x=getX();
				double y=getY();
				double dx=px-x;
				double dy=py-y;
				double dir=Math.atan2(dy,dx);
				mobShoot(x,y,dir);
			}
		}
	}
	private void shootClosest()
	{
		if(time%60==0)
		{
		ArrayList<Player> players=level.getPlayers(this, 100);
		double min=50;
		Entity closest=null;
		for(int i=0;i<players.size();i++)
		{
			Player player=players.get(i);
			double distance=Vector2i.getDistance(new Vector2i((int)x,(int)y), new Vector2i(player.getX(),player.getY()));
			if(i==0||distance<min)
			{
				min=distance;
				closest=player;
			}
		}
		if(closest!=null)
		{
			double px=closest.getX();
			double py=closest.getY();
			double x=getX();
			double y=getY();
			double dx=px-x;
			double dy=py-y;
			double dir=Math.atan2(dy,dx);
			shoot(x,y,dir);
		}
	}
	}
	
	@Override
	public void render(Screen screen) {
		//Debug.drawRect(screen, 17*16, 57*16, 100, 40, 0xff0000,true);
		sprite=animSprite.getSprite();
		screen.renderMob((int)x, (int)y, this,hurt,flip);
		if(hurt == true) {
			ticks++;
			if(ticks >= hurtDuration) {
				ticks = 0;
				hurt = false;
			}
		}
		// TODO Auto-generated method stub
		
	}
	

}
