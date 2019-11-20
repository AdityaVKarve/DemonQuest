package com.Aditya.tkp.Entity.mob;

import java.util.ArrayList;

import com.Aditya.tkp.Graphics.AnimatedSprite;
import com.Aditya.tkp.Graphics.RenderFont;
import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.Graphics.Sprite;
import com.Aditya.tkp.Graphics.SpriteSheet;
import com.Aditya.tkp.Graphics.ui.NamePlate;
import com.Aditya.tkp.Graphics.ui.UIDialogueBox;
import com.Aditya.tkp.level.PlayerPosition;
import com.Aditya.tkp.util.Node;
import com.Aditya.tkp.util.Vector2i;

public class Cain extends Mob{
	private AnimatedSprite up=new AnimatedSprite(SpriteSheet.villager_up,32,32,3);
	private AnimatedSprite down=new AnimatedSprite(SpriteSheet.villager_down,32,32,3);
	private AnimatedSprite right=new AnimatedSprite(SpriteSheet.villager_right,32,32,3);
	//private AnimatedSprite right=new AnimatedSprite(SpriteSheet.gunfighter_right,32,32,3);
	private AnimatedSprite animSprite=right;	 
	private int time=0;
	private double xa=0,ya=0;
	private boolean walking=false;
	private ArrayList<Node> path;
	protected int health;
	protected static int currentHealth;
	private double speed=0.4 +Math.random();
	int flip;
	boolean inRange;
	private UIDialogueBox db;
	String title;
	int dialogNumber;
	public Cain(int x, int y, int dialogNumber, String title)
	{
		
		this.x=x<<4;
		this.y=y<<4;
		this.dialogNumber = dialogNumber;
		this.title = title;
		sprite=Sprite.cain;
		health = 200;
		currentHealth = health;
		name = new NamePlate(title,false,new Vector2i(x,y));
		name.isActive = true;
		eui.addItem(name);
		inRange = false;
		isAlive = true;
	}
	@Override
	public void render(Screen screen) {
		// TODO Auto-generated method stub
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
			e.render(100, 0, 0, "!", 0xffffff, screen);
		}
	}
	private void move()
	{
		
		if(time%(random.nextInt(50)+30)==0)
		{
			double rand = Math.random();
			if(rand>0.5)
				xa=random.nextInt(3)-1;
			else
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
				
				db = new UIDialogueBox(dialogNumber, new Vector2i(0,0),Player.input);
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
		if(PlayerPosition.x >= x) {
			flip = 0;
		}
		else
			flip = 1;
		name.setPosition((int)x, (int)y);
		getDialogue();	
	}
}