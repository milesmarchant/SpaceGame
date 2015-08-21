package physics;

import java.awt.Color;
import java.awt.Graphics2D;

import core.GameWindow;
import core.Util;
import core.Util.DrawCommand;
import entity.Entity;

public class Collision {
	
	private static boolean collide(PointHitBox p1, PointHitBox p2){
		//are they on the same pixel?
		return (int)p1.e.getX()==(int)p2.e.getX() && (int)p1.e.getY()==(int)p2.e.getY();
	}
	
	private static boolean collide(PointHitBox p, RectHitBox r){
		//TODO? Check axis-aligned bounding box for r as a trivial case
		double sin = Math.sin(Math.toRadians(r.e.getAngle()));
		double cos = Math.cos(Math.toRadians(r.e.getAngle()));
		
		//{u, v} is the rotation of {x, y}
		double pu = p.e.getX()*cos + p.e.getY()*sin;
		double pv = p.e.getY()*cos - p.e.getX()*sin;
		
		double ru = r.e.getX()*cos + r.e.getY()*sin;
		double rv = r.e.getY()*cos - r.e.getX()*sin;
		
//		Util.addDrawCommand(new DrawCommand(){
//			@Override
//			public void draw(Graphics2D g) {
//
//				g.setColor(Color.WHITE);
//				//rotate
//				g.rotate(-Math.toRadians(r.e.getAngle()), 0, GameWindow.WIN_HEIGHT);
//				//draw the u value
//				g.drawLine(0, GameWindow.WIN_HEIGHT-0, (int)(ru), GameWindow.WIN_HEIGHT-0);
//				//draw the v value
//				g.drawLine((int)(ru), GameWindow.WIN_HEIGHT-0, (int)(ru), GameWindow.WIN_HEIGHT-(int)(rv));
//				//unrotate
//				g.rotate(Math.toRadians(r.e.getAngle()), 0, GameWindow.WIN_HEIGHT);
//				
//				g.setColor(Color.RED);
//				//draw point vector
//				g.drawLine(0, GameWindow.WIN_HEIGHT-0, (int)p.e.getX(), GameWindow.WIN_HEIGHT-(int)p.e.getY());
//				//draw rect center vector
//				g.drawLine(0, GameWindow.WIN_HEIGHT-0, (int)r.e.getX(), GameWindow.WIN_HEIGHT-(int)r.e.getY());
//				
//				//rotate
//				g.rotate(-Math.toRadians(r.e.getAngle()), 0, GameWindow.WIN_HEIGHT);
//				//draw rectangle bounding box
//				g.setColor(Color.GREEN);
//				g.drawLine((int)(ru + r.corners[0].x), GameWindow.WIN_HEIGHT-(int)(rv + r.corners[0].y), 
//						   (int)(ru + r.corners[1].x), GameWindow.WIN_HEIGHT-(int)(rv + r.corners[1].y));
//				g.drawLine((int)(ru + r.corners[1].x), GameWindow.WIN_HEIGHT-(int)(rv + r.corners[1].y), 
//						   (int)(ru + r.corners[2].x), GameWindow.WIN_HEIGHT-(int)(rv + r.corners[2].y));
//				g.drawLine((int)(ru + r.corners[2].x), GameWindow.WIN_HEIGHT-(int)(rv + r.corners[2].y), 
//						   (int)(ru + r.corners[3].x), GameWindow.WIN_HEIGHT-(int)(rv + r.corners[3].y));
//				g.drawLine((int)(ru + r.corners[3].x), GameWindow.WIN_HEIGHT-(int)(rv + r.corners[3].y), 
//						   (int)(ru + r.corners[0].x), GameWindow.WIN_HEIGHT-(int)(rv + r.corners[0].y));
//				//draw point's hitbox
//				g.drawOval((int)pu-1, GameWindow.WIN_HEIGHT-(int)pv-1, 2, 2);
//				//draw corner hitboxes
//				g.setColor(Color.YELLOW);
//				g.drawOval((int)(ru+r.corners[0].x)-5, GameWindow.WIN_HEIGHT-(int)(rv+r.corners[0].y)-5, 10, 10);
//				g.drawOval((int)(ru+r.corners[1].x)-5, GameWindow.WIN_HEIGHT-(int)(rv+r.corners[1].y)-5, 10, 10);
//				g.drawOval((int)(ru+r.corners[2].x)-5, GameWindow.WIN_HEIGHT-(int)(rv+r.corners[2].y)-5, 10, 10);
//				g.drawOval((int)(ru+r.corners[3].x)-5, GameWindow.WIN_HEIGHT-(int)(rv+r.corners[3].y)-5, 10, 10);
//				//unrotate
//				g.rotate(Math.toRadians(r.e.getAngle()), 0, GameWindow.WIN_HEIGHT);
//			}
//		});
		
		boolean result = pu - ru < r.corners[0].x && pv - rv < r.corners[0].y &&
				         pu - ru > r.corners[1].x && pv - rv < r.corners[1].y &&
				         pu - ru > r.corners[2].x && pv - rv > r.corners[2].y &&
				         pu - ru < r.corners[3].x && pv - rv > r.corners[3].y;
		
//		if(result == true){
//			System.out.println("Point:\n\tpu: "+pu+"\n\tpv: "+pv+"\n"
//					+ "Rectangle:\n\trx: "+r.e.getX()+"\n\try: "+r.e.getY()+"\n\tru: "+ru+"\n\trv: "+rv+"\n"
//					+ "Corners:\n\t0: "+r.corners[0].toString()+"\n\t1: "+r.corners[1].toString()+"\n\t2: "+r.corners[2].toString()+"\n\t3: "+r.corners[3].toString());
//		}
		return result;
	}
	
