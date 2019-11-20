package com.Aditya.tkp.level;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.Aditya.tkp.Game;
import com.Aditya.tkp.Entity.Entity;
import com.Aditya.tkp.Entity.Particle.Particle;
import com.Aditya.tkp.Entity.Projectile.Projectile;
import com.Aditya.tkp.Entity.mob.Chaser;
import com.Aditya.tkp.Entity.mob.Dummy;
import com.Aditya.tkp.Entity.mob.Lucifer;
import com.Aditya.tkp.Entity.mob.Minion;
import com.Aditya.tkp.Entity.mob.Player;
import com.Aditya.tkp.Entity.mob.Spirit;
import com.Aditya.tkp.Entity.mob.Star;
import com.Aditya.tkp.Entity.mob.Summoner;
import com.Aditya.tkp.Entity.mob.Turret;
import com.Aditya.tkp.Entity.mob.Villager;
import com.Aditya.tkp.Entity.spawner.Spawner;
import com.Aditya.tkp.Graphics.Screen;
import com.Aditya.tkp.Graphics.ui.UIDialogueBox;
import com.Aditya.tkp.Tile.Tile;
import com.Aditya.tkp.util.Node;
import com.Aditya.tkp.util.Vector2i;

public class Level {
	public int width,height;
	public int[] tilesInt;
	protected int[] tiles;
	//public static Level spawn=new SpawnLevel("/levels/spawn.png");
	public static Level carpet = new SpawnLevel("/levels/this_is_the_final_map_asshole.png");
	public ArrayList<Entity> entities=new ArrayList<Entity>();
	public ArrayList<Projectile> projectiles=new ArrayList<Projectile>();
	public ArrayList<Particle> particles=new ArrayList<Particle>();
	protected ArrayList<Player> players=new ArrayList<Player>();
	private Comparator<Node> nodeSorter=new Comparator<Node>() {	//A comparator takes two objects
	
		@Override
		public int compare(Node n0, Node n1) {
			if(n1.fCost<n0.fCost)
				return 1;
			if(n1.fCost>n0.fCost)
				return -1;
			return 0;
		}
		
	};
	public Level(int width, int height)
	{
		
		this.width=width;
		this.height=height;
		tilesInt=new int[width*height];
		generateLevel();
	}
	
