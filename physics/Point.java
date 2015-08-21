package physics;

import physics.Collision.PointHitBox;
import entity.Entity;

public class Point {
	
	double x;
	double y;
	
	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public PointHitBox toHitBox(Entity e){
		return new PointHitBox(e, x, y);
	}
	
	@Override
	public String toString(){
		return "{x: "+x+" y: "+y+"}";
	}
}
