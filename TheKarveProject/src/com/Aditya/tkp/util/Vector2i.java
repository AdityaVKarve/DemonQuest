package com.Aditya.tkp.util;

public class Vector2i
{
	public int x,y;
	
	public Vector2i()
	{
		set(0,0);
	}
	public void set(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	public Vector2i(int x,int y)		//[x,y]
	{
		this.x=x;
		this.y=y;
	}
	public Vector2i(Vector2i vector)
	{
		set(vector.x,vector.y);
	}
	public Vector2i add(Vector2i vector)
	{
		this.x+=vector.x;
		this.y+=vector.y;
		return this;
	}
	public Vector2i subtract(Vector2i vector)
	{
		this.x-=vector.x;
		this.y-=vector.y;
		return this;
	}
	public int getX()
	{
		return x;
	}
	public Vector2i setX(int x)
	{
		this.x=x;
		return this;
	}
	public int getY()
	{
		return y;
	}
	public Vector2i setY(int y)
	{
		this.y=y;
		return this;
	}
	public static double getDistance(Vector2i a, Vector2i b)
	{
		int ax=a.getX();
		int ay=a.getY();
		int bx=b.getX();
		int by=b.getY();
		int dx=ax-bx;
		int dy=ay-by;
		double distance=Math.sqrt(dx*dx+dy*dy);
		return distance;
	}
	public boolean equals(Object object)
	{
		if(!(object instanceof Vector2i))
			return false;
		Vector2i vec=(Vector2i)object;
		if(vec.getX()==this.getX()&&vec.getY()==this.getY())
			return true;
		return false;
	}
}