	private static boolean collide(PointHitBox p, CircleHitBox c){
		//you could abstract this out to an implementation of circle where one has a radius of 0
		double dx = c.e.getX() - p.e.getX();
		double dy = c.e.getY() - p.e.getY();
		double distance = Math.sqrt(dx*dx + dy*dy);
		boolean result = distance < c.r;
//		if(result == true){
//			System.out.println("Point:\n\tentName: "+p.e.toString()+"\n\tx: "+p.e.getX()+"\n\ty: "+p.e.getY()+"\n\txOffset: "+p.xOffset+"\n\tyOffset: "+p.yOffset
//					+"\nCircle:\n\tentName: "+c.e.toString()+"\n\tx: "+c.e.getX()+"\n\ty: "+c.e.getY()+"\n\tr: "+c.r
//					+"\nCalculations:\n\tdx: "+dx+"\n\tdy: "+dy+"\n\tdistance: "+distance+"\n\tresult calculation: "+c.r+" > "+distance+"\n\tresult: "+result);
//		}
		return result;
	}
	
	private static boolean collide(RectHitBox r1, RectHitBox r2){
		for(Point p: r1.corners){
			if(collide(p.toHitBox(r1.e), r2)){
				return true;
			}
		}
		for(Point p: r2.corners){
			if(collide(p.toHitBox(r2.e), r1)){
				return true;
			}
		}
		return false;
	}
	
	private static boolean collide(RectHitBox r, CircleHitBox c){
		//we will forego the process of using a naive rectangular bound of BOTH, as many times r will be rotated and this would likely slow the algorithm on average
		//TODO profile that
		if(collide(r, new RectHitBox(c))){
			return true;
		}
		//check to see if circle's center is inside r
		if(collide(new PointHitBox(c.e, c.e.getX(), c.e.getY()), r)){
			return true;
		}
		//check to see if any edge intersects the circle
		for(Line edge: r.getEdges()){
			//see: https://en.wikipedia.org/wiki/Distance_from_a_point_to_a_line#Line_defined_by_two_points
			double distance = Math.pow( (edge.b.y-edge.a.y)*c.e.getX() - (edge.b.x-edge.a.x)*c.e.getY() + edge.b.x*edge.a.y - edge.b.y*edge.a.y, 2)/
					(Math.pow(edge.b.y-edge.a.y,2) + Math.pow(edge.b.x-edge.a.x, 2));
			if(distance < c.r){
				return true;
			}
		}
		return false;
	}
	
