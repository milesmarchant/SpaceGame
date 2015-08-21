package entity;

import java.awt.Graphics2D;
import java.util.UUID;

import physics.Collision.HitBox;
import physics.Vec2;
import scene.Scene;
import sprite.Sprite;

//TODO see if we can refactor away this generic
public abstract class Entity<H extends HitBox> {
	
	protected String id;
	
	protected double x = 0;
	protected double y = 0;
	protected Vec2 vel;
	protected double angle = 0;
	protected double spriteAngle = 0;
	
	protected Sprite sprite;
	
	protected int spriteXOffset;
	protected int spriteYOffset;
	
	protected H hitBox;
	protected int collisionType;
	protected int[] collisionTargets;
	public static final int COLLISION_GROUP_PLAYER = 1;
	public static final int COLLISION_GROUP_ENEMY = 2;
	public static final int COLLISION_GROUP_POWERUP = 3;

	/**
	 * Use for initializations performed immediately before adding, as opposed to being intrinsically part of the entity.
	 */
	public abstract void init();
	
	public abstract void update(Scene scene);
	
	public abstract void draw(Graphics2D g);
	
	public abstract void onCollision(Scene scene, Entity<?> e);

	public String getId(){
		return id;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public Vec2 getVel(){
		return vel;
	}
	
	public double getAngle(){
		return angle;
	}
	
	public double getSpriteAngle(){
		return spriteAngle;
	}
	
	public H getHitBox(){
		return hitBox;
	}

	public int getCollisionType() {
		return collisionType;
	}

	public int[] getCollisionTargets() {
//		Integer[] result = new Integer[collisionTargets.length];
//		for(int i = 0; i < result.length; i++){
//			result[i] = collisionTargets[i];
//		}
//		return result;
		return collisionTargets;
	}
	
	public static String generateNewId(String base){
		UUID id = UUID.randomUUID();
		return base+"_"+id.toString();
	}
}
