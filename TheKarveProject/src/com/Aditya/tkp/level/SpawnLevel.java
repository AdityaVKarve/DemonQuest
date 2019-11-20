package com.Aditya.tkp.level;

import java.awt.image.BufferedImage;


import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.Aditya.tkp.Entity.Entity;
import com.Aditya.tkp.Entity.mob.Blacksmith;
import com.Aditya.tkp.Entity.mob.Cain;
import com.Aditya.tkp.Entity.mob.Chaser;
import com.Aditya.tkp.Entity.mob.Cook;
import com.Aditya.tkp.Entity.mob.DialogBox;
import com.Aditya.tkp.Entity.mob.Dummy;
import com.Aditya.tkp.Entity.mob.King;
import com.Aditya.tkp.Entity.mob.Lucifer;
//import com.Aditya.tkp.Entity.mob.House;
import com.Aditya.tkp.Entity.mob.Star;
import com.Aditya.tkp.Entity.mob.Summoner;
import com.Aditya.tkp.Entity.mob.Turret;
import com.Aditya.tkp.Entity.mob.Villager;
import com.Aditya.tkp.Entity.mob.bed;
import com.Aditya.tkp.Entity.mob.berryBush;
import com.Aditya.tkp.Entity.mob.bush01;
import com.Aditya.tkp.Entity.mob.chair;
import com.Aditya.tkp.Entity.mob.chest;
import com.Aditya.tkp.Entity.mob.table;
import com.Aditya.tkp.Entity.mob.throne;
import com.Aditya.tkp.Entity.mob.web;
import com.Aditya.tkp.Tile.Tile;

public class SpawnLevel extends Level{
	
	public int[] levelPixels;

	public SpawnLevel(String Path) {
		
		super(Path);
	}
	
