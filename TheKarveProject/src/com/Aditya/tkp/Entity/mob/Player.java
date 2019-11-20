package com.Aditya.tkp.Entity.mob;

import java.awt.Font;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.Aditya.tkp.Game;
import com.Aditya.tkp.Entity.*;
import com.Aditya.tkp.Entity.Projectile.Projectile;
import com.Aditya.tkp.Entity.Projectile.WizProjectile;
import com.Aditya.tkp.Graphics.AnimatedSprite;
import com.Aditya.tkp.Graphics.RenderFont;
import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.Graphics.Sprite;
import com.Aditya.tkp.Graphics.SpriteSheet;
import com.Aditya.tkp.Graphics.ui.NamePlate;
import com.Aditya.tkp.Graphics.ui.UIActionListener;
import com.Aditya.tkp.Graphics.ui.UIButton;
import com.Aditya.tkp.Graphics.ui.UIButtonListener;
import com.Aditya.tkp.Graphics.ui.UIDialogueBox;
import com.Aditya.tkp.Graphics.ui.UIImageButton;
import com.Aditya.tkp.Graphics.ui.UILabel;
import com.Aditya.tkp.Graphics.ui.UIManager;
import com.Aditya.tkp.Graphics.ui.UIPanel;
import com.Aditya.tkp.Graphics.ui.UIProgressBar;
import com.Aditya.tkp.Graphics.ui.UIQuestDisplay;
import com.Aditya.tkp.Input.Keyboard;
import com.Aditya.tkp.Input.Mouse;
import com.Aditya.tkp.level.PlayerPosition;
import com.Aditya.tkp.util.Vector2i;

public class Player extends Mob
{
	public static Keyboard input;
	private int xp;
	private int playerLevel;
	//private Sprite sprite;
	private int anim=0;
	boolean walking=false;
	private int firerate=0;
	Projectile p;
	//private AnimatedSprite test=new AnimatedSprite(SpriteSheet.player_down,32,32,3);
	private AnimatedSprite up=new AnimatedSprite(SpriteSheet.player_up,32,32,3);
	private AnimatedSprite down=new AnimatedSprite(SpriteSheet.player_down,32,32,3);
	private AnimatedSprite left=new AnimatedSprite(SpriteSheet.player_right,32,32,3);
	private AnimatedSprite right=new AnimatedSprite(SpriteSheet.player_right,32,32,3);
	private AnimatedSprite animSprite=down;
	private String name;
	private UIManager ui;  
	
	
	private UIProgressBar uiHealthBar;
	private UIProgressBar uiManaBar;
	protected int maxHealth = 1000;
	private int maxMana = 75;
	private BufferedImage image = null, imageHover = null;
	private int mana = 50;
	private UIButton button;
	private UIImageButton exitButton;
	private UIImageButton healthButton;
	private UIImageButton attackButton;
	private UIImageButton eyeButton;
	private UIImageButton heartButton;
	private UIImageButton brainButton;
	