	protected void generateLevel()
	{
		
	}
	public void spawnMob() {
		add(new Lucifer(698,480));
		players.get(0).removeRelics();
	}
	public void spawnMinion(int x, int y) {
		System.out.println("Minion added");
		add(new Minion(x>>4,y>>4));
		
	}
	public void spawnSpirit(int x, int y) {
		System.out.println("Minion added");
		add(new Spirit(x>>4,y>>4));
		
	}
	public Level(String Path)
	{ 
		loadLevel(Path);
		generateLevel();
		
	}
	public void loadLevel(String Path)
	{
		
	}
	public void update()
	{
		
		for(int i=0;i<entities.size();i++)
			entities.get(i).update();
		for(int i=0;i<projectiles.size();i++)
			projectiles.get(i).update();
		for(int i=0;i<particles.size();i++)
			particles.get(i).update();
		for(int i=0;i<players.size();i++)
			players.get(i).update();
		remove();
	}	
	private void remove()
	{
		for(int i=0;i<entities.size();i++)
			if(entities.get(i).isRemoved())
				entities.remove(i);
		for(int i=0;i<projectiles.size();i++)
			if(projectiles.get(i).isRemoved())
				projectiles.remove(i);
		for(int i=0;i<particles.size();i++)
			if(particles.get(i).isRemoved())
				particles.remove(i);
		for(int i=0;i<players.size();i++)
			if(players.get(i).isRemoved())
				players.remove(i);
	}
	public void render(int xscroll, int yscroll, Screen screen)
	{
		screen.setOffset(xscroll, yscroll);
		int x0=xscroll>>4;	//Leftmost corner of screen
		int x1=(xscroll+screen.width+16)>>4;
		int y0=yscroll>>4;
		int y1=(yscroll+screen.height+16)>>4;
		
		for(int y=y0;y<y1;y++)
		{
			for(int x=x0;x<x1;x++)
			{
				getTile(x,y).render(x, y, screen);				
			}
		}
		for(int i=0;i<entities.size();i++)
			entities.get(i).render(screen);
		for(int i=0;i<projectiles.size();i++)
			projectiles.get(i).render(screen);
		for(int i=0;i<particles.size();i++)
			particles.get(i).render(screen);
		for(int i=0;i<players.size();i++)
			players.get(i).render(screen);
		
	}
	public Tile getTile(int x, int y)
	{
		//tem.out.println("X:"+x+" Y:"+y);
		if(x<0||y<0||x>=width||y>=height) 
			return Tile.voidTile;
		if(tiles[x+y*width]==Tile.col_spawn_floor)
			return Tile.spawn_floor_tile;
		if(tiles[x+y*width]==Tile.col_spawn_grass)
			return Tile.spawn_grass_tile;
		if(tiles[x+y*width]==Tile.col_spawn_hedge)
			return Tile.spawn_hedge_tile;
		if(tiles[x+y*width]==Tile.col_spawn_wall1)
			return Tile.spawn_wall_1_tile;
		if(tiles[x+y*width]==Tile.col_spawn_wall2)
			return Tile.spawn_wall_2_tile;
		if(tiles[x+y*width]==Tile.col_spawn_water)
			return Tile.spawn_water_tile;
		if(tiles[x+y*width]==Tile.col_carpet)
			return Tile.carpet;
		if(tiles[x+y*width]==Tile.col_light_grass)
			return Tile.light_grass;
		if(tiles[x+y*width] == Tile.col_dungeon_bricks)
			return Tile.dungeon_bricks;
		if(tiles[x+y*width] == Tile.col_sand)
			return Tile.sand;
		if(tiles[x+y*width] == Tile.col_grassy_rock)
			return Tile.grassy_rock;
		if(tiles[x+y*width] == Tile.col_sandstone_bricks)
			return Tile.sandstone_bricks;
		if(tiles[x+y*width] == Tile.col_rock_floor)
			return Tile.rock_floor;
		
		if(tiles[x+y*width] == Tile.col_tree)
			return Tile.tree;
		return Tile.spawn_water_tile;
	}
	private void time()
	{
		
	}
	public ArrayList<Player> getPlayer()
	{
		return players;
	}
	public Player getPlayerAt(int index)
	{
		return players.get(index);
	}
	public Player getClientPlayer()
	{
		return players.get(0);
	}
	public ArrayList<Node> findPath(Vector2i start, Vector2i finish){
		
		ArrayList<Node> openList=new ArrayList<Node>();
		ArrayList<Node> closedList=new ArrayList<Node>();
		Node current=new Node(start,null,0,getDistance(start,finish));
		openList.add(current);
		while(openList.size()>0&&openList.size() < 100)
		{
			//System.out.println("RUN");
			
			Collections.sort(openList,nodeSorter);	//Auto sorts a ArrayList with a comporator
			current=openList.get(0);
			if(current.tile.equals(finish))//End of path
			{
				//System.out.println("Finish");
				ArrayList<Node> path=new ArrayList<Node>();
				while(current.parent!=null)
				{
					path.add(current);
					current=current.parent;
				}
				openList.clear();
				closedList.clear();
				return path;
			}
			openList.remove(current);
			closedList.add(current);
			for(int i=0;i<9;i++)
			{
				if(i==4)
					continue;
				int x=current.tile.getX();
				int y=current.tile.getY();
				int xi=(i%3)-1;
				int yi=(i/3)-1;
				Tile at=getTile(x+xi,y+yi);
				//System.out.println(xi+" "+yi+" "+at.getClass()+" "+at.solid());
				if(at==null)
					continue;
				if(at.solid()==true)
					continue;
				Vector2i a=new Vector2i(x+xi,y+yi);
				double gCost=current.gCost+(getDistance(current.tile,a)==1?1:0.95);
				double hCost=getDistance(a,finish);
				Node node=new Node(a,current,gCost,hCost);
				//System.out.println(gCost+" "+node.gCost+" "+current.gCost);
				if(vecInList(closedList,a)&&gCost>=node.gCost) continue;
				if(!vecInList(openList,a)||gCost<current.gCost)openList.add(node);
				
			}
		}
		closedList.clear();
		return null;
	}
	private boolean vecInList(ArrayList<Node> list,Vector2i vector)
	{
		for(Node n:list)
			if(n.tile.equals(vector))
				return(true);
		return(false);
	}
	private double getDistance(Vector2i start, Vector2i finish)
	{
		int dx=start.getX()-finish.getX();
		int dy=start.getY()-finish.getY();
		double distance=Math.sqrt(dx*dx+dy*dy);
		return distance;
	}
	public ArrayList<Entity> getEntities(Entity e,int radius)
	{
		ArrayList<Entity> result=new ArrayList<Entity>();
		int ex=(int)e.getX();
		int ey=(int)e.getY();
		for(int i=0;i<entities.size();i++)
		{
			Entity entity=entities.get(i);
			if(entity.equals(e))
				continue;
			int x=(int)entity.getX();
			int y=(int)entity.getY();
			int dx=Math.abs(ex-x);
			int dy=Math.abs(ey-y);
			double distance=Math.sqrt(dx*dx+dy*dy);
			if(distance<=radius)
			{
				result.add(entity);
				//System.out.println("e:"+e+"\nentity"+entity);
			}
		}
		return result;
	}
	public ArrayList<Player> getPlayers(Entity e, int radius)
	{
		ArrayList<Player> result=new ArrayList<Player>();
		int ex=(int)e.getX();
		int ey=(int)e.getY();
		for(int i=0;i<players.size();i++)
		{
			Player player=players.get(i);
			int x=(int)player.getX();
			int y=(int)player.getY();
			int dx=Math.abs(ex-x);
			int dy=Math.abs(ey-y);
			double distance=Math.sqrt(dx*dx+dy*dy);
			if(distance<=radius)
			{
				result.add(player);
				
			}
		}
		return result;
	}
	public boolean tileCollision(double x,double y, int size,int xOffset, int yOffset)
	{
		boolean solid=false;
		for(int c=0;c<4;c++)
		{
			int xt=((((int)x)-c%2*size+xOffset)>>4);
			int yt=((((int)y)-c/2*size+yOffset)>>4);
			if(getTile(xt,yt).solid())	//Tile precision
				solid=true;
		}
		return solid;
	}
	public boolean EntityCollision(double x, double y, int damage) {
		boolean hit = false;
		for(int i = 0; i < entities.size(); i++) {
			int tX = entities.get(i).getX();
			int tY = entities.get(i).getY();
			if((x <= (int)(tX+32) && x >=(int)(tX)) && (entities.get(i) instanceof Dummy || entities.get(i) instanceof Chaser|| entities.get(i) instanceof Spirit|| entities.get(i) instanceof Summoner
					 || entities.get(i) instanceof Lucifer|| entities.get(i) instanceof Minion||  entities.get(i) instanceof Turret)) {
				//System.out.println("x:"+tX);
				if((y <= (int)(tY+32) && y>= (int)(tY))&& (entities.get(i) instanceof Dummy || entities.get(i) instanceof Chaser || entities.get(i) instanceof Spirit
						|| entities.get(i) instanceof Summoner
						|| entities.get(i) instanceof Lucifer|| entities.get(i) instanceof Minion || entities.get(i) instanceof Turret)) {
					hit = true;
					entities.get(i).loseHealth(damage);
					entities.get(i).isHurt();
					//System.out.println(entities.get(i).getHealth());
					if(entities.get(i).getHealth()<=0) {
						entities.get(i).name.isActive = false;
						entities.get(i).remove();
						
					}
				
		}
		}
		}
		return hit;
	}
	
	public boolean PlayerCollision(double x, double y, int damage) {
		boolean hit = false;
		for(int i = 0; i < players.size(); i++) {
			int tX = players.get(i).getX();
			int tY = players.get(i).getY();
			if((x <= (int)(tX+32) && x >=(int)(tX)) && (players.get(i) instanceof Player)) {
				//System.out.println("x:"+tX);
				if((y <= (int)(tY+32) && y>= (int)(tY))&& (players.get(i) instanceof Player)) {
					hit = true;
					players.get(i).loseHealth(damage);
					players.get(i).isHurt();
					//System.out.println("Health:"+players.get(i).getHealth());
					//System.out.println(players.get(i).getHealth());
					if(players.get(i).getHealth()<0) {
						players.get(i).remove();
					}
				
		}
		}
		}
		return hit;
	}
	public ArrayList<Projectile> getProjectiles()
	{
		return projectiles;
	}
	public void add(Entity e)
	{	
		e.init(this);
		if(e instanceof Particle) {
			particles.add((Particle)e);
		}	
		else if(e instanceof Projectile)
		{
			projectiles.add((Projectile)e);
		}
		else if(e instanceof Player)
		{
			players.add((Player) e);
		}
		else {
			entities.add(e);
		}
	}
}