	public void loadLevel(String Path)
	{
		try {
			System.out.println("Test");
			BufferedImage image=ImageIO.read(SpawnLevel.class.getResourceAsStream(Path));
			int w=image.getWidth();
			int h=image.getHeight();
			width=w;
			height=h;
			tiles=new int[w*h];
			image.getRGB(0,0,w,h,tiles,0,w);
			//System.out.println("value-"+tiles[500]);
		}
		catch(IOException e)
		{
			System.out.println("Error in loading level files!");
			e.printStackTrace();	
		}
		
	
		//plant placement
		for(int i = 348; i < 363; i++) {
			add(new bush01(i,452));
			add(new bush01(i,455));
		}
		
		for(int i = 0; i < 20; i++) {
			int xB = 358 + (int)(Math.random()*10);
			int yB = 333+(int)(Math.random()*60);
			add(new Turret(xB,yB));
		}
		for(int i = 0; i < 50; i++) {
			int xB = 395+(int)(Math.random()*18);
			int yB = 443+(int)(Math.random()*30);
			add(new bush01(xB,yB));
			System.out.println(xB+" "+yB);
		}
		for(int i = 0; i < 50; i++) {
			int xB = 413+(int)(Math.random()*18);
			int yB = 455+(int)(Math.random()*30);
			add(new bush01(xB,yB));
			System.out.println(xB+" "+yB);
		}
		for(int i = 0; i < 30; i++) {
			int xB = 304+(int)(Math.random()*100);
			int yB = 478+(int)(Math.random()*6);
			add(new bush01(xB,yB));
			System.out.println(xB+" "+yB);
		}
		for(int i = 0; i < 100; i++) {
			int xB = 243+(int)(Math.random()*120);
			int yB = 331+(int)(Math.random()*60);
			add(new bush01(xB,yB));
			System.out.println(xB+" "+yB);
		}
		for(int i = 0; i < 100; i++) {
			int xB = 369+(int)(Math.random()*20);
			int yB = 333+(int)(Math.random()*60);
			add(new bush01(xB,yB));
			System.out.println(xB+" "+yB);
		}
		for(int x = 341; x < 344; x++) {
			for(int y = 446; y < 473; y++) {
				add(new berryBush(x,y));
			}
		}
		for(int x = 341; x < 344; x++) {
			for(int y = 412; y < 443; y++) {
				add(new berryBush(x,y));
			}
		}
		//webs
		add(new web(358,417));
		//chests
		add(new chest(357,416,new int[]{3,0,0,0,0}));
		add(new chest(363,459,new int[]{1,2,0,0,0}));
		add(new chest(356,459,new int[]{2,0,0,0,0}));
		add(new chest(375,459,new int[]{2,1,0,0,0}));
		add(new chest(273,242,new int[]{3,2,0,0,0}));
		add(new chest(320,471,new int[]{1,0,0,0,0}));
		add(new chest(305,460,new int[]{1,1,0,0,0}));
		add(new chest(336,466,new int[]{1,0,0,0,0}));
		add(new chest(273,242,new int[]{1,0,1,0,0}));
		add(new chest(90,458,new int[]{5,1,0,1,0}));
		add(new chest(132,488,new int[]{3,4,0,0,1}));
		add(new chest(353,293,new int[]{1,0,0,0,0}));
		add(new chest(337,313,new int[]{1,0,0,0,0}));
		add(new chest(322,298,new int[]{1,0,0,0,0}));
		//beds
		add(new bed(358,413));
		add(new bed(356,469));
		add(new bed(356,472));
		add(new bed(375,419));
		add(new bed(358,427));
		add(new bed(374,427));
		add(new bed(356,441));
		add(new bed(372,445));
		add(new bed(319,467));
		
		add(new bed(363,315));
		add(new bed(349,315));
		add(new bed(333,315));
		add(new bed(318,315));
		add(new bed(318,293));
		add(new bed(333,293));
		add(new bed(349,293));
		add(new bed(363,293));
		//tables
		add(new table(364,464));
		add(new table(307,442));
		add(new table(307,435));
		add(new table(307,449));
		
		add(new table(320,296));
		add(new table(335,296));
		add(new table(350,296));
		add(new table(365,296));
		add(new table(320,311));
		add(new table(335,311));
		add(new table(350,311));
		add(new table(350,311));
		//chairLeft
		add(new chair(367,464,2));
		add(new chair(436,427,2));
		add(new chair(436,434,2));
		add(new chair(310,442,2));
		add(new chair(310,435,2));
		add(new chair(310,449,2));
		//chairRight
		add(new chair(379,469,1));
		add(new chair(415,427,1));
		add(new chair(304,442,1));
		add(new chair(304,435,1));
		add(new chair(304,449,1));
		add(new chair(361,464,1));
		add(new throne(321,414));
		//chairFront
		add(new chair(364,461,0));
		add(new chair(307,445,0));
		add(new chair(307,439,0));
		add(new chair(307,432,0));
		add(new Star(365,407));
		add(new Villager(365,409,5, "Gheed"));
		add(new Villager(373,415,6, "Warriv"));
		add(new Villager(357,429,7, "Marko"));
		add(new Villager(373,442,8, "Elzix"));
		add(new Blacksmith(364,460,9,"Alchemist"));
		add(new Villager(416,436,10,"Secretary"));
		add(new Villager(433,435,11,"Businessman"));
		add(new Cook(307,444,12,"Cook"));
		add(new King(321,414,13,"King Cherno"));
		add(new Cain(444,450,14,"Cain"));
		add(new Summoner(107,396));
		add(new Summoner(107,396));
		//add(new Lucifer(365,409));
		for(int i = 0; i < 10; i++) {
			
			add(new Turret(107,396));
		}
		//add(new House(20,55));
		//add(new DialogBox("test"));
		//for(int i=0;i<2;i++)
			//add(new Dummy(20,55));
	}
	//Grass=0xFF00FF00
	//Rock=0xFFFF00
	//Dirt=0x7f7f00
	
	protected void generateLevel()
	{
		

	}
	

}