	private BufferedImage homeBase = null;
	private BufferedImage homeHover = null;
	private BufferedImage eye = null;
	private BufferedImage heart = null;
	private BufferedImage brain = null;
	private BufferedImage healthPotion = null;
	private BufferedImage speedPotion = null;
	private BufferedImage attackPotion = null;
	private Game game;
	private UIQuestDisplay uiQuest;
	//TESTING ONLY//
	int rogueCount = 0;
	private UIDialogueBox db;
	private static int flip;
	private int healPotionHealth = 100;
	private PlayerPosition pp;
	static UIPanel panel;
	boolean isAmpedAttack = false;
	long currentTime = System.currentTimeMillis()/100;
	static UILabel healthCountLabel;
	static UILabel attackCountLabel;
	static UILabel eyeCountLabel;
	static UILabel heartCountLabel;
	static UILabel brainCountLabel;
	static UILabel statusLabel;
	public Player(String name, int x, int y,Keyboard input)	//Create Player at a certain point
	{
		this.name = name;
		this.x=x;	//x & y from Entity class
		this.y=y;
		this.input=input;
		currentHealth = 700;
		sprite=Sprite.player_forward;
		playerSpeed=1;
		speed = 1;
		attackPotionCount = 10;
		healthPotionCount = 10;
		firerate=WizProjectile.FIRERATE;
		ArrayList<NamePlate> names = new ArrayList<NamePlate>();
		statusLabel = new UILabel(new Vector2i ((game.width+40)*game.scale - 100,430),"Status:" ,22).setColor(0x00DD00);
		
			
		ui=Game.getUIManager();
		//UIPanel panel = new UIPanel(new Vector2i((300-80)*3,0), new Vector2i(80*3,160*3));
		panel = new UIPanel(new Vector2i((300 - 80)*Game.scale,0),new Vector2i(80*Game.scale,168*Game.scale)).setColor(0xFF333333);
		ui.addPanel(panel);
		panel.addComponent(new UILabel(new Vector2i ((game.width+40)*game.scale - 50,90), name ,32).setColor(0x999999));
		uiHealthBar = new UIProgressBar(new Vector2i((game.width+40)*game.scale - 100,140),new Vector2i(200,20), 0x00dd00,100, "HP");
		uiManaBar = new UIProgressBar(new Vector2i((game.width+40)*game.scale - 100,165), new Vector2i(200,20), 0x0000cc,70, "Mana");
		uiQuest = new UIQuestDisplay("Kill 5 rogues");
		panel.addComponent(statusLabel);
		button = new UIButton(new Vector2i((game.width + 40)*game.scale - 100, 200), new Vector2i(100,40), new UIActionListener() {
			public void perform() {
				//System.out.println("Button pressed");
			}
		}, 0x22FF22);
		
		//button.setForegroundColor	(0x22FF22);
		button.setText("TEST",0xFFFFFF);
		//panel.addComponent(button);
		
		try {
			homeBase = ImageIO.read(new File("resources/buttons/home.png"));
			homeHover = ImageIO.read(new File("resources/buttons/homeHover.png"));
			eye = ImageIO.read(new File("resources/buttons/eye.png"));
			heart = ImageIO.read(new File("resources/buttons/heart.png"));
			brain = ImageIO.read(new File("resources/buttons/brain.png"));
			healthPotion = ImageIO.read(new File("resources/buttons/healthPotion.png"));
			speedPotion = ImageIO.read(new File("resources/buttons/speedPotion.png"));
			attackPotion = ImageIO.read(new File("resources/buttons/attackPotion.png"));
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		 exitButton = new UIImageButton(new Vector2i((game.width + 40)*game.scale - 100, 250),homeBase, homeHover, new UIActionListener() {
			public void perform() {
				System.exit(0);
			}
		});
		 healthButton = new UIImageButton(new Vector2i((game.width+40)*game.scale-100,250), healthPotion,healthPotion,new UIActionListener() {
			@Override
			public void perform() {
				// TODO Auto-generated method stub
				if(healthPotionCount > 0) {
					if(health + healPotionHealth > maxHealth) {
						currentHealth = maxHealth;
					}
					if(health >= maxHealth)
						return;
					else
						currentHealth += healPotionHealth;
					healthPotionCount--;
				}
			} 
		 });
		 healthCountLabel = new UILabel(new Vector2i ((game.width+40)*game.scale - 100,255), Integer.toString(healthPotionCount) ,15).setColor(0x999999);
		 
		 attackButton = new UIImageButton(new Vector2i((game.width+60)*game.scale - 100,250),attackPotion,attackPotion,new UIActionListener() {

			@Override
			public void perform() {
				// TODO Auto-generated method stub
				
				if(attackPotionCount > 0) {
					currentTime = System.currentTimeMillis()/100;
					isAmpedAttack = true;
					attackPotionCount--;
				}
			}
			 
		 });
		 attackCountLabel = new UILabel(new Vector2i ((game.width+60)*game.scale - 100,255), Integer.toString(attackPotionCount) ,15).setColor(0x999999);
		
		 eyeButton = new UIImageButton(new Vector2i((game.width+40)*game.scale-100,320), eye,eye,new UIActionListener() {
				@Override
				public void perform() {
					// TODO Auto-generated method stub
					
				} 
			 });
		 eyeCountLabel = new UILabel(new Vector2i ((game.width+38)*game.scale - 100,325), Integer.toString(eyeCount) ,15).setColor(0x999999);
		 
		 heartButton = new UIImageButton(new Vector2i((game.width+60)*game.scale-100,320), heart,heart,new UIActionListener() {
				@Override
				public void perform() {
					// TODO Auto-generated method stub
					
				} 
			 });
		 heartCountLabel = new UILabel(new Vector2i ((game.width+60)*game.scale - 100,325), Integer.toString(heartCount) ,15).setColor(0x999999);
		 
		 brainButton = new UIImageButton(new Vector2i((game.width+80)*game.scale-100,320), brain,brain,new UIActionListener() {
				@Override
				public void perform() {
					// TODO Auto-generated method stub
					
				} 
			 });
		 brainCountLabel= new UILabel(new Vector2i ((game.width+80)*game.scale - 100,325), Integer.toString(brainCount) ,15).setColor(0x999999);
		//panel.addComponent(exitButton);
		panel.addComponent(healthButton);
		panel.addComponent(attackButton);
		panel.addComponent(uiHealthBar);
		panel.addComponent(uiManaBar);
		panel.addComponent(eyeButton);
		panel.addComponent(heartButton);
		panel.addComponent(brainButton);
		panel.addComponent(attackCountLabel);
		panel.addComponent(brainCountLabel);
		panel.addComponent(eyeCountLabel);
		panel.addComponent(healthCountLabel);
		panel.addComponent(heartCountLabel);
		//db = new UIDialogueBox(4, new Vector2i(0,0),input);
		//db.activate();
		//pp = new PlayerPosition();
		//panel.addComponent(db);
		//button = new UIButton(new Vector2i(10, 260), new Vector2i(120,40));
	}
	@Deprecated
	public Player(Keyboard input)	//Create player at default location
	{
		this.input=input;
		sprite=Sprite.player_forward;
		//System.out.println(sprite);
	}
	public void addRogue() {
		rogueCount+=1;
	}
	public static void addHealthPotion() {
		healthPotionCount +=1;
	}
	public static void addAttackPotion() {
		attackPotionCount +=1;
	}
	public static void removeRelics() {
		heartCount = 0;
		eyeCount = 0;
		brainCount = 0;
	}
	public static void addBrain() {
		brainCount +=1;
	}
	public static void addHeart() {
		heartCount+=1;
	}
	public static void addEye() {
		eyeCount +=1;
	}
	public int getRogue() {
		return rogueCount;
	}
	public void addXp(int xp) {
		this.xp+=xp;
	}
	public void update()
	{
		//System.out.println(playerSpeed);
		healthCountLabel.setText(Integer.toString(healthPotionCount));
		attackCountLabel.setText(Integer.toString(attackPotionCount));
		
		heartCountLabel.setText(Integer.toString(heartCount));
		eyeCountLabel.setText(Integer.toString(eyeCount));
		brainCountLabel.setText(Integer.toString(brainCount));
		
		if(isAmpedAttack) {
			if(System.currentTimeMillis()/100-currentTime >100) {
				isAmpedAttack = false;
			}
			statusLabel.setText("Status:Amplified attack");
			System.out.println("Amplified attack");
			WizProjectile.setDamage(1000);
			
		}
		
		else if(!isAmpedAttack) {
			statusLabel.setText("Status:");
			WizProjectile.setDamage(100);
		}
		if(heartCount>0 && eyeCount>0 && brainCount > 0) {
			level.spawnMob();
		}
		//System.out.println("Player X"+x+" Player Y"+y);
		ArrayList<Entity> es=level.getEntities(this, 20);
		//System.out.println("Entity count:"+es.size());
		//System.out.println();
		if(firerate>0)
			firerate--;
		//System.out.println(level.projectiles.size());
		anim++;
		if(anim<7000)
			anim++;
		else anim=0;
		double xa=0,ya=0;
		if(walking)
			animSprite.update();
		else
			animSprite.getFirstFrame();
		if(input.up) 
		{
			//animSprite.update();
			//System.out.println("UP");
			ya-=playerSpeed;
			animSprite=up;
			
		}
		if(input.down)
		{
			//animSprite.update();
			ya+=playerSpeed;
			animSprite=down;
		}
		if(input.shift) {
			playerSpeed = 3;
			speed = 3;
		}
		if(!input.shift) {
			playerSpeed = 1;
			speed = 1;
		}
		if(input.left)
		{
			//animSprite.update();
			xa-=playerSpeed;
			animSprite=left;
			flip = 1;
		}
		if(input.right)
		{
			//animSprite.update();
			xa+=playerSpeed;
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
		updateShooting();
		//button.update();
		pp.x = (int) x;
		pp.y= (int)y;
		clear();
		
	}
	
 
	private void clear()
	{
		for(int i=0;i<level.projectiles.size();i++)
		{
			Projectile p=level.projectiles.get(i);
			if(p.isRemoved())
				level.projectiles.remove(i);
		}
	}
	int time = 0;
	public void updateShooting()
	{
		if(Mouse.getB()==1&&firerate<=0 && Mouse.getX()-Game.getWindowWidth()/2 < Game.getWindowWidth()/2)
		{
			double dx=Mouse.getX()-Game.getWindowWidth()/2;
			double dy=Mouse.getY()-Game.getWindowHeight()/2;
			double dir=Math.atan2(dy, dx);
			shoot(x,y,dir);
			//System.out.println(dx);
			
			firerate=WizProjectile.FIRERATE;
		}
		uiHealthBar.setProgress(((double)currentHealth/maxHealth));
		uiManaBar.setProgress(((double)mana/maxMana));
		
	}
	
	public void render(Screen screen)
	{
		int xx=(int) (x-16);
		int yy=(int) (y-16);
		//System.out.println(xx);
		RenderFont renderFont = new RenderFont();
		//renderFont.render(0,0,0,("X:"+xx/16+" Y:"+yy/16),0xFFFFFF,screen);
		sprite=animSprite.getSprite();
		
		screen.renderMob((int)x, (int)y, this,hurt,flip);
		if(hurt == true) {
			hurt = false;
		}
		
	
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = 	name;
	}
}
	

