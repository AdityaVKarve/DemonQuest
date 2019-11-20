package com.Aditya.tkp.Entity.mob;

import java.util.ArrayList;

import com.Aditya.tkp.Entity.Entity;
import com.Aditya.tkp.Graphics.AnimatedSprite;
import com.Aditya.tkp.Graphics.RenderFont;
import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.Graphics.SpriteSheet;
import com.Aditya.tkp.Graphics.ui.NamePlate;
import com.Aditya.tkp.Graphics.ui.UIDialogueBox;
import com.Aditya.tkp.level.PlayerPosition;
import com.Aditya.tkp.util.Node;
import com.Aditya.tkp.util.Vector2i;

public class Lucifer extends Mob{
	private AnimatedSprite up=new AnimatedSprite(SpriteSheet.lucifer_up,32,32,3);
	private AnimatedSprite down=new AnimatedSprite(SpriteSheet.lucifer_down,32,32,3);
	private AnimatedSprite right=new AnimatedSprite(SpriteSheet.lucifer_right,32,32,3);
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
	private long clock;
	boolean inRange;
	private UIDialogueBox db;
	public Lucifer(int x, int y)
	{
		
		this.x=x<<4;
		this.y=y<<4;
		sprite=animSprite.getSprite();
		health = 2000;
		currentHealth = health;
		name = new NamePlate("Lucifer",false,new Vector2i(x,y));
		name.isActive = true;
		eui.addItem(name);
		clock = System.currentTimeMillis();
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
	private void spawnMinion() {
		//System.out.println(System.currentTimeMillis()-clock);
		if(Math.sqrt(Math.pow(PlayerPosition.x -x, 2) + Math.pow(PlayerPosition.y -y,2)) < 5000) {
			if(System.currentTimeMillis() - clock > 1000) {
			level.spawnMinion((int)x, (int)y);
			clock = System.currentTimeMillis();
			}
		}
	}
	private void move()
	{
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
		
		
	}
	private void shootRandom()
	{
		if(time%60==1)
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
	public void getDialogue() {
		PlayerPosition pp = new PlayerPosition();
		
		if(Math.sqrt(Math.pow((pp.x-x),2))+Math.pow((pp.y-y), 2)<100) {
			inRange = true;
			if(Player.input.e) {
				
				db = new UIDialogueBox(4, new Vector2i(0,0),Player.input);
				Player.panel.addComponent(db);
				db.activate();
				
			}
			Player.input.e = false;
			
		}
		else
			inRange = false;
	}
	public void update()
	{
		spawnMinion();
		move();
		shootRandom();
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