	private static boolean collide(CircleHitBox c1, CircleHitBox c2){
		double dx = c1.e.getX() - c2.e.getX();
		double dy = c1.e.getY() - c2.e.getY();
		double distance = Math.sqrt(dx*dx + dy*dy);
		return distance < c1.r + c2.r;
	}
	
	private static boolean within(double n, double center, double range){
		return Math.abs(n-center) < range;
	}
	
	private static boolean within(double n, double center, double upperRange, double bottomRange){
		return center+n < upperRange && center-n > bottomRange;
	}
	
	public static abstract class HitBox{
		Entity<?> e;
		public HitBox(Entity<?> e){
			this.e = e;
		}
		public abstract boolean collideWith(HitBox h);
	}
	
	public static class PointHitBox extends HitBox{
		double xOffset;
		double yOffset;
		public PointHitBox(Entity<PointHitBox> e){
			this(e, 0, 0);
		}
		public PointHitBox(Entity<?> e, double xOffset, double yOffset){
			super(e);
			this.xOffset = xOffset;
			this.yOffset = yOffset;
		}

		@Override
		public boolean collideWith(HitBox h) {
			if(h instanceof PointHitBox){
				PointHitBox h_cast = (PointHitBox)h;
				return collide(this, h_cast);
			} else if(h instanceof RectHitBox){
				RectHitBox h_cast = (RectHitBox)h;
				return collide(this, h_cast);
			} else if(h instanceof CircleHitBox){
				CircleHitBox h_cast = (CircleHitBox)h;
				return collide(this, h_cast);
			}
			return false;
		}
	}
	
	public static class RectHitBox extends HitBox{
		//Ordered in counter clockwise, ie 0 is top left corner and so on
		//Corner offsets should be applied after doing rotation transformations, as they are relative to the rectangle
		Point[] corners = new Point[4];
		public RectHitBox(Entity<?> e, double[] xCorners, double[] yCorners){
			super(e);
			for(int i = 0; i < 4; i++){
				corners[i] = new Point(xCorners[i], yCorners[i]);
			}
		}
		public RectHitBox(Entity<?> e, double width, double height){
			this(e, new double[]{width/2, -width/2, -width/2, width/2}, new double[]{height/2, height/2, -height/2, -height/2});
		}
		public RectHitBox(CircleHitBox c) {
			this(c.e, new double[]{c.r, -c.r, -c.r, c.r}, new double[]{c.r, c.r, -c.r, -c.r});
		}
		@Override
		public boolean collideWith(HitBox h) {
			if(h instanceof PointHitBox){
				PointHitBox h_cast = (PointHitBox)h;
				return collide(h_cast, this);
			} else if(h instanceof RectHitBox){
				RectHitBox h_cast = (RectHitBox)h;
				return collide(this, h_cast);
			} else if(h instanceof CircleHitBox){
				CircleHitBox h_cast = (CircleHitBox)h;
				return collide(this, h_cast);
			}
			return false;
		}
		public Line[] getEdges(){
			return new Line[]{new Line(corners[0], corners[1]), new Line(corners[1], corners[2]), new Line(corners[2], corners[3]), new Line(corners[3], corners[0])};
		}
	}
	
	public static class CircleHitBox extends HitBox{
		double r;
		public CircleHitBox(Entity<?> e, double r){
			super(e);
			this.r = r;
		}
		@Override
		public boolean collideWith(HitBox h) {
			if(h instanceof PointHitBox){
				PointHitBox h_cast = (PointHitBox)h;
				return collide(h_cast, this);
			} else if(h instanceof RectHitBox){
				RectHitBox h_cast = (RectHitBox)h;
				return collide(h_cast, this);
			} else if(h instanceof CircleHitBox){
				CircleHitBox h_cast = (CircleHitBox)h;
				return collide(this, h_cast);
			}
			return false;
		}
	}
	
	

}
