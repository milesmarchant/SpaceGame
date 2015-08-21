package physics;

/**
 * Heavily influenced by Sean Middleditch's Vec2 here:
 * <a href="https://github.com/seanmiddleditch/BubbleBattleBoids/blob/master/BubbleBattleBoids/Vec2.h">Vec2.h</a>
 * 
 * @author Miles Marchant
 *
 */
public class Vec2 {
	
	public double x;
	public double y;
	
	public Vec2(){
		this.x = 0;
		this.y = 0;
	}
	
	public Vec2(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double length(){
		return Math.sqrt(Math.pow(x, 2) + Math.pow(x, 2));
	}
	
	public Vec2 normalize(){
		assert(isZero() == false);
		return new Vec2(x / length(), y / length());
	}
	
	public boolean isZero(){
		return x == 0 && y == 0;
	}
	
	public Vec2 invert(){
		return new Vec2(1.0/x, 1.0/y);
	}
	
	public double getAngle(){
		return Math.atan2(y, x);
	}
	
	/**
	 * Finds the dot product of this vector with another vector
	 * 
	 * RESEARCH Check if this is at all right, maybe replace with the multiplication one
	 * 
	 * @param vec
	 * @return
	 */
	public double dot(Vec2 vec){
		return length() * vec.length() * Math.cos(getAngle()-vec.getAngle());
	}
	
	/**
	 * Returns an array of size 2 representing the x and y of a vector described in polar coordinates
	 * 
	 * @param r
	 * @param angle
	 * @return
	 */
	public static double[] convertToRect(double r, double angle){
		angle *= Math.PI/180.0;
		return new double[]{r*Math.cos(angle), r*Math.sin(angle)};
	}

}
