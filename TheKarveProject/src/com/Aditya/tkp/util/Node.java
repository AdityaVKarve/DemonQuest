package com.Aditya.tkp.util;

public class Node {
	public Vector2i tile;
	public Node parent;
	public double fCost,gCost,hCost;
	//gCost-sum of costs of all nodes
	//hCost-the Euclidean distance between start and finish
	//fCost-hCost+gCost or Total Cost
	public Node(Vector2i tile, Node parent, double gCost, double hCost)
	{
		this.tile=tile;
		this.parent=parent;
		this.gCost=gCost;
		this.hCost=hCost;
		this.fCost=this.gCost+this.hCost;
	}
	
}
